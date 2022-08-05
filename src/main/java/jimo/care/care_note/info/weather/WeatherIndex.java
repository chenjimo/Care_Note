package jimo.care.care_note.info.weather;

import lombok.Data;

/***
 * 天气指数消息Bean
 */
@Data
public class WeatherIndex {
    private String iname;
    private String ivalue;
    private String detail;
}
