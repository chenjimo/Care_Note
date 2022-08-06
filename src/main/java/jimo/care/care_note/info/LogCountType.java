package jimo.care.care_note.info;

/***
 * 此类用于便于观测收集信息数的类型
 * 0：全部，1：全部成功记录；
 * 2：享受服务的用户数，3：最佳暖王ID(大客户)；
 * 4：被关怀的对象数，5：被关心最多的女（男）神（小心被骚扰疯掉）；
 * 6：使用的模板数，7：最受欢迎的模板（情圣）；
 */
public class LogCountType {
    public static final Integer ALL_LOG=0;
    public static final Integer ALL_SUCCEED=1;
    public static final Integer LOG_USER=2;
    public static final Integer USER_MAX_ID=3;
    public static final Integer LOG_SETTING=4;
    public static final Integer SETTING_MAX_ID=5;
    public static final Integer LOG_MODULE=6;
    public static final Integer MODULE_MAX_ID=7;
}
