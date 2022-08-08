package jimo.care.care_note.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.*;
import jimo.care.care_note.service.impl.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/api/get")
public class GetAPIController {
    @Resource
    RelationServiceImpl relationService;
    @Resource
    UserServiceImpl userService;
    @Resource
    SettingServiceImpl settingService;
    @Resource
    ModuleServiceImpl moduleService;
    @Resource
    LogServiceImpl logService;

    @GetMapping("/u")
    public Page<User> getUsers() {
        return userService.AdminGetUsers(null, null);
    }
    @GetMapping("/s")
    public Page<Setting> getSettings() {
        return settingService.AdminGetSettings(null,null);
    }
    @GetMapping("/l")
    public Page<Log> getLogs() {
        Page<Log> page = logService.AdminGetLog(null, null);
        return page;
    }
    @GetMapping("/r")
    public Page<Relation> getRelation() {
        Page<Relation> page = relationService.AdminGetRelations(null, null);
        return page;
    }
    @GetMapping("/m")
    public Page<CareModule> getModule() {
        Page<CareModule> page = moduleService.AdminGetModules(null, null);
        return page;
    }

}
