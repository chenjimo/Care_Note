package jimo.care.care_note.config;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jimo.care.care_note.bean.Page;
import jimo.care.care_note.service.impl.PageServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/***
 * 视图配置
 * 最终决定废弃此类中的访问增加功能，
 * 通过测试才发现配置类的东西只在刚开始建立时记忆一次，、
 * 因此起不到所需的效果！
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    PageServiceImpl pageService;

    /***
     * 在这里可以进行对View的控制
     * 同时对访问数据进行简单的处理！！！
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*registry.addViewController("/").setViewName(getPage("/"));
        registry.addViewController("/404").setViewName(getPage("/404"));
        registry.addViewController("/500").setViewName(getPage("/500"));
        registry.addViewController("/user").setViewName(getPage("/user"));
        registry.addViewController("/admin").setViewName(getPage("/admin"));
        registry.addViewController("/login/user").setViewName(getPage("/login/user"));
        registry.addViewController("/login/admin").setViewName(getPage("/login/admin"));
        registry.addViewController("/jimo.fun").setViewName(getPage("/jimo.fun"));*/
        getHtml(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:F://pictures/");
//        registry.addResourceHandler("/images/**").addResourceLocations("file:/project/collection/images/");
    }

    /***
     * @param url 通过请求地址，
     * @return 获取本地地址
     */
    private String getPage(String url) {
        String pageUrl = pageService.getPageUrl(url, false);
        return pageUrl == null ? pageService.getPageUrl("/404", true) : pageUrl;
    }

    /***
     * 原始的方法要一个一个手动,做了优化
     * 这里直接数据库遍历操作！！！
     */
    private void getHtml(ViewControllerRegistry registry) {
        pageService.getPageList(null, Wrappers.<Page>query().select("url", "page_url")).getRecords()
                .forEach(page ->
                        registry.addViewController(page.getUrl()).setViewName(page.getPageUrl()));
    }
}
