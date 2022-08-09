package jimo.care.care_note.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
class SendConfigTest {
    @Resource
    SendConfig sendConfig;
    @Value("${jimo.api-util.stringLimit}")
    private int stringLimit;

    @Test
    void accept() {
        System.out.println(stringLimit);
    }

    @Test
    void scheduledMorning() {
       // sendConfig.scheduledMorning();
    }

    @Test
    void scheduledNoon() {
       // sendConfig.scheduledNoon();
    }

    @Test
    void scheduledEvening() {
        //sendConfig.scheduledEvening();
    }

    @Test
    void scheduledOlg() {
        Map<String,String> map =new HashMap<>();
        map.put("xxx","yyy");
        System.out.println(map);
        System.out.println(map.getClass());
        map= new HashMap<>();
        System.out.println(map);
        System.out.println(map.getClass());
    }

    @Test
    void scheduledHint() {
    }

    @Test
    void scheduledTest() {
    }

    @Test
    void fun() {
    }
}