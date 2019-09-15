package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Category;
import domain.Page;
import domain.Product;
import service.ProductService;
import service.ProductServiceImp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/productServlet")
public class ProductServlet extends BaseServlet {


private ObjectMapper mapper=new ObjectMapper();

    private ProductService productService=new ProductServiceImp();
    //查询热门商品
    public void findHotProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> list=productService.findHotProduct();
        String json = mapper.writeValueAsString(list);
        response.getWriter().println(json);
    }
    //查询分类，导航栏显示
    public void findHead(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> list=productService.findHead();
        String json = mapper.writeValueAsString(list);
        response.getWriter().println(json);
    }

    //根据分类ID查询商品
    public String findProductByCid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cid = request.getParameter("cid");
        Page page1 = new Page();
        String pageNo = request.getParameter("pageNo");
        if(pageNo==null||pageNo.equals("")){
            page1.setPageNo(1);
        }else{
            page1.setPageNo(Integer.valueOf(pageNo));
        }
        Page page=productService.findProductByCid(cid,page1);
       request.setAttribute("pagelist",page);
       return "product_list.jsp";

    }

    //商品展示，根据Id查询详细信息.
    public String findProductByPid(HttpServletRequest request, HttpServletResponse response){
        String pid = request.getParameter("pid");
        Product product=productService.findProductByPid(pid);
        request.getSession().setAttribute("product",product);
        request.setAttribute("product",product);

        return  "product_info.jsp";
    }

}
