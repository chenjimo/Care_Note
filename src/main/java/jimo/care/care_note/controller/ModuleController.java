package jimo.care.care_note.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jimo.care.care_note.bean.CareModule;
import jimo.care.care_note.bean.Log;
import jimo.care.care_note.bean.Relation;
import jimo.care.care_note.bean.User;
import jimo.care.care_note.info.Message;
import jimo.care.care_note.info.user.UserSettingStatus;
import jimo.care.care_note.service.impl.LogServiceImpl;
import jimo.care.care_note.service.impl.ModuleServiceImpl;
import jimo.care.care_note.service.impl.RelationServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * ModuleController前端控制器
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
@RestController
public class ModuleController {
    @Resource
    ModuleServiceImpl moduleService;
    @Resource
    LogServiceImpl logService;
    @Resource
    RelationServiceImpl relationService;

    @PostMapping("/user/module")
    public Message getModules(HttpServletRequest request) {
        Map<String, List<CareModule>> map = null;
        try {
            User careUser = getCareUser(request);
            List<CareModule> publicMoules = new ArrayList<>();
            List<CareModule> privateMoules = new ArrayList<>();
            moduleService.AdminGetModules(null, Wrappers.<CareModule>query().eq("u_id", -1).or().eq("u_id", -2).or().eq("u_id", 0)).getRecords()
                    .forEach(m -> moduleInfo(publicMoules, m));
            moduleService.AdminGetModules(null, Wrappers.<CareModule>query().eq("u_id", careUser.getId())).getRecords()
                    .forEach(m -> moduleInfo(privateMoules, m));
            map = new HashMap<>();
            map.put("publicMoules", publicMoules);
            map.put("privateMoules", privateMoules);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(500, "error", null);
        }
        return new Message(200, "ok", map);
    }

    /***
     * @param id ModuleID
     * @return 删除结果！
     */
    @PostMapping("/user/module/delete/{id}")
    public Message deleteModule(@PathVariable("id") Integer id, HttpServletRequest request) {
        Integer count1 = relationService.getCount(Wrappers.<Relation>query().eq("m_id", id));
        if (count1 > 0) {
            return new Message(400, "系统拒绝了您的请求！此模板已经绑定了" + count1 + "个对象", null);
        } else {
            Integer count2 = logService.getCount(Wrappers.<Log>query().eq("m_id", id));
            if (count2 > 0) {
                return new Message(400, "系统拒绝了您的请求！此模板已经发送了" + count2 + "个消息！", null);
            } else {
                boolean b = moduleService.UserDeleteModule(id, getCareUser(request).getId());
                return new Message(b ? 200 : 500, b ? "删除成功！刷新页面生效！" : "拒绝删除，权限不足可联系管理员！", null);
            }
        }
    }

    /***
     * @param request
     * @return 用于判断这是修改或是创建！并传入参数。
     */
    @PostMapping("/user/module/get")
    public Message getModule(HttpServletRequest request) {
        Object mID = request.getSession().getAttribute("module");
        if (mID == null) {
            User careUser = getCareUser(request);
            return new Message(200, "欢迎创建模板！", careUser.getId());
        } else {
            CareModule module = moduleService.getModule((int) mID);
            module.setStatus(auto(module.getStatus()));
            module.setTemp(auto(module.getTemp()));
            module.setMorning(auto(module.getMorning()));
            module.setNoon(auto(module.getNoon()));
            module.setEvening(auto(module.getEvening()));
            return new Message(201, "谨慎修改模板！", module);
        }
    }

    @PostMapping("/user/mondule/post")
    public Message postModule(CareModule m, HttpServletRequest request) {
        Object mID = request.getSession().getAttribute("module");
        if (mID == null) {
           //遇见u_id无可解药再使用 m.setUId(m.getUId()==0||m.getUId()==-1||m.getUId()==-1?m.getId():getCareUser(request).getId());
            m.setStatus(autoF(m.getStatus()));
            m.setTemp(autoF(m.getTemp()));
            m.setMorning(autoF(m.getMorning()));
            m.setNoon(autoF(m.getNoon()));
            m.setEvening(autoF(m.getEvening()));
            boolean b = moduleService.insert(m);
            return new Message(b ? 200 : 500, b ? "创建成功！" : "创建失败，请重试！", null);
        } else {
            m.setId((int) mID);
            m.setStatus(autoF(m.getStatus()));
            m.setTemp(autoF(m.getTemp()));
            m.setMorning(autoF(m.getMorning()));
            m.setNoon(autoF(m.getNoon()));
            m.setEvening(autoF(m.getEvening()));
            boolean b = moduleService.updateById(m);
            return new Message(b ? 200 : 500, b ? "修改成功！" : "修改失败，请重试！", null);
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
     * 共同方法进行处理数据
     */
    private void moduleInfo(List<CareModule> list, CareModule module) {
        module.setVisit(logService.getCount(Wrappers.<Log>query().eq("m_id", module.getId())));
        module.setStatus(auto(module.getStatus()));
        module.setTemp(auto(module.getTemp()));
        module.setMorning(auto(module.getMorning()));
        module.setNoon(auto(module.getNoon()));
        module.setEvening(auto(module.getEvening()));
        list.add(module);
    }

    /***
     * @param s 将标识信息转化为可见的信息！
     * @return
     */
    private String auto(String s) {
        return Objects.equals(s, UserSettingStatus.AUTO) ? "系统自动智能语句！" : s;
    }

    /***
     * @param s 将可见的信息转化为内部标识！
     * @return
     */
    private String autoF(String s) {
        return Objects.equals(s,"系统自动智能语句！")||Objects.equals(s,"")||Objects.equals(s,null) ? UserSettingStatus.AUTO : s;
    }
}
