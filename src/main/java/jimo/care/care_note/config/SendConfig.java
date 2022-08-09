package jimo.care.care_note.config;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jimo.care.care_note.bean.*;
import jimo.care.care_note.info.user.UserPower;
import jimo.care.care_note.info.user.UserSettingStatus;
import jimo.care.care_note.info.weather.WeatherDay;
import jimo.care.care_note.info.weather.WeatherIndex;
import jimo.care.care_note.module.DeveloperMessage;
import jimo.care.care_note.module.UserMessage;
import jimo.care.care_note.module.weather.WeatherEvening;
import jimo.care.care_note.module.weather.WeatherMorning;
import jimo.care.care_note.module.weather.WeatherNoon;
import jimo.care.care_note.service.SendFunction;
import jimo.care.care_note.service.impl.*;
import jimo.care.care_note.util.APIUtil;
import jimo.care.care_note.util.DateUtil;
import jimo.care.care_note.util.JSONUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;

/**
 * <p>
 * 服务实现类
 * 定时的核心业务实现！！！
 * 超级无敌大类，这个业务差点给俺干到土里面！！！
 * </p>
 *
 * @author JIMO
 * @since 2022-08-07
 */
@Component
public class SendConfig implements Consumer<String>, SendFunction {
    @Resource
    RelationServiceImpl relationService;
    @Resource
    UserServiceImpl userService;
    @Resource
    SettingServiceImpl settingService;
    @Resource
    ModuleServiceImpl moduleService;
    @Resource
    LogServiceImpl logService;
    @Resource
    WeatherMorning weatherMorning;
    @Resource
    WeatherNoon weatherNoon;
    @Resource
    WeatherEvening weatherEvening;
    @Resource
    APIUtil apiUtil;
    @Resource
    DeveloperMessage developerMessage;
    @Resource
    UserMessage userMessage;
    private static Map<String, WeatherIndex> weatherIndexMap = new HashMap<>();
    private static Map<String, WeatherDay> weatherDayMap = new HashMap<>();

    /**
     * Performs this operation on the given argument.
     *
     * @param s the input argument
     */
    @Override
    public void accept(String s) {
        switch (s) {
            case "0 0 7 * * ?":
                scheduledMorning();
                break;
            case "0 0 12 * * ?":
                scheduledNoon();
                break;
            case "0 0 21 * * ?":
                scheduledEvening();
                break;
            case "0 0 22 * * ?":
                scheduledOlg();
                break;
            case "Test":
                scheduledTest();
                break;
        }
    }

    /***
     * 每天的上午七点执行一次！
     * power 1、3、5、7
     */
    @Override
    public void scheduledMorning() {
        fun(UserSettingStatus.MORNING);
    }

    /***
     * 每天的中午十二点执行一次！
     * power 2、3、6、7
     */
    @Override
    public void scheduledNoon() {
        fun(UserSettingStatus.NOON);
    }

    /***
     * 每天的晚上九点执行一次！
     * power 4、5、6、7
     */
    @Override
    public void scheduledEvening() {
        fun(UserSettingStatus.EVENING);
    }

    /***
     * 每天的晚上十点执行一次！
     */
    @Override
    public void scheduledOlg() {
        adminDaily(UserPower.ADMIN_ALERT);
    }


    /***
     * 测试每10秒一执行
     */
    @Override
    public void scheduledTest() {
    }

    /***
     * @param power 服务与关怀业务的核心方法，填写服务权限码即可使用
     */
    void fun(Integer power) {
        relationService.AdminGetRelations(null, null).getRecords()
                .forEach(r -> cha(r, power));
        zeroSet();
    }

