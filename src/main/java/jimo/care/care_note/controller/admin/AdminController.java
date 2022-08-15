package jimo.care.care_note.controller.admin;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.taobao.api.ApiException;
import jimo.care.care_note.bean.*;
import jimo.care.care_note.info.Message;
import jimo.care.care_note.info.ResultData;
import jimo.care.care_note.info.user.UserPower;
import jimo.care.care_note.module.UserMessage;
import jimo.care.care_note.module.ding.DingOrders;
import jimo.care.care_note.service.impl.*;
import jimo.care.care_note.util.DateUtil;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * AdminController前端控制器
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    PageServiceImpl pageService;
    @Resource
    LogServiceImpl logService;
    @Resource
    ModuleServiceImpl moduleService;
    @Resource
    RelationServiceImpl relationService;
    @Resource
    UserServiceImpl userService;
    @Resource
    SettingServiceImpl settingService;
    @Resource
    UserMessage userMessage;
    @Resource
    DingOrders dingOrders;
    private static final String success = "恭喜您，操作成功！";
    private static final String error = "抱歉，操作失败，请重试！";

    @GetMapping("/menu")
    public Message getMenu(HttpServletRequest request) {
        User careUser = getCareUser(request);
        Integer power = careUser.getPower();
        try {
            List<Page> list = new ArrayList<>(pageService.getPageList(null, Wrappers.<Page>query().select("url", "name").ge("power", UserPower.UPDATE).le("power", power)).getRecords());
            careUser.setPages(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(500, "我尊敬的管理员，欢迎您来管控！<br>刷新失败还是您来修修吧😔,您辛苦了！", null);
        }
        return new Message(200, "我尊敬的:" + power + "级管理员-" + careUser.getName() + "，欢迎您来管控！<br>小的(*^_^*)帮您把页面整理好啦,您辛苦了！", careUser);
    }

    /***
     * 下面的映射方法均为列表查询展示！
     */
    @GetMapping("/users")
    public ResultData<User> getUsers() {
        List<User> users = new ArrayList<>();
        userService.AdminGetUsers(null, Wrappers.<User>query().le("power", UserPower.USER_ALERT)).getRecords()
                .forEach(u -> {
                    u.setCt(DateUtil.localDateTimeToString(u.getCreateTime()));
                    u.setLt(DateUtil.localDateTimeToString(u.getLoginTime()));
                    users.add(u);
                });
        return new ResultData<>(users, users.size());
    }

    @GetMapping("/admins")
    public ResultData<User> getAdmins() {
        List<User> users = new ArrayList<>();
        userService.AdminGetUsers(null, Wrappers.<User>query().ge("power", UserPower.UPDATE)).getRecords()
                .forEach(u -> {
                    u.setCt(DateUtil.localDateTimeToString(u.getCreateTime()));
                    u.setLt(DateUtil.localDateTimeToString(u.getLoginTime()));
                    users.add(u);
                });
        return new ResultData<>(users, users.size());
    }

    @GetMapping("/settings")
    public ResultData<Setting> getSetting() {
        List<Setting> settings = new ArrayList<>();
        settingService.AdminGetSettings(null, null).getRecords()
                .forEach(s -> {
                    s.setVisit(logService.getCount(Wrappers.<Log>query().eq("s_id", s.getId())));
                    settings.add(s);
                });
        return new ResultData<>(settings, settings.size());
    }

    @GetMapping("/modules")
    public ResultData<CareModule> getModules() {
        List<CareModule> careModules = new ArrayList<>();
        moduleService.AdminGetModules(null, null).getRecords()
                .forEach(m -> {
                    m.setVisit(logService.getCount(Wrappers.<Log>query().eq("m_id", m.getId())));
                    careModules.add(m);
                });
        return new ResultData<>(careModules, careModules.size());
    }

    @GetMapping("/pages")
    public ResultData<Page> getPages() {
        List<Page> pages = new ArrayList<>(pageService.getPageList(null, null).getRecords());
        return new ResultData<>(pages, pages.size());
    }

    @GetMapping("/logs")
    public ResultData<jimo.care.care_note.bean.Log> getLogs() {
        List<jimo.care.care_note.bean.Log> logs = new ArrayList<>();
        logService.AdminGetLog(null, null).getRecords()
                .forEach(l -> {
                    l.setTime(DateUtil.localDateTimeToString(l.getDate()));
                    logs.add(l);
                });
        return new ResultData<>(logs, logs.size());
    }

    @GetMapping("/relations")
    public ResultData<Relation> getRelations() {
        List<Relation> relations = new ArrayList<>();
        relationService.AdminGetRelations(null, null).getRecords()
                .forEach(r -> {
                    User u = userService.getUserByUID(r.getUId());
                    Setting s = settingService.UserGetSetting(r.getSId());
                    CareModule m = moduleService.getModule(r.getMId());
                    r.setUserName(nameID(u.getName(), u.getId()));
                    r.setSettingName(nameID(s.getName(), s.getId()));
                    r.setModuleName(nameID(m.getName(), m.getId()));
                    relations.add(r);
                });
        return new ResultData<>(relations, relations.size());
    }

    /***
     * 下面的映射方法均用于处理管理层面的内容！！！
     */
    @GetMapping("/user")
    public Message getUser(User u, HttpServletRequest request) {
        User user = userService.UserGetUser(u.getEmail());
        User careUser = getCareUser(request);
        if (user == null || user.getPower() >= careUser.getPower()) {
            return new Message(400, "查无此人，或您的权限不足无法!<br>有疑问请联系开发人员！", null);
        }
        user = userService.getUserByUID(user.getId());
        return new Message(200, "查询成功，请您谨慎修改！", user);
    }

    @PostMapping("/user")
    public Message updateUser(User u) {
        boolean b = userService.updateByUID(u);
        return new Message(b ? 200 : 500, b ? success : error, null);
    }

    @GetMapping("/page")
    public Message getPage(Page page, HttpServletRequest request) {
        User careUser = getCareUser(request);
        Page pageByUrl = pageService.getPageByUrl(page.getUrl());
        if (pageByUrl == null || pageByUrl.getPower() > careUser.getPower()) {
            return new Message(400, "查无此页面，或您的权限不足无法!<br>有疑问请联系开发人员！", null);
        }
        return new Message(200, "查询成功，请您谨慎修改！", pageByUrl);
    }
    @PostMapping("/page")
    public Message updatePage(Page page){
        boolean b = pageService.updateByPID(page);
        return new Message(b ? 200 : 500, b ? success : error, null);
    }
    @GetMapping("/module")
    public Message getModule(CareModule module, HttpServletRequest request) {
        User careUser = getCareUser(request);
        CareModule module1 = moduleService.getModule(module.getName());
        if (module1 == null || careUser.getPower()<UserPower.ADMIN) {
            return new Message(400, "查无此页面，或您的权限不足无法!<br>有疑问请联系开发人员！", null);
        }
        return new Message(200, "查询成功，请您谨慎修改！", module1);
    }
    @PostMapping("/module")
    public Message updateModule(CareModule module){
        if (module.getUId()<-2){
            Integer u_id = relationService.getCount(Wrappers.<Relation>query().eq("u_id", module.getId()));
            if (u_id>0){
                return new Message(400, "无法删除或停用！，此模板已经绑定"+u_id+"个消息!<br>有疑问请联系开发人员！", null);
            }
        }
        boolean b = moduleService.AdminUpdateModule(module);
        return new Message(b ? 200 : 500, b ? success : error, null);
    }
    @PostMapping("/money")
    public Message addMoney(User u, @RequestParam("msg") String msg, HttpServletRequest request) {
        User user = userService.UserGetUser(u.getEmail());
        if (user == null || user.getPower() < 1) {
            return new Message(404, "此邮箱用户，不存在或已删除！", null);
        } else {
            User user1 = new User();
            Integer money = user.getMoney() + u.getMoney();
            user1.setMoney(money);
            user1.setId(user.getId());
            boolean b = userService.updateByUID(user1);
            if (b) {//充值业务提醒！
                List<String> list = new ArrayList<>();
                String s = DateUtil.localDateTimeToString(LocalDateTime.now());
                list.add(user.getEmail());
                list.add("Care-Note:充值成功提醒");
                list.add(user.getName());
                list.add(String.valueOf(user.getPower()));
                list.add("您的充值已成功！本次充值次数为:" + u.getMoney() + "次<br><br>" +
                        "充值后您的余额还有👉：" + money + "<br><br>感谢您的支持，祝您生活愉快！" +
                        "轮班充值的管理员为您留言：" + msg + "" +
                        "<br><br>操作时间为:" + s);
                userMessage.text(list);
                List<String> stringList = new ArrayList<>();
                User careUser = getCareUser(request);
                stringList.add(careUser.getName());
                stringList.add(String.valueOf(careUser.getId()));
                stringList.add(String.valueOf(u.getMoney()));
                stringList.add(s);
                stringList.add(user.getName());
                stringList.add(msg);
                stringList.add(user.toString());
                try {
                    dingOrders.orderOver(stringList);
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
            return new Message(b ? 200 : 500, b ? success : error, null);
        }
    }

    /***
     * @param request 传入HttpServiceRequest
     * @return 取出session中的CareUser信息
     */
    private User getCareUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("CareUser");
    }

    /***
     * @param name 名字
     * @param id ID
     * @return 方便看到的字符串
     */
    private String nameID(String name, Integer id) {
        return name + "(ID:" + id + ")";
    }
}
