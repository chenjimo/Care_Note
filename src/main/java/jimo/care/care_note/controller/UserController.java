package jimo.care.care_note.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.taobao.api.ApiException;
import jimo.care.care_note.bean.CareModule;
import jimo.care.care_note.bean.Log;
import jimo.care.care_note.bean.Relation;
import jimo.care.care_note.bean.User;
import jimo.care.care_note.info.Message;
import jimo.care.care_note.module.UserMessage;
import jimo.care.care_note.module.ding.DingOrders;
import jimo.care.care_note.service.impl.LogServiceImpl;
import jimo.care.care_note.service.impl.ModuleServiceImpl;
import jimo.care.care_note.service.impl.RelationServiceImpl;
import jimo.care.care_note.service.impl.UserServiceImpl;
import jimo.care.care_note.util.CodeUtils;
import jimo.care.care_note.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * UserController前端控制器
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    LogServiceImpl logService;
    @Resource
    ModuleServiceImpl moduleService;
    @Resource
    RelationServiceImpl relationService;
    @Resource
    UserServiceImpl userService;
    @Resource
    UserMessage userMessage;
    @Resource
    DingOrders dingOrders;
    private static final String success = "恭喜您，操作成功！";
    private static final String error = "抱歉，操作失败，请重试！";

    /***
     * @param request 传入HttpServletRequest
     * @return 用户首页的Post请求信息！
     */
    @GetMapping("/console/all")
    public Message getConsole(HttpServletRequest request) {
        List<Object> objectList = new ArrayList<>();
        User careUser = getCareUser(request);
        objectList.add(careUser.getName() + "\t-\t" + careUser.getPower() + "级用户");
        objectList.add(DateUtil.localDateTimeToString(careUser.getCreateTime()));
        objectList.add(careUser.getMoney());
        objectList.add(logService.getCount(Wrappers.<Log>query().eq("u_id", careUser.getId())));
        List<Integer> finalList = new ArrayList<>();
        moduleService.AdminGetModules(null, Wrappers.<CareModule>query().select("id").eq("u_id", 1)).getRecords()
                .forEach(m -> finalList.add(m.getId()));
        objectList.add(finalList.size());
        objectList.add(finalList.size() == 0 ? 0 : logService.getCount(Wrappers.<Log>query().in("m_id", finalList)));
        List<Integer> list = new ArrayList<>();
        relationService.AdminGetRelations(null, Wrappers.<Relation>query().select("s_id").eq("u_id", careUser.getId())).getRecords()
                .forEach(r -> list.add(r.getSId()));
        objectList.add(list.size());
        objectList.add(list.size() == 0 ? 0 : logService.getCount(Wrappers.<Log>query().in("s_id", list)));
        return new Message(200, success, objectList);
    }

    /***
     * @param request
     * @return 获取已经登录的用户信息
     */
    @GetMapping("/message")
    public Message getCareUserMessage(HttpServletRequest request) {
        return new Message(200, success, getCareUser(request));
    }

    /***
     * @param u newUser
     * @return 修改结果
     */
    @PostMapping("/update")
    public Message updateCareUser(User u, @RequestParam("code")String code, HttpServletRequest request) {
        User careUser = getCareUser(request);
        String senCode = (String)request.getSession().getAttribute(careUser.getEmail());
        if (senCode==null){
            return new Message(404,"该邮箱暂未发送验证码！", null);
        }else if (!senCode.equals(code)){
            return new Message(400,"验证码不匹配！", null);
        }else {
            u.setId(careUser.getId());
            boolean b = userService.updateByUID(u);
            return new Message(b ? 200 : 500, b ? success : error, null);
        }
    }

    /***
     * @return 用于身份验证的消息！！！
     */
    @PostMapping("/send")
    public Message sendEmail(HttpServletRequest request) {
        try {
            User careUser = getCareUser(request);
            String code = CodeUtils.getCode();
            request.getSession().setAttribute(careUser.getEmail(),code);
            List<String> list = new ArrayList<>();
            list.add(careUser.getEmail());
            list.add("Care-Note:信息修改验证");
            list.add(careUser.getName());
            list.add(String.valueOf(careUser.getPower()));
            list.add("【Care-Note】验证码：" + "<h4 style='color: blue'>"+ code +"</h4>" + " 您正在修改账户信息，为保证您的账户安全，请勿向任何人提供此验证码。如非本人操作，请忽略本邮件." +
                    "<h4 style='color: red'><a href='http://jimo.fun/'> 欢迎您访问JIMO:http://jimo.fun/ </a></h4>" +
                    "<a href='https://wpa.qq.com/msgrd?v=3&uin=1517962688&site=qq&menu=yes' target=\"_blank\" title=\"QQ:1517962688\">任何疑问请联系管理员：QQ1517962688</a>" +
                    "<a href=\"mailto:jimoworld@qq.com\" target=\"_blank\" title=\"邮箱：jimoworld@qq.com\" >或向我们发送：email</a>");
            userMessage.text(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(500,error,null);
        }
        return new Message(200,success,null);
    }

    /***
     * 信息参数[name,ID,Money,套餐，留言，User]
     * @return 暂时未接入支付平台的接口，通过提醒手动处理手动处理。
     */
    @PostMapping("/money")
    public Message addMoney(@RequestParam("msg")String msg,@RequestParam("money")String money,HttpServletRequest request) {
        try {
            User careUser = getCareUser(request);
            List<String> list =new ArrayList<>();
            list.add(careUser.getName()+"<->"+careUser.getPower()+"级用户");
            list.add(String.valueOf(careUser.getId()));
            list.add(String.valueOf(careUser.getMoney()));
            list.add(money);
            list.add(msg);
            list.add(careUser.toString());
            dingOrders.text(list);
        } catch (ApiException e) {
            e.printStackTrace();
            return new Message(500,error,null);
        }
        return new Message(200,success,null);
    }


    /***
     * @param request 传入HttpServiceRequest
     * @return 取出session中的CareUser信息
     */
    private User getCareUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("CareUser");
    }
}
