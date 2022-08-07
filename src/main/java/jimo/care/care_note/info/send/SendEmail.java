package jimo.care.care_note.info.send;

import jimo.care.care_note.util.APIUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/***
 * https://blog.csdn.net/LittleBlackyoyoyo/article/details/101277268
 * https://blog.51cto.com/cxhit/3241513
 * https://gitee.com/yzh52521/think-mail
 */
@Data
@Component
public class SendEmail {
    private String email;
    private String title;
    private String message;

    @Resource
    APIUtil apiUtil;
    public void open(){
        apiUtil.sendMail(email,title,message);
    }
}
