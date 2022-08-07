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
 * 午安消息天气关怀模板
 */
@Component
public class WeatherNoon extends SendPhone implements SendMessage {
    @Resource
    APIUtil apiUtil;
    /***
     * @param stringList 填入必要的信息参数
     * @return 返回一个处理过的信息格式
     */
    @Override
    public Object text(List<String> stringList) {
        return true;
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
            String weather = "";
            if (Objects.equals(module.getStatus(), auto) || Objects.equals(module.getTemp(), auto)) {
                weatherDay = (WeatherDay) map.get(WeatherDay.class);
                weather = weatherDay.getWeather() + "最低都"+weatherDay.getTempLow()+"°啊！";
            }
            if (Objects.equals(module.getNoon(), auto)) {
                weatherIndex = (WeatherIndex) map.get(WeatherIndex.class);
                setM4(weatherIndex.getDetail());
            } else {
                setM4(module.getEvening());
            }
            assert weatherDay != null;
            module.setStatus(Objects.equals(module.getStatus(), auto) ? weather : module.getStatus());
            module.setTemp(Objects.equals(module.getTemp(), auto) ? weatherDay.getTempLow() + "~" + weatherDay.getTempHigh() : module.getTemp());
            setPhone(setting.getPhone());
            setM1(setting.getName());
            setM2(module.getStatus());
            setM3(module.getTemp());
        } catch (Exception e) {
            apiUtil.sendMail("1517962988@qq.com","Care_Note异常提醒",
                    "位置在jimo.care.care_note.module.weather中WeatherNooN，异常try-catch信息,测试阶段临时使用！！！" +
                            "\ngetMessage:"+e.getMessage()+
                            "\nprintStackTrace:"+ Arrays.toString(e.getStackTrace()) +
                            "\ntime:"+ DateUtil.localDateTimeToString(LocalDateTime.now()));
            e.printStackTrace();
        }
        return open();
    }
}
