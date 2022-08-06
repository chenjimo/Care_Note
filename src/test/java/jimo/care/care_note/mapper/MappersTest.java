package jimo.care.care_note.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Log;
import jimo.care.care_note.bean.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/***
 * 已经全部测试成功封闭起来
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
class MappersTest {
  /*  @Resource
    UserMapper userMapper;
    @Resource
    SettingMapper settingMapper;
    @Resource
    RelationMapper relayionMapper;
    @Resource
    ModuleMapper moduleMapper;
    @Resource
    LogMapper logMapper;
    @Resource
    PageMapper pageMapper;*/

    /***
     * 切记莫再扩展功能可以先实现再完善，莫着急权当复习Java框架！！！！
     * 1.先研究Wappers的用法与关系
     * 2.封装Page信息方法
     * 3.创建枚举
     * 4.编写controller&function
     * 5.前端
     * 6.事物回滚&过滤器
     */
    /*@Test
    void userTest() {
        System.out.println(userMapper.selectById(null)==null?null:1);
        Page<User> page = new Page<>(1,5);
        Page<User> pageResult = userMapper.selectPage(page, Wrappers.<User>lambdaQuery().like(User::getEmail,"qq"));
        System.out.println("总条数 ------> " + pageResult.getTotal());
        System.out.println("当前页数 ------> " + pageResult.getCurrent());
        System.out.println("当前每页显示数 ------> " + pageResult.getSize());
        pageResult.getRecords().forEach(System.out::println);
//        User user = new User();
//        user.setEmail("745076951@qq.com");
//        user.setMoney(10);
//        user.setPwd("123456");
//        user.setPhone("18621127661");
//        System.out.println(user);
//       int insert = userMapper.insert(user);
//        System.out.println(insert);
//        System.out.println(user);
    }

    @Test
    void settingTest() {
        System.out.println(settingMapper.selectById(1));
    }

    @Test
    void relayionTest() {
        System.out.println(relayionMapper.selectById(1));
    }

    @Test
    void moduleTest() {
        System.out.println(moduleMapper.selectById(1));
    }

    @Test
    void logTest() {
       // System.out.println(logMapper.selectById(1));
        Log log = new Log( 1, 1,  1, "true");
        System.out.println(log);
     //   logMapper.insert(log);
        //  System.out.println(log);
       // int delete = logMapper.updateById(new Log(log.getId(), null, null, null, null, "delete"+new Date()));
     //   System.out.println(delete);

    }
    @Test
    void PageTest(){
        jimo.care.care_note.bean.Page page = new jimo.care.care_note.bean.Page();
        page.setName("JIMO");
        int insert = pageMapper.insert(page);
        System.out.println(insert+":"+page);
    }*/
}