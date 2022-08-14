package jimo.care.care_note.controller;


import jimo.care.care_note.info.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * LogController前端控制器
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
@RestController
@RequestMapping("/test")
public class LogController {
    @GetMapping("/e")
    public Message errorTest(){
       //测试异常使用 throw new RuntimeException("我是小可爱呀！我骚不骚@_@");
        return new Message(200,"Success",null);
    }
}
