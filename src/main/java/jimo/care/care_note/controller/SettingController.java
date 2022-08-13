package jimo.care.care_note.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jimo.care.care_note.bean.*;
import jimo.care.care_note.info.Message;
import jimo.care.care_note.info.user.UserSettingStatus;
import jimo.care.care_note.service.impl.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * SettingController前端控制器
 * </p>
 *
 * @author JIMO
 * @since 2022-08-13
 */
@RestController
public class SettingController {
    @Resource
    ModuleServiceImpl moduleService;
    @Resource
    LogServiceImpl logService;
    @Resource
    RelationServiceImpl relationService;
    @Resource
    UserServiceImpl userService;
    @Resource
    SettingServiceImpl settingService;

    /***
     * @param request
     * @return 获取全部关怀对象的信息
     */
    @PostMapping("/user/setting")
    public Message getModules(HttpServletRequest request) {
        Map<String, List<Setting>> map = new HashMap<>();
        try {
            User careUser = getCareUser(request);
            List<Setting> onSetting = new ArrayList<>();
            List<Setting> stopSetting =new ArrayList<>();
            relationService.AdminGetRelations(null,Wrappers.<Relation>query().select("s_id","m_id").eq("u_id",careUser.getId())).getRecords()
                    .forEach(r-> settingInfo(onSetting,stopSetting,r));
            map.put("onSetting",onSetting);
            map.put("stopSetting",stopSetting);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(500, "error", null);
        }
        return new Message(200, "ok", map);
    }
    @PostMapping("/user/setting/delete/{id}")
    public Message deleteSetting(@PathVariable("id")Integer id){
        boolean b = settingService.deleteBySID(id);
        return new Message(b?200:500,b?"删除成功！刷新页面生效！":"系统异常，请重试！",null);
    }
    /***
     * @param request
     * @return 用于判断这是修改或是创建！并传入参数。
     */
    @PostMapping("/user/setting/get")
    public Message getSetting(HttpServletRequest request) {
        Object sID = request.getSession().getAttribute("setting");
        User careUser = getCareUser(request);
        List<CareModule> list =new ArrayList<>();
        moduleService.UserGetModules(null,careUser.getId()).getRecords()
                .forEach(m->list.add(m));
        if (sID == null) {
            Setting setting = new Setting();
            setting.setModules(list);
            return new Message(200, "欢迎创建对象！", setting);
        } else {
            Relation relation = relationService.getRelation((int) sID);
            Setting setting = settingService.UserGetSetting((int) sID);
            setting.setModuleId(relation.getMId());
            setting.setModules(list);
            return new Message(201, "谨慎修改对象！", setting);
        }
    }
    @PostMapping("/user/setting/post")
    public Message postSetting(Setting setting, HttpServletRequest request) {
        Object sID = request.getSession().getAttribute("setting");
        if (sID == null) {
            boolean b = settingService.insert(setting)&&relationService.insert(new Relation(getCareUser(request).getId(),setting.getId(),setting.getModuleId()));
            return new Message(b ? 200 : 500, b ? "创建成功！" : "创建失败，请重试！", null);
        } else {
            setting.setId((int)sID);
            Relation relation = relationService.getRelation((int) sID);
            relation.setMId(setting.getModuleId());
            boolean b=settingService.updateBySIF(setting)&&relationService.updateByRID(relation);
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
     * 根据业务需求进行筛选处理
     */
    private void settingInfo(List<Setting> onSetting, List<Setting> stopSetting,Relation relation) {
        Setting setting = settingService.UserGetSetting(relation.getSId());
        if (setting.getStatus()>=0&&setting.getStatus()<8){
            setting.setVisit(logService.getCount(Wrappers.<Log>query().eq("s_id",relation.getSId())));
            setting.setModuleName(moduleService.getModule(relation.getMId()).getName());
            if (setting.getStatus()!=0){
                setting.setStatusName(auto(setting.getStatus()));
                onSetting.add(setting);
            }else {
                setting.setStatusName("服务已暂停");
                stopSetting.add(setting);
            }
        }
    }

    /***
     * @param s 将标识信息转化为可见的信息！
     * @return
     */
    private String auto(Integer s) {
        switch (s) {
            case 0:
                return "服务已暂停";
            case 1:
                return "早安服务";
            case 2:
                return "午安服务";
            case 3:
                return "早安-午安服务";
            case 4:
                return "晚安服务";
            case 5:
                return "早安-晚安服务";
            case 6:
                return "午安-晚安服务";
            case 7:
                return "全天服务";
        }
        return null;
    }

    /***
     * @param s 将可见的信息转化为内部标识！
     * @return
     */
    private String autoF(String s) {
        return Objects.equals(s,"系统自动智能语句！")||Objects.equals(s,"")||Objects.equals(s,null) ? UserSettingStatus.AUTO : s;
    }
}
