package jimo.care.care_note.controller;

import jimo.care.care_note.bean.User;
import jimo.care.care_note.info.Message;
import jimo.care.care_note.info.user.UserPower;
import jimo.care.care_note.module.UserMessage;
import jimo.care.care_note.service.impl.UserServiceImpl;
import jimo.care.care_note.util.CodeUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/***
 * 用于登录和注册方法的映射
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private static final String success = "恭喜您，操作成功！";
    private static final String error = "抱歉，操作失败，请重试！";
    @Resource
    UserServiceImpl userService;
    @Resource
    UserMessage userMessage;

    /***
     * @param u 用户信息登录验证
     * @param request HttpServletRequest
     * @return Message
     */
    @PostMapping("/user")
    public Message loginUser(User u, HttpServletRequest request) {
        User user = userService.UserGetUser(u.getEmail());
        if (user!=null&&Objects.equals(user.getPwd(), u.getPwd()) && user.getPower() >= UserPower.USER) {
            request.getSession().setAttribute("CareUser", user);
            return new Message(200, success, null);
        } else {
            return new Message(400,error , null);
        }
    }

    /***
     * @param u 管理员信息登录验证
     * @param request HttpServletRequest
     * @return Message
     */
    @PostMapping("/admin")
    public Message loginAdmin(User u, HttpServletRequest request) {
        User user = userService.UserGetUser(u.getEmail());
        if (user!=null&&Objects.equals(user.getPwd(), u.getPwd()) && user.getPower() >= UserPower.UPDATE) {
            request.getSession().setAttribute("CareUser", user);
            return new Message(200, success, null);
        } else {
            return new Message(400, error, null);
        }
    }

    /***
     * @param request 退出登录的session清楚
     * @return Message
     */
    @GetMapping("/out")
    public Message loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
            return new Message(200, success, null);
    }

    /***
     * @param u 注册信息
     * @return Message
     */
    @PostMapping("/post")
    public Message postUser(User u,@RequestParam("code")String code,HttpServletRequest request){
        User user = userService.UserGetUser(u.getEmail());
        boolean b;
        String senCode = (String) request.getSession().getAttribute(u.getEmail());
        if (senCode==null){
            return new Message(404,"此邮箱未发送验证码，请重新验证！",null);
        }else if (!senCode.equals(code)){
            return new Message(400,"您的验证码不正确请检查输入！",null);
        }
        if (user==null){
            b = userService.insert(u);
        }else {
            u.setId(user.getId());
            b = userService.updateByUID(u);
        }
        return new Message(b?200:500,b?success:error,null);
    }
    /***
     * @return 用于验证码的发送
     */
    @PostMapping("/send")
    public Message sendCode(@RequestParam("email")String email,HttpServletRequest request){
        List<String> list =new ArrayList<>();
        list.add(email);
        list.add("Care-Note：用户验证");
        User user = userService.UserGetUser(email);
        String code = CodeUtils.getCode();
        request.getSession().setAttribute(email,code);
        if (user==null){
            list.add("新用户");
            list.add("0");
            list.add("【Care-Note】验证码：" + "<h4 style='color: blue'>"+ code +"</h4>" + " 您正在注册账户，为保证您的账户安全，请勿向任何人提供此验证码。如非本人操作，请忽略本邮件." +
                    "<h4 style='color: red'><a href='http://jimo.fun/'> 欢迎您访问JIMO:http://jimo.fun/ </a></h4>" +
                    "<a href='https://wpa.qq.com/msgrd?v=3&uin=1517962688&site=qq&menu=yes' target=\"_blank\" title=\"QQ:1517962688\">任何疑问请联系管理员：QQ1517962688</a>" +
                    "<a href=\"mailto:jimoworld@qq.com\" target=\"_blank\" title=\"邮箱：jimoworld@qq.com\" >或向我们发送：email</a>");
            userMessage.text(list);
        }else {
            list.add(user.getName());
            list.add(String.valueOf(user.getPower()));
            list.add("【Care-Note】验证码：" + "<h4 style='color: blue'>"+ code +"</h4>" + " 您正在重置密码，为保证您的账户安全，请勿向任何人提供此验证码。如非本人操作，请忽略本邮件." +
                    "<h4 style='color: red'><a href='http://jimo.fun/'> 欢迎您访问JIMO:http://jimo.fun/</a></h4>" +
                    "<a href='https://wpa.qq.com/msgrd?v=3&uin=1517962688&site=qq&menu=yes' target=\"_blank\" title=\"QQ:1517962688\">任何疑问请联系管理员：QQ1517962688</a>" +
                    "<a href=\"mailto:jimoworld@qq.com\" target=\"_blank\" title=\"邮箱：jimoworld@qq.com\" >或向我们发送：email</a>");
            userMessage.text(list);
        }
        return new Message(200,success,null);
    }


}
