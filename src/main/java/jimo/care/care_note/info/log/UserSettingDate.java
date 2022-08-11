package jimo.care.care_note.info.log;

import lombok.Data;

@Data
public class UserSettingDate {
    private String name;
    private String setting;
    private String localTime;

    public UserSettingDate(String name, String setting, String localTime) {
        this.name = name;
        this.setting = setting;
        this.localTime = localTime;
    }
}
