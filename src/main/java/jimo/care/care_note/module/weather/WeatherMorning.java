package jimo.care.care_note.module.weather;
/***
 * 早安消息天气关怀模板
 */
public class WeatherMorning {
    private static String weatherJSON;
    private static String status;
    private static String temp;
    private static String morning;

    public WeatherMorning(String weather) {
        weatherJSON=weather;
    }

    public static String getStatus(){

        return status;
    }
}
