package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Setting;
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
class SettingServiceImplTest {
@Autowired
SettingServiceImpl settingService;
    @Test
    void insert() {
//       settingService.insert(new Setting("Q","上海","17761612832",1,1));
//        settingService.insert(new Setting("XIXI","南阳","18621127661",4,0));
    }

    @Test
    void deleteBySID() {
      //  settingService.deleteBySID(5);
    }

    @Test
    void updateBySIF() {
     // settingService.updateBySIF(new Setting(4,5));
    }

    @Test
    void userGetSetting() {
        Setting setting = settingService.UserGetSetting(2);
        System.out.println(setting);
    }

    @Test
    void adminGetSettings() {
        settingService.AdminGetSettings(new Page<>(1,5), null)
                .getRecords().forEach(System.out::println);
    }

    @Test
    void getSettingCount() {
        Integer settingCount = settingService.getSettingCount(null);
        System.out.println(settingCount);
    }
}