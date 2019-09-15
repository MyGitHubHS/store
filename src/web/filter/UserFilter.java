package web.filter;

import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import utils.MyDateConverter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@WebFilter("/*")
public class UserFilter implements Filter {
    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //判断当前请求是否来自登陆界面和有无登陆
        //不是来自登陆界面并且没有登录
        if(!"login".equals(request.getParameter("method"))&&session.getAttribute("user")==null){
            Cookie[] cookies=request.getCookies();
            for (Cookie cookie:cookies
                 ) {
                //如果cookie里有aotulogin，则自动登录
                if(cookie.getName().equals("aotulogin")){
                    String value = cookie.getValue();
                    String[] split = value.split("&&");
                    User user1=new User();
                    user1.setUsername(split[0]);
                    user1.setPassword(split[1]);
                   session.setAttribute("user",user1);
                }

            }
        }else {
            //来自登录界面，
            User user = new User();
            try {
                ConvertUtils.register(new MyDateConverter(), Date.class);
                BeanUtils.populate(user, request.getParameterMap());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //将用户名和密码封装成一个cookie，一般放session里，安全问题
            if ("on".equals(request.getParameter("aotulogin"))) {
                Cookie cookie = new Cookie("aotulogin", user.getUsername() + "&&" + user.getPassword());
                cookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(cookie);
            }
            //记住用户名
            if ("on".equals(request.getParameter("remembername"))) {
                Cookie cookie = new Cookie("remembername", user.getUsername());
                cookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(cookie);
            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
