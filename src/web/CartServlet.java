package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Cart;
import domain.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet("/cartServlet")
public class CartServlet extends BaseServlet {
    private ObjectMapper mapper=new ObjectMapper();

    public String addCart(HttpServletRequest request, HttpServletResponse response) {
        //获取传来的参数
        String num = request.getParameter("num");
        HttpSession session = request.getSession();

        //获取存在session中的商品数据
        Product product = (Product)session.getAttribute("product");
        //创建对象来保存一条记录
        Cart cart=new Cart();
        if(product!=null&&num!=null){
            session.removeAttribute("product");
            cart.setNum(Integer.valueOf(num));
            cart.setProduct(product);
        }
        //获取session中的map(存储的是多条记录)集合
        Map<String,Cart> map = (Map<String, Cart>) session.getAttribute("map");
        if(map==null){
            //如果map不存在，就创建一个，将cart存进去，key为当前商品的pid。
            Map<String,Cart> map1=new HashMap<>();
            map1.put(product.getPid(),cart);
            session.setAttribute("map",map1);
        }else{
            //如果map存在，该商品再map里，改变num值
            if(map.get(product.getPid())!=null){
                map.get(product.getPid()).setNum( map.get(product.getPid()).getNum()+Integer.valueOf(num));
            }else {
                //如果不存在，直接添加
                map.put(product.getPid(), cart);
                session.setAttribute("map", map);
            }
        }
        return "c:cart.jsp";
    }

//购物车更改商品个数信息
    public void modifyByPid(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pid = request.getParameter("pid");
        String num = request.getParameter("num");
        HttpSession session = request.getSession();
        Map<String,Cart> map = (Map<String, Cart>) session.getAttribute("map");
        //根据传过来的pid进行改变该购物项的个数
        Cart cart = map.get(pid);
        cart.setNum(Integer.valueOf(num));
        //算出该商品的小计
        Double shopPrice = cart.getProduct().getShopPrice()*Integer.valueOf(num);
        Set<String> strings = map.keySet();
        Double totalMoney=0D;
        //循环算出所有商品总价
        for (String s:strings
             ) {
           totalMoney+= map.get(s).getProduct().getShopPrice()*map.get(s).getNum();
        }
        Double[] data={shopPrice,totalMoney};
        //将两个结果以json发回前端
        String json = mapper.writeValueAsString(data);
        response.getWriter().println(json);
    }

    //根据pid删除购物项
    public String  delectCartByPid(HttpServletRequest request, HttpServletResponse response){
        String pid = request.getParameter("pid");
        HttpSession session = request.getSession();
        //获取所有订单项map，根据pid删除
        Map<String,Cart> map = (Map<String, Cart>) session.getAttribute("map");
        map.remove(pid);
        return "c:cart.jsp";
    }

    //清除所有购物项，即清空session中的map
    public  String cleanAll(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //获取所有订单项map，根据pid删除
       session.removeAttribute("map");
        return "c:cart.jsp";
    }

}