    /***
     * @param relation 通过表关系查出对应关系和权限
     * @param power 此服务的权限码
     */
    private void cha(Relation relation, Integer power) {
        Map<Class, Object> map = new HashMap<>();
        User user = userService.getUserByUID(relation.getUId());
        Setting setting = settingService.UserGetSetting(relation.getSId());
        CareModule module = moduleService.getModule(relation.getMId());
        map.put(Setting.class, setting);
        map.put(CareModule.class, module);
        Integer money = user.getMoney();
        boolean b = power(setting.getStatus(), power);
        if (money > 0 && b) {
            //进入这里已经证明此需求是必要的执行
            //1.先判断Module是否需要请求weather
            if (judge(module, power)) {
                //2.查看已经存在的weatherInfo是否已有该信息，避免多次请求造成资源浪费
                //为防止第一次初始化weatherIndexMap、weatherDayMap,
                if (weatherDayMap==null|| weatherIndexMap==null||weatherIndexMap.get(setting.getLocal()) == null || weatherDayMap.get(setting.getLocal()) == null) {
                    String weather = apiUtil.getWeather(setting.getLocal());
                    Object msg = JSONObject.fromObject(weather).get("msg");
                    if ("ok".equals(msg)) {
                        //3.根据类型选择JSONUtil处理方式
                        jsonPutMaps(weather, power, setting.getLocal());
                    } else {
                        //失败提示运维团队！！！
                        getWeatherError(162, "类中的\"cha()\"方法" + DateUtil.localDateTimeToString(LocalDateTime.now()) + "调用API-weather：" + msg);
                    }
                }
                map.put(WeatherIndex.class, weatherIndexMap.get(setting.getLocal()));
                map.put(WeatherDay.class, weatherDayMap.get(setting.getLocal()));
            }
            //最后分类执行方法内容
            String open = open(power, map);
            if ("OK".equalsIgnoreCase(open)){
                //成功写入日志,并扣除一次钱！！！！
                Log aTrue1 = new Log(user.getId(), setting.getId(), module.getId(), "true");
                boolean aTrue = logService.insert(aTrue1);
                User u1 = new User();
                u1.setId(user.getId());
                u1.setMoney(money-1);
                String errorTime = DateUtil.localDateTimeToString(LocalDateTime.now());
                //扣钱成功提醒消费成功！
                if (userService.updateByUID(u1)&&aTrue){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    alterUser(user,
                            "<p style='font-size: 15px; background: linear-gradient(to right, red, blue);-webkit-background-clip: text; color: transparent;'>" +
                                    "\t\t亲爱的用户就在刚刚‘"+errorTime+"'o(*￣▽￣*)ブ" +
                                    "\n您成功的对您亲爱的："+setting.getName()+(setting.getSex() == 0 ? "女士" : "男士")+"发起了关怀！♥远在"+setting.getLocal()+"的"+(setting.getSex() == 0 ? "她" : "他")+"一定也会挂念您的！！！"+
                                    "\n\t感谢您对JIMO-Care_Note的信任！🤩" +
                                    "\n本次服务您的订单记录已经生成:订单编号为（"+aTrue1.getId()+"），您的余额还剩"+(money-1)+"次!"+
                                    "\n温馨提示：亲，"+(money==1?"本次是JIMO最后一次为您服务了😔，我哪里做的不好可以打我骂我请不要离开我好嘛！╥﹏╥...":money<4?"系统检测到您的余额不足！请持续为爱充电，谢谢！":"您的余额充足O(∩_∩)O，起码这两天够用(●'◡'●)，大胆的为爱发光吧！")+
                                    "</p>");
                }else {
                    //扣钱失败提醒管理员和运维团队！！！
                    adminAlter(182,
                            "订单异常","此订单日志记录：’"+aTrue+"‘" +(aTrue?"("+aTrue1+")":"请尽快检查程序！！！")+
                                    "\n\n订单内容:用户-"+user.getName()+"(ID:"+user.getId()+"),关怀对象-"+setting.getName()+"(ID:"+setting.getId()+")，模板-"+module.getName()+"(ID:"+module.getId()+")" +
                                    "\n\n"+(!aTrue ?"请手动检测账本，对用户的money进行核实处理！":"请尽快维修程序！！！" +
                                    "\n\n异常时间："+errorTime)
                    );
                }
            }else {
                //失败也写入日志！！！并提醒管理员和运维小伙伴
                Log aTrue1 = new Log(user.getId(), setting.getId(), module.getId(), open);
                boolean aTrue = logService.insert(aTrue1);
                adminAlter(173,
                        "消息发送异常","此订单日志记录：’"+aTrue+"‘" +(aTrue?"("+aTrue1+")":"请尽快检查程序！！！")+
                                "\n订单内容:用户-"+user.getName()+"(ID:"+user.getId()+"),关怀对象-"+setting.getName()+"(ID:"+setting.getId()+")，模板-"+module.getName()+"(ID:"+module.getId()+")" +
                                "\n"+(!aTrue ?"请手动检测，对用户、模板、关怀对象进行核对处理！":"请尽快维修程序！！！" +
                                "\nSendPhone异常时间："+DateUtil.localDateTimeToString(LocalDateTime.now()))
                );
            }

        }
    }

