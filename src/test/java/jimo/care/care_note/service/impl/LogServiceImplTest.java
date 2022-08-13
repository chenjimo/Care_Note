package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.CareModule;
import jimo.care.care_note.bean.Log;
import jimo.care.care_note.bean.Relation;
import jimo.care.care_note.bean.User;
import jimo.care.care_note.info.LogCountType;
import jimo.care.care_note.info.log.CountThree;
import jimo.care.care_note.mapper.LogMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * <p>
 *  已经测试成功，关系数据库的操作，以防数据操作变革，设计增、删、改测试已经注释！！！
 *  需要的再解开注释测试，注意非必要不再进行测试！！！
 *  打包时，注意跳过测试方法，不然后果可怕！！！！
 *  <p/>
 *  @author JIMO
 *  @since 2022-08-06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class LogServiceImplTest {
    @Autowired
    LogServiceImpl logService;
    @Resource
    ModuleServiceImpl moduleService;

    @Test
    void insert() {
//        logService.insert(new Log(2, 2, 1, "true"));
//        logService.insert(new Log(2, 3, 1, "true"));
//        logService.insert(new Log(1, 2, -1, "true"));
//        logService.insert(new Log(1, 3, -2, "true"));
//        logService.insert(new Log(1, 4, -2, "true"));
//        logService.insert(new Log(1, 1, -2, "true"));
    }

    @Test
    void deleteByLID() {
        //logService.deleteByLID(4);
    }

    @Test
    void adminGetLog() {
        logService.AdminGetLog(new Page<>(1, 10), null)
                .getRecords().forEach(System.out::println);
    }

    @Test
    void userGetLog() {
        logService.UserGetLog(new Page<>(1,5),1)
                .getRecords().forEach(System.out::println);
    }

    @Test
    void getCount() {
        List<Map<String, Object>> countMaps = logService.getCountMaps(Wrappers.<Log>query()
                .select("count(u_id),count(m_id),count(s_id)")
                .groupBy("status"));
        countMaps.forEach(System.out::println);
    }
    @Test
    void Count(){
        QueryWrapper<Log> qw = new QueryWrapper<>();
        qw.select("count(u_id),count(m_id),count(s_id)");//查询自定义列
        qw.groupBy("m_id");
        qw.eq("status","true");
        //qw.orderByDesc("count(m_id)");
        //qw.having("count(*)");
        //listMaps方法查询
        LogMapper baseMapper = logService.getBaseMapper();
        List<Map<String, Object>> maps = baseMapper.selectMaps(qw);
        System.out.println("长："+maps.size());
        maps.forEach(m->System.out.println(m.size()+"--->>>"+m+m.keySet()));
    }
    @Test
    void controller1(){
        logService.AdminGetLog(new Page<Log>(1, 10), Wrappers.<Log>query().select("u_id", "s_id", "date").orderByDesc("date"))
                .getRecords().forEach(System.out::println);
    }
    @Test
    void controller2(){
        Integer count = logService.getCount(null);
        Integer date = logService.getCount(Wrappers.<Log>query().ge("date", LocalDateTime.now().minusDays(3)));
        Integer count2 = logService.getCount(Wrappers.<Log>query().eq("status", "true"));
         System.out.println(new CountThree(count,count2,date));
    }
    @Test
    void getConsole(){
        List<Integer> finalList = new ArrayList<>();
        moduleService.AdminGetModules(null, Wrappers.<CareModule>query().select("id").eq("u_id", 1)).getRecords()
                .forEach(m -> finalList.add(m.getId()));
        System.out.println(finalList);
        System.out.println(finalList.size());
        System.out.println(finalList.size()==0?0:logService.getCount(Wrappers.<Log>query().in("m_id", finalList)));
    }
}