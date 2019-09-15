package web;


import domain.*;
import service.OrderListService;
import service.OrderListServiceImp;
import service.UserService;
import service.UserServiceImp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/orderListServlet")
public class OrderListServlet extends BaseServlet {
    private   UserService userService = new UserServiceImp();
    private OrderListService orderListService=new OrderListServiceImp();
    //查询我的订单
    public String fingMyOrder(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //根据登录名查询用户，再根据用户去查对应的订单
        User user = (User)session.getAttribute("user");
        String username = user.getUsername();
        //获取当前登录账号的所有信息
        user= userService.getUserByName(username);
        //分页查询所需信息
        OrderListPage orderListPage = new OrderListPage();
        String pageNo = request.getParameter("pageNo");
        if(pageNo==null||pageNo.equals("")){
            orderListPage.setPageNo(1);
        }else{
            orderListPage.setPageNo(Integer.valueOf(pageNo));
        }
        OrderListPage page=orderListService.findOrderListpageByUid(user.getUid(),orderListPage);
        request.setAttribute("orderListPage",page);
        /*List<OrderList> orderListList=orderListService.findOrderListByUid(user.getUid());
        session.setAttribute("orderList",orderListList);*/
        return  "order_list.jsp";
    }
}