    /***
     * @param p 被关怀对象的服务状态码
     * @param power 此服务的权限码
     */
    private boolean power(Integer p, Integer power) {
        boolean b;
        if ((!Objects.equals(p, UserSettingStatus.DELETE) && !Objects.equals(p, UserSettingStatus.STOP) && Objects.equals(p, power)) || Objects.equals(p, UserSettingStatus.ALL)) {
            b = true;
        } else {
            if ((Objects.equals(power, UserSettingStatus.MORNING) || Objects.equals(power, UserSettingStatus.NOON)) && Objects.equals(p, UserSettingStatus.MORNING_NOON)) {
                b = true;
            } else if ((Objects.equals(power, UserSettingStatus.MORNING) || Objects.equals(power, UserSettingStatus.EVENING)) && Objects.equals(p, UserSettingStatus.MORNING_EVENING)) {
                b = true;
            } else
                b = (Objects.equals(power, UserSettingStatus.NOON) || Objects.equals(power, UserSettingStatus.EVENING)) && Objects.equals(p, UserSettingStatus.NOON_EVENING);
        }
        return b;
    }

    /***
     * @param module 传入模型的数据
     * @param power 传入此时的场景状态码
     * @return 返回Boolean是否需要getWeather
     */
    private boolean judge(CareModule module, Integer power) {
        String auto = UserSettingStatus.AUTO;
        if (Objects.equals(power, UserSettingStatus.MORNING)) {
            return auto.equals(module.getMorning())||auto.equals(module.getStatus()) || auto.equals(module.getTemp());
        } else if (Objects.equals(power, UserSettingStatus.NOON)) {
            return auto.equals(module.getNoon())||auto.equals(module.getStatus()) || auto.equals(module.getTemp());
        } else if (Objects.equals(power, UserSettingStatus.EVENING)) {
            return auto.equals(module.getEvening())||auto.equals(module.getStatus()) || auto.equals(module.getTemp());
        }
        return false;
    }

    /***
     * 每一次有过的Map结束
     * 清楚归零防止下次缓存影响，不能null处理缓存！！！！
     */
    private void zeroSet() {
        weatherDayMap = new HashMap<>();
        weatherIndexMap = new HashMap<>();
    }

