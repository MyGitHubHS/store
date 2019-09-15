package web;


import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import service.UserService;
import service.UserServiceImp;
import utils.MyDateConverter;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {

    private UserService userService =new UserServiceImp();

        //注册时查询用户名可用不。
      public String findUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
          String name = request.getParameter("name");
          int count= userService.findUserByName(name);
          response.setCharacterEncoding("utf-8");
          response.getWriter().println(count);
          return null;
      }

        //注册，注册成功，跳到login.jsp页面，失败返回注册页面
      public  String register(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
          User user=new User();
          ConvertUtils.register(new MyDateConverter(), Date.class);
          BeanUtils.populate(user,request.getParameterMap());
          System.out.println(user);
          int count= userService.register(user);
            if(count==1){
                return "login.jsp";
            }
          return "register.jsp";
      }

      //登陆成功跳转到index.jsp，否则不跳转
      public String login(HttpServletRequest request,HttpServletResponse response){
          User user=new User();
          try {
              //查询数据库有无该用户
              BeanUtils.populate(user,request.getParameterMap());
              int count = userService.login(user);
              Cookie[] cookies = request.getCookies();
              //返回值为1则是代表又改用户，将在session中添加一个user，跳转到index.jsp
              if(count==1){
                  request.getSession().setAttribute("user",user);
                  return "index.jsp";
              }
          } catch (IllegalAccessException e) {
              e.printStackTrace();
          } catch (InvocationTargetException e) {
              e.printStackTrace();
          }
          request.setAttribute("errorMsg","密码错误或者未激活！");
          return "login.jsp";
      }

      //激活用户
      public  String active(HttpServletRequest request,HttpServletResponse response){
          String code = request.getParameter("code");
          System.out.println(code);
          userService.active(code);
          return "login.jsp" ;
      }


}
