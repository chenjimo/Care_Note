package jimo.care.care_note.filter;

import jimo.care.care_note.bean.User;
import jimo.care.care_note.info.user.UserPower;
import jimo.care.care_note.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/***
 * 用于限制外部API调用的密钥检测
 */
@Component
public class ApiKeyFilter implements Filter {
    @Resource
    UserServiceImpl userService;

    /**
     * The <code>doFilter</code> method of the Filter is called by the container
     * each time a request/response pair is passed through the chain due to a
     * client request for a resource at the end of the chain. The FilterChain
     * passed in to this method allows the Filter to pass on the request and
     * response to the next entity in the chain.
     * <p>
     * A typical implementation of this method would follow the following
     * pattern:- <br>
     * 1. Examine the request<br>
     * 2. Optionally wrap the request object with a custom implementation to
     * filter content or headers for input filtering <br>
     * 3. Optionally wrap the response object with a custom implementation to
     * filter content or headers for output filtering <br>
     * 4. a) <strong>Either</strong> invoke the next entity in the chain using
     * the FilterChain object (<code>chain.doFilter()</code>), <br>
     * 4. b) <strong>or</strong> not pass on the request/response pair to the
     * next entity in the filter chain to block the request processing<br>
     * 5. Directly set headers on the response after invocation of the next
     * entity in the filter chain.
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this
     *                 filter to pass the request and response to for further
     *                 processing
     * @throws IOException      if an I/O error occurs during this filter's
     *                          processing of the request
     * @throws ServletException if the processing fails for any other reason
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //继续执行下一个过滤器
        HttpServletRequest req1 = (HttpServletRequest) request;
        HttpServletResponse res1 = (HttpServletResponse) response;
        User user = (User) req1.getSession().getAttribute("CareUser");
        String key = req1.getParameter("key");
        String pwd = userService.UserGetUser("1517962688@qq.com").getPwd();
        if (Objects.equals(key, pwd)||user != null) {
            if ( Objects.equals(key, pwd) || user.getPower() >= UserPower.UPDATE) {
                chain.doFilter(request, response);
            } else {
                res1.sendError(400, "\t很遗憾,权限不足或账户已经注销,<a href=‘login/admin’>请您重新登录！或输入密钥</a>\n\t或联系管理员QQ：1517962688");
            }
        } else {
            res1.sendError(404, "\t很遗憾,权限不足<a href=‘login/admin’>请您登录，或输入密钥！</a>\n\t或联系管理员QQ：1517962688");
        }
    }
}
