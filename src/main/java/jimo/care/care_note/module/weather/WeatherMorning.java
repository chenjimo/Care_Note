package jimo.care.care_note.module.weather;

import jimo.care.care_note.bean.CareModule;
import jimo.care.care_note.bean.Setting;
import jimo.care.care_note.info.send.SendPhone;
import jimo.care.care_note.info.user.UserSettingStatus;
import jimo.care.care_note.info.weather.WeatherDay;
import jimo.care.care_note.info.weather.WeatherIndex;
import jimo.care.care_note.module.SendMessage;
import jimo.care.care_note.util.APIUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/***
 * 早安消息天气关怀模板
 */
@Component
public class WeatherMorning implements SendMessage {
    @Resource
    APIUtil apiUtil;
    @Resource
    SendPhone sendPhone;

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
        String auto = UserSettingStatus.AUTO;
        Setting setting = (Setting) map.get(Setting.class);
        CareModule module = (CareModule) map.get(CareModule.class);
        WeatherDay weatherDay = (WeatherDay) map.get(WeatherDay.class);
        WeatherIndex weatherIndex = (WeatherIndex) map.get(WeatherIndex.class);
        module.setMorning(Objects.equals(module.getMorning(), auto) ? weatherIndex.getDetail() : module.getMorning());
        module.setStatus(Objects.equals(module.getStatus(), auto) ? autoStatus(weatherDay) : module.getStatus());
        module.setTemp(Objects.equals(module.getTemp(), auto) ? weatherDay.getTempLow() + "~" + weatherDay.getTempHigh() : module.getTemp());
        sendPhone.setPhone(setting.getPhone());
        sendPhone.setM1(setting.getName());
        sendPhone.setM2(module.getStatus());
        sendPhone.setM3(module.getTemp());
        sendPhone.setM4(module.getMorning());
        return sendPhone.open();
    }

    /***
     * @param weatherDay 自动装配参数
     * @return 自动智能语句分析
     */
    private static String autoStatus(WeatherDay weatherDay) {
        return weatherDay.getWeather() + "最低都" + weatherDay.getTempLow() + "°啊！";
    }
}
