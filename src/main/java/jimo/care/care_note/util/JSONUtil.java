package jimo.care.care_note.util;

import jimo.care.care_note.info.weather.WeatherDay;
import jimo.care.care_note.info.weather.WeatherIndex;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/***
 * 天气状况回馈数据提取处理工具
 */
public class JSONUtil {
    private static Object result;

    /***
     * @param weather 原数据初步处理
     */
    public static void init(String weather) {
        result = JSONObject.fromObject(weather).get("result");
    }

    /***
     * @return 获取WeatherIndex的List
     */
    public static List<WeatherIndex> indexList() {
        List<WeatherIndex> weatherIndices = new ArrayList<>();
        Object index = JSONObject.fromObject(result.toString()).get("index");
        JSONArray jsonArray = JSONArray.fromObject(index.toString());
        jsonArray.forEach(i ->
                weatherIndices.add((WeatherIndex) JSONObject.toBean((JSONObject) i, WeatherIndex.class))
        );
        return weatherIndices;
    }

    /***
     * @return 获取WeatherIndex的List（0为当天，1为明天，总共显示最近一周数据）
     */
    public static List<WeatherDay> WeatherDays() {
        ArrayList<WeatherDay> weatherDays = new ArrayList<>();
        Object daily = JSONObject.fromObject(result.toString()).get("daily");
        JSONArray jsonArray = JSONArray.fromObject(daily.toString());
        jsonArray.forEach(d -> weatherDays.add(setWeatherDay((JSONObject) d)));
        return weatherDays;
    }

    /***
     * 通过对每日数据提炼处理
     * @param daily 传入JSON
     * @return 返回一点最重要数据
     */
    public static WeatherDay setWeatherDay(JSONObject daily) {
        Object night = JSONObject.fromObject(daily).get("night");
        Object day = JSONObject.fromObject(daily).get("day");
        return new WeatherDay(JSONObject.fromObject(day).get("weather").toString(),
                JSONObject.fromObject(day.toString()).get("temphigh").toString(),
                JSONObject.fromObject(night.toString()).get("templow").toString());
    }

}
