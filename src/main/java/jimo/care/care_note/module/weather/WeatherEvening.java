package jimo.care.care_note.module.weather;

import jimo.care.care_note.bean.CareModule;
import jimo.care.care_note.bean.Setting;
import jimo.care.care_note.info.send.SendPhone;
import jimo.care.care_note.info.user.UserSettingStatus;
import jimo.care.care_note.info.weather.WeatherDay;
import jimo.care.care_note.info.weather.WeatherIndex;
import jimo.care.care_note.module.SendMessage;
import jimo.care.care_note.util.APIUtil;
import jimo.care.care_note.util.DateUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/***
 * 晚安消息天气关怀模板
 */
@Component
public class WeatherEvening extends SendPhone implements SendMessage {
    @Resource
    APIUtil apiUtil;

    /***
     * @param stringList 填入必要的信息参数
     * @return 返回一个处理过的信息格式
     */
    @Override
    public Object text(List<String> stringList) {
        return null;
    }

    /***
     * @param map 存入对象信息
     * @return 返回标准消息格式
     */
    @Override
    public String test(Map<Class, Object> map) {
        try {
            String auto = UserSettingStatus.AUTO;
            Setting setting = (Setting) map.get(Setting.class);
            CareModule module = (CareModule) map.get(CareModule.class);
            WeatherDay weatherDay = null;
            WeatherIndex weatherIndex;
            String weather ="";
            if (Objects.equals(module.getStatus(), auto) || Objects.equals(module.getTemp(), auto)) {
                weatherDay = (WeatherDay) map.get(WeatherDay.class);
                weather = weatherDay.getWeather() + tempL(weatherDay.getTempLow());
            }
            if (Objects.equals(module.getEvening(), auto)) {
                weatherIndex = (WeatherIndex) map.get(WeatherIndex.class);
                setM4(weatherIndex.getDetail());
            } else {
                setM4(module.getEvening());
            }

            module.setStatus(Objects.equals(module.getStatus(), auto) ? weather : module.getStatus());
            module.setTemp(Objects.equals(module.getTemp(), auto) ? Objects.requireNonNull(weatherDay).getTempLow() + "~" + weatherDay.getTempHigh() : module.getTemp());
            setPhone(setting.getPhone());
            setM1(setting.getName());
            setM2(module.getStatus());
            setM3(module.getTemp());
        } catch (Exception e) {
            apiUtil.sendMail("1517962988@qq.com","Care_Note异常提醒",
                    "位置在jimo.care.care_note.module.weather中WeatherEvening，异常try-catch信息,测试阶段临时使用！！！" +
                            "\ngetMessage:"+e.getMessage()+
                            "\nprintStackTrace:"+ Arrays.toString(e.getStackTrace()) +
                            "\ntime:"+ DateUtil.localDateTimeToString(LocalDateTime.now()));
            e.printStackTrace();
        }
        return open();
    }

    private String tempL(String tempLow) {
        String s;
        int i = Integer.parseInt(tempLow);
        if (i > 28) {
            s = "答应我今晚一定要开空调！最低气温都" + i + "°";
        } else if (i > 24) {
            s = "今晚最低" + i + "°可不怕你踢被子了";
        } else if (i > 20) {
            s = "虽然" + i + "°不是很热，刚好是爱的温度";
        } else if (i > 16) {
            s = "夜渐微凉今晚" + i + "°但爱你不减少";
        } else if (i > 12) {
            s = i + "°这有点低呀！放心爱为你发热";
        } else if (i > 8) {
            s = "别嫌我烦，盖好被子，今晚" + i + "°";
        } else if (i > 4) {
            s = "呀今晚都" + i + "°心尖火苗为你预留";
        } else if (i > 0) {
            s = "已经" + i + "°不知道啥时候和你去看开雪";
        } else if (i > -4) {
            s = "这" + i + "°我想离和你一起看雪不远了";
        } else if (i > -8) {
            s = i + "°的天应该结冰了吧，一起滑雪呀";
        } else {
            s = i + "这天太奇葩，我脑子都给搞坏了";
        }
        return s;
    }
}
