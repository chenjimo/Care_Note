package jimo.care.care_note.pojo;

import lombok.Data;

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