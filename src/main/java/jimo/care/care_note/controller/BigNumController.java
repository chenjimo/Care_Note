package jimo.care.care_note.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.CareModule;
import jimo.care.care_note.bean.Log;
import jimo.care.care_note.bean.Setting;
import jimo.care.care_note.bean.User;
import jimo.care.care_note.info.log.ChangeCountData;
import jimo.care.care_note.info.log.CountThree;
import jimo.care.care_note.info.log.LocalInfo;
import jimo.care.care_note.info.log.UserSettingDate;
import jimo.care.care_note.service.impl.*;
import jimo.care.care_note.util.DateUtil;
import jimo.care.care_note.util.LimitUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * BigNumController前端控制器
 * 主要用于处理大数据的页面的API
 * </p>
 *
 * @author JIMO
 * @since 2022-08-010
 */
@RestController
@RequestMapping("/num")
public class BigNumController {
    @Resource
    SettingServiceImpl settingService;
    @Resource
    LogServiceImpl logService;
    @Resource
    UserServiceImpl userService;
    @Resource
    ModuleServiceImpl moduleService;
    @Resource
    PageServiceImpl pageService;
    @Resource
    LimitUtil limitUtil;

    /***
     * @return 获取最新的十条Log数据
     * (此处的数据查询还可以在优化的，因懒暂放)
     */
    @GetMapping("/log/logs")
    public List<UserSettingDate> getLogs() {
        List<UserSettingDate> dateList = new ArrayList<>();
        Page<Log> logPage = logService.AdminGetLog(new Page<>(1, 10),
                Wrappers.<Log>query().select("u_id", "s_id", "date").orderByDesc("date"));
        logPage.getRecords().forEach(log ->
                dateList.add(new UserSettingDate(limitUtil.encode(userService.getUserByUID(log.getUId()).getName()),
                        limitUtil.encode(settingService.UserGetSetting(log.getSId()).getName()),
                        DateUtil.localDateTimeToString(log.getDate())))
        );
        return dateList;
    }

    /***
     * @return 获取全部细信息的类型条数信息
     */
    @GetMapping("/log/counts")
    public CountThree getTotalCount() {
        Integer count = logService.getCount(null);
        Integer date = logService.getCount(Wrappers.<Log>query().ge("date", LocalDateTime.now().minusDays(3)));
        Integer count2 = logService.getCount(Wrappers.<Log>query().eq("status", "true"));
        return new CountThree(count, date, count2);
    }

    /***
     * @return 获取动态的三组数据的动态值，为下边的方法积分或微分
     */
    @GetMapping("/changes")
    public List<ChangeCountData> getChange() {
        List<ChangeCountData> dataList = new ArrayList<>();
        List<Map<String, Integer>> list1 = new ArrayList<>();
        List<Map<String, Integer>> list2 = new ArrayList<>();
        List<Map<String, Integer>> list3 = new ArrayList<>();
        AtomicInteger sun = new AtomicInteger();
        userService.AdminGetUsers(null, Wrappers.<User>query().select("money")).getRecords().forEach(user -> sun.set(sun.get() + user.getMoney()));
        for (int i = 0; i < 7; i++) {
            Integer create_time = userService.getCount(Wrappers.<User>query().le("create_time", LocalDateTime.now().minusDays(i)));
            Integer count = userService.getCount(Wrappers.<User>query().le("login_time", LocalDateTime.now().minusDays(i)).ge("login_time", LocalDateTime.now().minusDays(i + 3)));
            Map<String, Integer> map1 = new HashMap<>();
            map1.put("count", create_time);
            list1.add(map1);
            map1 = new HashMap<>();
            map1.put("count", count);
            list2.add(map1);
            map1 = new HashMap<>();
            map1.put("count", sun.get());
            list3.add(map1);
        }
        ChangeCountData data1 = new ChangeCountData(list1, list2, list3);
        dataList.add(data1);
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        Integer moduleCount = moduleService.getModuleCount(null);
        QueryWrapper<CareModule> eq = Wrappers.<CareModule>query().eq("u_id", -1).or().eq("u_id", 0).or().eq("u_id", -2);
        Integer moduleCount1 = moduleService.getModuleCount(eq);
        List<Integer> list = new ArrayList<>();
        moduleService.AdminGetModules(null, eq.select("id")).getRecords().forEach(m -> list.add(m.getId()));
        for (int i = 0; i < 7; i++) {
            Integer moduleCount3 = logService.getCount(Wrappers.<Log>query().in("m_id", list).le("date", LocalDateTime.now().minusDays(i)));
            Map<String, Integer> map1 = new HashMap<>();
            map1.put("count", moduleCount);
            list1.add(map1);
            map1 = new HashMap<>();
            map1.put("count", moduleCount1);
            list2.add(map1);
            map1 = new HashMap<>();
            map1.put("count", moduleCount3);
            list3.add(map1);
        }
        dataList.add(new ChangeCountData(list1, list2, list3));
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        Integer settingCount = settingService.getSettingCount(null);
        Integer logServiceCount = logService.getCount(Wrappers.<Log>query().ge("date", LocalDateTime.now().minusDays(3)));
        for (int i = 0; i < 7; i++) {
            Integer date = logService.getCount(Wrappers.<Log>query().le("date", LocalDateTime.now().minusDays(i)));
            Map<String, Integer> map1 = new HashMap<>();
            map1.put("count", settingCount);
            list1.add(map1);
            map1 = new HashMap<>();
            map1.put("count", logServiceCount);
            list2.add(map1);
            map1 = new HashMap<>();
            map1.put("count", date);
            list3.add(map1);
        }
        dataList.add(new ChangeCountData(list1, list2, list3));
        return dataList;
    }

