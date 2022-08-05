package jimo.care.care_note.info.weather;

import lombok.Data;

/***
 * 重要天气数据Bean
 */
@Data
public class WeatherDay {
    private String weather;
    private String tempHigh;
    private String tempLow;

    public WeatherDay(String weather, String tempHigh, String tempLow) {
        this.weather = weather;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
    }
}
