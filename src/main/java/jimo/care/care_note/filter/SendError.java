package jimo.care.care_note.filter;

import jimo.care.care_note.util.APIUtil;
import jimo.care.care_note.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author crush
 * @ControllerAdvice
 * @ResponseBody //表示返回的对象，Spring会自动把该对象进行json转化，最后写入到Response中。
 */

//@Slf4j
//@RestControllerAdvice
//@ResponseBody
public class SendError extends APIUtil{
//    @Value("${spring.mail.username}")
//    private String from;
//
//    /**
//     * //表示让Spring捕获到所有抛出的SignException异常，并交由这个被注解的方法处理。
//     * //表示设置状态码
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    String handleException(Exception e){
//        String time = DateUtil.localDateTimeToString(LocalDateTime.now());
//        String message = e.getMessage();
//        String localizedMessage = e.getLocalizedMessage();
//        log.error("Time:{} Exception:{}",time,message);
//     sendMail(from,"CARE_NOTE_ERROR", "Time:"+time +"\n"+e.toString());
//     return e.toString();
//    }
}
