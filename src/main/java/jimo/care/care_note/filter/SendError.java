package jimo.care.care_note.filter;

import com.taobao.api.ApiException;
import jimo.care.care_note.info.user.UserPower;
import jimo.care.care_note.module.DeveloperMessage;
import jimo.care.care_note.module.ding.DingError;
import jimo.care.care_note.util.CodeUtils;
import jimo.care.care_note.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 自定义异常处理服务实现类，
 * 全局检测系统异常提醒6级以上管理员（开发员级别）。
 * 有待优化细分的一场领域提示！（需细粒度化再处理！）
 * </p>
 *
 * @author JIMO
 * @since 2022-08-08
 */
@Slf4j
@ControllerAdvice
@ResponseBody
@Component
public class SendError{
    @Resource
    DeveloperMessage developerMessage;
    @Resource
    DingError dingError;
    @Value("${jimo.alter.ding}")
    boolean dingAlter;
    @Value("${jimo.alter.email}")
    boolean emailAlter;
    /**
     * //表示让Spring捕获到所有抛出的SignException异常，并交由这个被注解的方法处理。
     * //表示设置状态码
     * 有待细分优化！！！！
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    String handleException(Exception e){
        String time = DateUtil.localDateTimeToString(LocalDateTime.now());
        String message = e.getMessage();
        String localizedMessage = e.getLocalizedMessage();
        Logger loggerFactory = LoggerFactory.getLogger(SendError.class);
        String code = CodeUtils.getCode();
        loggerFactory.error("Time:{} Exception:{}",time,message);
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        if (dingAlter){
            dingAlter(message,localizedMessage,time,code);
        }
        if (emailAlter){
            emailAlter(time,message,code);
        }
        return e.toString();
    }

    /***
     * 钉钉运维异常监测提醒！！！
     * @param message 错误信息
     * @param localizedMessage 错误信息
     * @param time 时间
     * @param code 防重码
     */
    private void dingAlter(String message,String localizedMessage,String time,String code){
        List<String> list = new ArrayList<>();
        list.add(message);
        list.add(localizedMessage);
        list.add(time);
        list.add(code);
        try {
            dingError.text(list);
        } catch (ApiException ex) {
            ex.printStackTrace();
        }
    }

    /***
     * 管理员运维异常监测提醒！！！
     * @param time 时间
     * @param message 错误信息
     * @param code 防重码
     */
    private void emailAlter(String time,String message,String code){
        List<String> stringList =new ArrayList<>();
        stringList.add(String.valueOf(UserPower.ADMIN_ALERT));
        stringList.add("CARE_NOTE_ERROR:系统级异常");
        stringList.add("Time:"+time +"\n\t<br><br>message:"+message
                +"\n\t<br><br>详细信息已发到钉钉维运群中:localizedMessage-请前往查看！"+
                "\n\n<br>本次异常防重随机码："+code+"\n\n\t");
        developerMessage.text(stringList);
    }
}