    /***
     * @return 获取动态的三组数据
     * 用户的：总数、最近三天活跃数、可请求次数
     * 模板的：总数、公开数、共享数
     * 被关怀者的、总数、最近一周的关怀数
     */
    @GetMapping("/all/count")
    public List<CountThree> getThree() {
        List<CountThree> threeList = new ArrayList<>();
        Integer count = userService.getCount(null);
        Integer login_time = userService.getCount(Wrappers.<User>query().ge("login_time", LocalDateTime.now().minusDays(3)));
        AtomicInteger sun = new AtomicInteger();
        userService.AdminGetUsers(null, Wrappers.<User>query().select("money")).getRecords().forEach(user -> sun.set(sun.get() + user.getMoney()));
        threeList.add(new CountThree(count, login_time, sun.get()));
        Integer moduleCount = moduleService.getModuleCount(null);
        QueryWrapper<CareModule> eq = Wrappers.<CareModule>query().eq("u_id", -1).or().eq("u_id", 0).or().eq("u_id", -2);
        Integer moduleCount1 = moduleService.getModuleCount(eq);
        List<Integer> list = new ArrayList<>();
        moduleService.AdminGetModules(null, eq.select("id")).getRecords().forEach(m -> list.add(m.getId()));
        Integer moduleCount3 = logService.getCount(Wrappers.<Log>query().in("m_id", list));
        threeList.add(new CountThree(moduleCount, moduleCount1, moduleCount3));
        Integer settingCount = settingService.getSettingCount(null);
        Integer date = logService.getCount(Wrappers.<Log>query().ge("date", LocalDateTime.now().minusDays(3)));
        Integer count1 = logService.getCount(null);
        threeList.add(new CountThree(settingCount, date, count1));
        return threeList;
    }

    /***
     * @return 按时间总数、成功数、更新量进行处理Log的数据
     */
    @GetMapping("/all/log")
    public ChangeCountData getLogCount() {
        List<Map<String, Integer>> list1 = new ArrayList<>();
        List<Map<String, Integer>> list2 = new ArrayList<>();
        List<Map<String, Integer>> list3 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Integer count1 = logService.getCount(Wrappers.<Log>query().le("date", LocalDateTime.now().minusDays(i)));
            Integer count2 = logService.getCount(Wrappers.<Log>query().eq("status", "true").le("date", LocalDateTime.now().minusDays(i)));
            Integer count3 = logService.getCount(Wrappers.<Log>query().ge("date", LocalDateTime.now().minusDays(i + 1)).le("date", LocalDateTime.now().minusDays(i)));
            Map<String, Integer> map1 = new HashMap<>();
            map1.put("count", count1);
            list1.add(map1);
            map1 = new HashMap<>();
            map1.put("count", count2);
            list2.add(map1);
            map1 = new HashMap<>();
            map1.put("count", count3);
            list3.add(map1);
        }
        Collections.reverse(list1);
        Collections.reverse(list2);
        Collections.reverse(list3);
        return new ChangeCountData(list1, list2, list3);
    }

    /***
     * @return 回馈不同地区的请求次数
     */
    @GetMapping("/local")
    public List<LocalInfo> getLocal() {
        List<LocalInfo> localInfos = new ArrayList<>();
        settingService.AdminGetSettings(null, Wrappers.<Setting>query().select("distinct local", "id","local")).getRecords().forEach(
                setting -> localInfos.add(new LocalInfo(setting.getLocal(),logService.getCount(Wrappers.<Log>query().eq("s_id",setting.getId()))))
        );
        return localInfos;
    }
    @GetMapping("/pages")
   public List<LocalInfo> getPages(){
        List<LocalInfo> list = new ArrayList<>();
        pageService.getPageList(null,Wrappers.<jimo.care.care_note.bean.Page>query().select("visit","name")).getRecords()
                .forEach(page ->list.add(new LocalInfo(page.getName(),page.getVisit())));
        return list;
   }
}
