package jimo.care.care_note.util;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/***
 * 用作对时间进行规范处理了的工具
 */
public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /***
     * @param localDateTime 2022-08-05T12:00:05.459
     * @return 2022-08-05 12:00:05
     */
    public static String localDateTimeToString(LocalDateTime localDateTime){
        return localDateTime.format(formatter);
    }

    /***
     * @param date Fri Aug 05 11:58:50 CST 2022
     * @return 2022年8月5日 星期五
     */
    public static String dateToString(Date date){
        DateFormat df = DateFormat.getDateInstance(DateFormat.ERA_FIELD);
        return df.format(date);
    }
}
