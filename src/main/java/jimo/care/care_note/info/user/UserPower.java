package jimo.care.care_note.info.user;

/***
 * 此类用于举例各种不同权限的用户power：
 * 4修改权限，
 * 2消息提醒，
 * 1普通用户，默认注册均为1；
 * 0封禁或注销，
 * 3=1+2（用户消息提醒），
 * 5=1+4（管理用户权限），
 * 6=4+2（运维消息提醒权限），
 * 7全部权限+admin管控
 */
public class UserPower {
    public static final Integer DELETE=0;
    public static final Integer USER=1;
    public static final Integer ALERT=2;
    public static final Integer USER_ALERT=3;
    public static final Integer UPDATE=4;
    public static final Integer ADMIN=5;
    public static final Integer ADMIN_ALERT=6;
    public static final Integer JIMO=7;
}
