package jimo.care.care_note.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class APIUtilTest {
    @Autowired
    APIUtil apiUtil;

    @Test
    void getWeather() {
        String weather = apiUtil.getWeather("郑州");
        System.out.println(weather);
    }

    @Test
    void sendSms() {
        //注意限定字符在20以内超过自动分割
        String name = "1234567890123456789012345678";
        String phoneNumber = "17761612832";
        String s1 = "1234567890123456789012345678";
        String s2 = "1234567890123456789012345678";
        String s3 = "1234567890123456789012345678";//28
        boolean send = apiUtil.sendPhone(phoneNumber, name, s1, s2, s3);
        System.out.println(send);
    }

    @Test
    void sendEmail() {
        apiUtil.sendMail("1517962688@qq.com", "JIMO-Care", "test01");
    }
}