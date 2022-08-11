package jimo.care.care_note.filter;

import jimo.care.care_note.bean.User;
import jimo.care.care_note.info.user.UserPower;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 对管理员身份的验证过滤器
 */
@Component
public class AdminFilter implements Filter {
 
    //重写其中的doFilter方法
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //继续执行下一个过滤器
        HttpServletRequest req1 = (HttpServletRequest) req;
        HttpServletResponse res1 = (HttpServletResponse) res;
        User user =(User) req1.getSession().getAttribute("CareUser");
        if (user!=null){
            if (user.getPower()>= UserPower.UPDATE){
                chain.doFilter(req, res);
            }else {
                res1.sendError(400,"\t很遗憾,权限不足或账户已经注销,<a href=‘login/admin’>请您重新登录！</a>\n\t或联系管理员QQ：1517962688");
            }
        }else {
            res1.sendRedirect("/login/admin");
        }
    }
}