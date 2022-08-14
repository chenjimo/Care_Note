package jimo.care.care_note.controller;

import jimo.care.care_note.service.impl.PageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <p>
 * PageController前端控制器
 * 主要记录页面的访问次数
 * </p>
 *
 * @author JIMO
 * @since 2022-08-010
 */
@Controller
public class PageController {
    @Resource
    PageServiceImpl pageService;

    /***
     * @return 常规页面的处理！！！
     */
    @GetMapping("/404")
    public ModelAndView notFind() {
        return new ModelAndView(pageService.getPageUrl("/404", true));
    }

    @GetMapping("/400")
    public ModelAndView noFind() {
        return new ModelAndView(pageService.getPageUrl("/400", true));
    }

    @GetMapping("/500")
    public ModelAndView error() {
        return new ModelAndView(pageService.getPageUrl("/500", true));
    }

    @GetMapping("/login/user")
    public ModelAndView user() {
        return new ModelAndView(pageService.getPageUrl("/login/user", true));
    }

    @GetMapping("/login/admin")
    public ModelAndView admin() {
        return new ModelAndView(pageService.getPageUrl("/login/admin", true));
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView(pageService.getPageUrl("/", true));
    }

    /***
     * @return 用户页面的处理
     */
    @GetMapping("/user/console")
    public ModelAndView userConsole() {
        return new ModelAndView(pageService.getPageUrl("/user/console", true));
    }

    @GetMapping("/user/my")
    public ModelAndView userMy() {
        return new ModelAndView(pageService.getPageUrl("/user/my", true));
    }

    @GetMapping("/user/setting")
    public ModelAndView userSetting() {
        return new ModelAndView(pageService.getPageUrl("/user/setting", true));
    }

    @GetMapping("/user/module")
    public ModelAndView userModule() {
        return new ModelAndView(pageService.getPageUrl("/user/module", true));
    }

    /***
     * @param request
     * @return 下面的用于用户修改的页面！！！
     */
    @GetMapping("/user/update")
    public ModelAndView updateUser(HttpServletRequest request) {
        return new ModelAndView(pageService.getPageUrl("/user/update", true));
    }

    /***
     * @param type 判断是修改、上传
     * @return 视图地址！
     */
    @GetMapping("/user/module/{type}")
    public ModelAndView updateModule(@PathVariable("type")String type,HttpServletRequest request){
        if (Objects.equals(type, "post")){//true为上传信息的页面！！！
            request.getSession().setAttribute("module",null);
            return new ModelAndView(pageService.getPageUrl("/user/moduleUpdate", true));
        }else {//修改个别信息的情况！！！type为ID
            request.getSession().setAttribute("module",Integer.parseInt(type));
            return new ModelAndView(pageService.getPageUrl("/user/moduleUpdate", true));
        }
    }
    /***
     * @param type 判断是修改、上传
     * @return 视图地址！
     */
    @GetMapping("/user/setting/{type}")
    public ModelAndView updateSetting(@PathVariable("type")String type,HttpServletRequest request){
        if (Objects.equals(type, "post")){//true为上传信息的页面！！！
            request.getSession().setAttribute("setting",null);
            return new ModelAndView(pageService.getPageUrl("/user/settingUpdate", true));
        }else {//修改个别信息的情况！！！type为ID
            request.getSession().setAttribute("setting",Integer.parseInt(type));
            return new ModelAndView(pageService.getPageUrl("/user/settingUpdate", true));
        }
    }
    @GetMapping("/user/money")
    public ModelAndView addMoney(HttpServletRequest request) {
        return new ModelAndView(pageService.getPageUrl("/user/money", true));
    }

    /***
     * @param page 用于管理员的页面识图处理
     * @return
     */
    @GetMapping("/admin/v/{page}")
    public ModelAndView adminView(@PathVariable("page")String page){
        return new ModelAndView(pageService.getPageUrl("/admin/v/"+page, true));
    }
    /***
     * @param page 用于管理员的页面识图处理
     * @return
     */
    @GetMapping("/admin/u/{page}")
    public ModelAndView adminUpdate(@PathVariable("page")String page){
        return new ModelAndView(pageService.getPageUrl("/admin/u/"+page, true));
    }
}
