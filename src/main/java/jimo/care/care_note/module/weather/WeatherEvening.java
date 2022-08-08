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
 * 晚安消息天气关怀模板
 */
@Component
public class WeatherEvening implements SendMessage {
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
            module.setEvening(Objects.equals(module.getEvening(), auto) ? weatherIndex.getDetail() : module.getEvening());
            module.setStatus(Objects.equals(module.getStatus(), auto) ? autoStatus(weatherDay,weatherDay.getTempLow()) : module.getStatus());
            module.setTemp(Objects.equals(module.getTemp(), auto) ? Objects.requireNonNull(weatherDay).getTempLow() + "~" + weatherDay.getTempHigh() : module.getTemp());
            sendPhone.setPhone(setting.getPhone());
            sendPhone.setM1(setting.getName());
            sendPhone.setM2(module.getStatus());
            sendPhone.setM3(module.getTemp());
            sendPhone.setM4(module.getEvening());
        return sendPhone.open();
    }
    /***
     * @param weatherDay 自动装配参数
     * @return 自动智能语句分析
     */
    private String autoStatus(WeatherDay weatherDay,String tempLow) {
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
        return weatherDay.getWeather()+s;
    }
}