    /***
     * 处理了获取天气异常的情况发送消息给管理员Email
     * 以及运营钉钉群
     */
    private void getWeatherError(Integer h, String error) {
        List<String> strings = new ArrayList<>();
        strings.add(String.valueOf(UserPower.ADMIN_ALERT));
        strings.add("Care_NOte运维提醒:API-ERROR");
        strings.add("激发消息位置：jimo.care.care_note.config中的getWeather方法用处" + h + "行出现了异常!\n" +
                "接口给出的信息为：" + error + "\n我觉得可能原因为：Weather-API的url失效、密钥过时、QPS过高、参数信息异常等！！！");
        developerMessage.text(strings);
    }
    /***
     * 处理了获取扣钱失败异常的情况发送消息给管理员Email
     * 以及运营钉钉群
     */
    private void adminAlter(Integer h,String alter ,String error) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> strings = new ArrayList<>();
        strings.add(String.valueOf(UserPower.ADMIN_ALERT));
        strings.add("Care_NOte运维提醒:"+alter+"");
        strings.add("激发消息位置：jimo.care.care_note.config中的" + h + "行出现了异常!\n" +
                "接口给出的信息为：" + error + "\n我觉得可能原因为：API的url失效、密钥过时、QPS过高、参数信息异常等！！！");
        developerMessage.text(strings);
    }
    /***
     * @param adminAlert 接受程序每日日报的权限码
     */
    private void adminDaily(Integer adminAlert) {
        List<String> stringList = new ArrayList<>();
        List<Map<String, Object>> countMaps = logService.getCountMaps(Wrappers.<Log>query().select("status", "count(*)").groupBy("status"));
        Integer count = userService.getCount(null);
        Integer moduleCount = moduleService.getModuleCount(null);
        Integer settingCount = settingService.getSettingCount(null);
        stringList.add(String.valueOf(adminAlert));
        stringList.add("Care_NOte运维:每日详报！");
        stringList.add("<p style='font-size: 15px;  background: linear-gradient(to right, red, blue);-webkit-background-clip: text; color: transparent;'>" +
                "\n\n日志记录汇总概况：" +countMaps+
                "\n\n截至今日当前时间：" +DateUtil.localDateTimeToString(LocalDateTime.now())+
                "\n\n注册的用户总数为："+count+"\t创建的模板总数为："+moduleCount+"\t关怀的对象总数为："+settingCount+
                "</p>");
        developerMessage.text(stringList);
    }
    /***
     * @param weather 天气信息处理JSONString
     * @param power 权限状态码
     * @param local 地址信息
     *              多情况利用随机数进行选取决定！
     *  UserSettingStatus.MORNING 早上选择则:
     *              weatherIndexMap:运动指数1、空气污染扩散指数5,weatherDayMap:当天
     *  UserSettingStatus.NOON 中午选择:
     *              weatherIndexMap:紫外线指数2、洗车指数4,weatherDayMap:当天
     *  UserSettingStatus.EVENING 晚上选择:
     *              weatherIndexMap:空调指数0、穿衣指数6,weatherDayMap:明天
     */
    private void jsonPutMaps(String weather, Integer power, String local) {
        JSONUtil.init(weather);
        List<WeatherIndex> weatherIndices = JSONUtil.indexList();
        List<WeatherDay> weatherDays = JSONUtil.WeatherDays();
        int i = new Random().nextInt(2);
        if (Objects.equals(power, UserSettingStatus.MORNING)) {
            weatherIndexMap.put(local, weatherIndices.get(i == 1 ? 1 : 5));
            weatherDayMap.put(local, weatherDays.get(0));
        } else if (Objects.equals(power, UserSettingStatus.NOON)) {
            weatherIndexMap.put(local, weatherIndices.get(i == 1 ? 2 : 4));
            weatherDayMap.put(local, weatherDays.get(0));
        } else if (Objects.equals(power, UserSettingStatus.EVENING)) {
            weatherIndexMap.put(local, weatherIndices.get(i == 1 ? 0 : 6));
            weatherDayMap.put(local, weatherDays.get(0));
        }
    }

    /***
     * @param power 对应不同场景的智能模板
     * @param map 传入重要信息参数，个性化模板！
     * @return 返回处理结果“OK”表示成功
     */
    private String open(Integer power, Map<Class, Object> map) {
        String code = null;
        switch (power) {
            case 1:
                code = weatherMorning.test(map);
                break;
            case 2:
                code = weatherNoon.test(map);
                break;
            case 4:
                code = weatherEvening.test(map);
                break;
        }
        return code;
    }
    private void alterUser(User u, String details){
        List<String> strings = new ArrayList<>();
        strings.add(u.getEmail());
        strings.add("Care_NOte业务提醒:");
        strings.add(u.getName());
        strings.add(String.valueOf(u.getPower()));
        strings.add(details);
        userMessage.text(strings);
    }
}
