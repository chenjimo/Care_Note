package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.User;
import jimo.care.care_note.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
class UserServiceImplTest {
@Autowired
UserServiceImpl userService;
    @Test
    void insert() {
        userService.insert(new User(null,"123456","745076951@qq.com",null,"18621127661",null,null,null));
    }

    @Test
    void deleteByUID() {
      //  userService.deleteByUID(4);
    }

    @Test
    void updateByUID() {
       // userService.updateByUID(new User(4,5));
    }

    @Test
    void adminGetUsers() {
        userService.AdminGetUsers(new Page<>(1,5),null)
                .getRecords().forEach(System.out::println);
    }

    @Test
    void userGetUsers() {
        User user = userService.UserGetUser("1517962688@qq.com");
        System.out.println(user);
        UserMapper baseMapper = userService.getBaseMapper();
        baseMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getEmail,"1517962688@qq.com"))
                .forEach(System.out::println);
    }

    @Test
    void getCount() {
        Integer count = userService.getCount(null);
        System.out.println(count);
    }

}