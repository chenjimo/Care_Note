package jimo.care.care_note.info.send;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class SendPhoneTest {
@Resource
SendPhone sendPhone;
    @Test
    void open() {
        sendPhone.setPhone("17761612832");
        System.out.println(sendPhone);
    }
}