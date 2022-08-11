package jimo.care.care_note.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class LimitUtilTest {
    @Resource
    LimitUtil limitUtil;

    @Test
    void stringLimit() {
        System.out.println(limitUtil.stringLimit("气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。123456789"));
    }
    @Test
    void conde(){
        System.out.println(limitUtil.encode("陈"));
        System.out.println(limitUtil.encode("陈浩"));
        System.out.println(limitUtil.encode("陈己墨"));
        System.out.println(limitUtil.encode("JIMO"));
        System.out.println(limitUtil.encode("JIMOW"));
        System.out.println(limitUtil.encode("JIMOWO"));
        System.out.println(limitUtil.encode("JIMOWOR"));
        System.out.println(limitUtil.encode("JIMOWORL"));
        System.out.println(limitUtil.encode("JIMOWORLD"));
    }
}
