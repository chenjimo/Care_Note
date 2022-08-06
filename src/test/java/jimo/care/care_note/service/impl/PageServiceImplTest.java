package jimo.care.care_note.service.impl;

import jimo.care.care_note.bean.Page;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
class PageServiceImplTest {
    @Autowired
    PageServiceImpl pageService;

    @Test
    void insert() {
        //   pageService.insert(new Page());
    }

    @Test
    void deleteByPID() {
        // pageService.deleteByPID(1);
    }

    @Test
    void updateByPID() {
//pageService.updateByPID(new Page(1,7));
    }

    @Test
    void getPageList() {
        pageService.getPageList(
                        new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(1, 5)
                        , null)
                .getRecords().forEach(System.out::println);
    }

    @Test
    void addVisit() {
       // pageService.AddVisit(1);
    }
}