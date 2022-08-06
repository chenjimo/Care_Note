package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Module;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
class ModuleServiceImplTest {
@Resource
ModuleServiceImpl moduleService;
    @Test
    void insert() {
     // moduleService.insert(new Module());
    }

    @Test
    void userDeleteModule() {
     //   moduleService.UserDeleteModule(2,1);
    }

    @Test
    void adminDeleteModule() {
       // moduleService.AdminDeleteModule(3);
    }

    @Test
    void userUpdateModule() {
      //  moduleService.UserUpdateModule(new Module(4,0));
    }

    @Test
    void adminUpdateModule() {
      //  moduleService.AdminUpdateModule(new Module(4,1));
    }

    @Test
    void userGetModules() {
        moduleService.UserGetModules(new Page<>(1,5),0)
                .getRecords().forEach(System.out::println);
    }

    @Test
    void adminGetModules() {
        moduleService.AdminGetModules(new Page<>(1,5),new QueryWrapper<>().like("name","JIMO"))
                .getRecords().forEach(System.out::println);
    }

    @Test
    void getModule() {
        Module module = moduleService.getModule(1);
        System.out.println(module);
    }

    @Test
    void getModuleCount() {
        Integer moduleCount = moduleService.getModuleCount(null);
        System.out.println(moduleCount);
    }
}