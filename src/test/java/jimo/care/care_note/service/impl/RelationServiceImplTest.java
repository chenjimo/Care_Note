package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Relation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

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
class RelationServiceImplTest {
    @Resource
    RelationServiceImpl relationService;

    @Test
    void insert() {
        //  relationService.insert(new Relation(2,1,null));
    }

    @Test
    void deleteByRID() {
        //relationService.deleteByRID(2);
    }

    @Test
    void updateByRID() {
       /* Relation relation = new Relation(null,null,-2);
        relation.setId(3);
        relationService.updateByRID(relation);*/
    }

    @Test
    void userGetRelations() {
        relationService.UserGetRelations(new Page<>(1,5),1)
                .getRecords().forEach(System.out::println);
    }

    @Test
    void adminGetRelations() {
        relationService.AdminGetRelations(new Page<>(1,5),null)
                .getRecords().forEach(System.out::println);
    }
}