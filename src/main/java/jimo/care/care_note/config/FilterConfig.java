package jimo.care.care_note.config;

import jimo.care.care_note.filter.AdminFilter;
import jimo.care.care_note.filter.ApiKeyFilter;
import jimo.care.care_note.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.annotation.Resource;

/***
 * 用于将配置的过滤器进行注入启动
 */
@Configuration
public class FilterConfig {
    @Resource
    UserFilter userFilter;
    @Resource
    AdminFilter adminFilter;
    @Resource
    ApiKeyFilter apiKeyFilter;

    /**
     * 注入userFilter
     */
    @Bean
    public FilterRegistrationBean<UserFilter> postUserFilter() {
        FilterRegistrationBean<UserFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(userFilter);
        registration.addUrlPatterns("/user/*");
        //设置优先级别
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    /**
     * 注入adminFilter
     */
    @Bean
    public FilterRegistrationBean<AdminFilter> postAdminFilter() {
        FilterRegistrationBean<AdminFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(adminFilter);
        registration.addUrlPatterns("/admin/*");
        //设置优先级别
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    /**
     * 注入ApiKeyFilter
     */
    @Bean
    public  FilterRegistrationBean<ApiKeyFilter> postAPiKeyFilter(){
        FilterRegistrationBean<ApiKeyFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(apiKeyFilter);
        registration.addUrlPatterns("/api/*");
        //设置优先级别
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}
