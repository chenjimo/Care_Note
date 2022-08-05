package jimo.care.care_note.info.user;

/***
 * 此类用于举例各种服务类型的用户关怀对象的status：
 * status（int值）0表示暂停服务；
 * 1表示早安服务；2表示午安服务；4表示晚安服务；
 * 3=1+2服务、5=1+4服务、6=2+4服务、7=1+2+4服务。
 */
public class UserSettingStatus {
    public static final Integer DELETE = 0;
    public static final Integer MORNING = 1;
    public static final Integer NOON = 2;
    public static final Integer EVENING = 4;
    public static final Integer MORNING_NOON = 3;
    public static final Integer MORNING_EVENING = 5;
    public static final Integer NOON_EVENING = 6;
    public static final Integer ALL = 7;
}
