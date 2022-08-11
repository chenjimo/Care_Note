package jimo.care.care_note.controller;

import jimo.care.care_note.service.impl.PageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

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
    @GetMapping("/404")
    public ModelAndView notFind(){
        return new ModelAndView(pageService.getPageUrl("/404",true));
    }@GetMapping("/400")
    public ModelAndView noFind(){
        return new ModelAndView(pageService.getPageUrl("/400",true));
    }@GetMapping("/500")
    public ModelAndView error(){
        return new ModelAndView(pageService.getPageUrl("/500",true));
    }@GetMapping("/login/user")
    public ModelAndView user(){
        return new ModelAndView(pageService.getPageUrl("/login/user",true));
    }@GetMapping("/login/admin")
    public ModelAndView admin(){
        return new ModelAndView(pageService.getPageUrl("/login/admin",true));
    }@GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView(pageService.getPageUrl("/",true));
    }
}
