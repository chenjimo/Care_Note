package jimo.care.care_note.config;

import jimo.care.care_note.bean.Relation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class SendConfigTest {

    @Test
    void accept() {
        Relation relation = new Relation(1, 1, 1);
        Map<Class,Object> map = new HashMap<Class,Object>();
        map.put(Relation.class,relation);
        Relation relation1 = (Relation) map.get(Relation.class);
        System.out.println(relation1);
    }

    @Test
    void scheduledMorning() {
    }

    @Test
    void scheduledNoon() {
    }

    @Test
    void scheduledEvening() {
    }

    @Test
    void scheduledOlg() {
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