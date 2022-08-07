package jimo.care.care_note.module;

import jimo.care.care_note.util.APIUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/***
 * 用户验证注册、登录、找回密码、消费成功、余额不足提醒！
 */
@Component
public class UserMessage implements SendMessage{
    @Resource
    APIUtil apiUtil;
    @Value("${jimo.message.email.title}")
    private String tittle ;
    @Value("${jimo.message.email.end}")
    private String end;
    @Value("${jimo.message.email.logo}")
    private String logo;

    /***
     * @param stringList 填入必要的信息参数["email","标题内容","name","power","提示内容"]
     * @return 返回一个处理过的信息格式
     */
    @Override
    public Object text(List<String> stringList) {
        apiUtil.sendMail(stringList.get(0),stringList.get(1),
                stringList.get(2)+"-"+stringList.get(3)+"级用户"+tittle+
                        "\n"+stringList.get(4)+"\n"+end);
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
