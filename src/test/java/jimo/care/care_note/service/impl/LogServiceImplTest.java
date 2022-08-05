package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Log;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class LogServiceImplTest{
@Resource
LogServiceImpl logService;
    @Test
    void insert() {
     //   logService.insert(new Log(null,1,1, LocalDateTime.now(),1,"true"));
    }

    @Test
    void deleteByLID() {
   //    logService.deleteByLID(4);
    }

    @Test
    void getLogPage() {
        //Page<Log> logPage = logService.getLogPage(new Page<>(1, 5), Wrappers.<Log>query().ge("status", "true"));
      //  logPage.getRecords().forEach(System.out::println);
    }
}