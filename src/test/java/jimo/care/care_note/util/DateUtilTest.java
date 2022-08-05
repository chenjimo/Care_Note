package jimo.care.care_note.util;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {
    @Test
    void localDateTimeToString() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now+"--->>>"+ DateUtil.localDateTimeToString(now));
    }

    @Test
    void dateToString() {
        Date date = new Date();
        System.out.println(date+"--->>>"+DateUtil.dateToString(date));

    }
}