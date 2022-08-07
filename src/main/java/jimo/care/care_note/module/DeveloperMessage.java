package jimo.care.care_note.module;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jimo.care.care_note.bean.User;
import jimo.care.care_note.service.impl.UserServiceImpl;
import jimo.care.care_note.util.APIUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/***
 * 开发者异常错误提醒。
 */
@Component
public class DeveloperMessage implements SendMessage{
    @Resource
    UserServiceImpl userService;
    @Resource
    APIUtil apiUtil;
    @Value("${jimo.message.email.title}")
    private String tittle ;
    @Value("${jimo.message.email.end}")
    private String end;
    @Value("${jimo.message.email.logo}")
    private String logo;
    /***
     * @param stringList 填入必要的信息参数["power","标题","内容"]
     * @return 返回一个处理过的信息格式
     */
    @Override
    public Object text(List<String> stringList) {
        //先查询ADMIN的信息判断权限进行对应处理。
        userService.AdminGetUsers(null, Wrappers.<User>query().select("email","name","power")
                .ge("power", Integer.parseInt(stringList.get(0)))).getRecords()
                .forEach(u->apiUtil.sendMail(u.getEmail(),stringList.get(1),
                        u.getName()+"-"+u.getPower()+"级管理员"+tittle+stringList.get(2)+"\n"+end));
        return stringList;
    }

    /***
     * @param map 存入对象信息
     * @return 返回标准消息格式
     */
    @Override
    public String test(Map<Class, Object> map) {
        return null;
    }
}
