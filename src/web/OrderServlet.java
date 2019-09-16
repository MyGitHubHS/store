package web;


import domain.Cart;
import domain.Product;
import domain.User;
import service.OrderService;
import service.OrderServiceImp;
import service.UserService;
import service.UserServiceImp;
import utils.UUIDUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

@WebServlet("/orderServlet")
public class OrderServlet extends BaseServlet {
private UserService userService=new UserServiceImp();
private OrderService orderService=new OrderServiceImp();
    //提交订单
    public String submit(HttpServletRequest request, HttpServletResponse response){
        String address = request.getParameter("address");
        //收货人姓名
        String username = request.getParameter("username");
        String telephone = request.getParameter("telephone");
        //TODO 付款方式
        String pd_frpId = request.getParameter("pd_FrpId");
        HttpSession session = request.getSession();

        //获取oid
        String oid = UUIDUtils.getUUID();
        oid=oid.replaceAll("-","");
        Map<String, Cart> map = (Map<String, Cart>) session.getAttribute("map");
        Set<String> strings = map.keySet();
        //计算所有商品总价
        Double totalMoney=0D;
        for (String s:strings
        ) {
            totalMoney+= map.get(s).getProduct().getShopPrice()*map.get(s).getNum();
        }

        //获取订单信息，并存储(存储uid，uid可以有多个订单)。
        //获取当前登录者信息，以获取uid。
        User user = (User) session.getAttribute("user");
        String username1 = user.getUsername();
        user=userService.getUserByName(username1);
        //空指针
        String uid = user.getUid();
        orderService.placeOrder(oid,totalMoney,address,username,telephone,uid);

         for (String s:strings
                ) {
                    //遍历每个订单项，再获取存储(存储订单编号oid，oid可以有多个订单项)
                    Product product=map.get(s).getProduct();
                     Integer num=map.get(s).getNum();
                     orderService.addOrderitem(num,product,oid);
         }

         return "c:order_list.jsp";

    }




}
