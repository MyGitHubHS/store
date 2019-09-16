package web.admin;


import domain.Category;
import domain.Page;
import domain.Product;
import org.apache.commons.beanutils.BeanUtils;
import service.admin.ProductService;
import service.admin.ProductServiceImp;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@MultipartConfig
@WebServlet("/adminProductServlet")//AdminProductServlet?method=findByPage&currPage=1
public class AdminProductServlet extends BaseServlet {

    private ProductService productService=new ProductServiceImp();
    //后台管理查看所商品列表
    public String  findByPage(HttpServletRequest request, HttpServletResponse response){
        String currPage = request.getParameter("currPage");
        Page page= productService.findByPage(currPage);
        request.setAttribute("pageBean",page);
        return "admin/product/list.jsp";
    }

    //后台添加商品时查询商品种类
    public String findCategory(HttpServletRequest request, HttpServletResponse response){
        List<Category> head = new service.ProductServiceImp().findHead();
        request.setAttribute("list",head);
        return "admin/product/add.jsp";
    }

    //添加商品
    public String saveUI(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException, ServletException {
        Product product=new Product();
        //将前端粗哈利阿德数据封装成product对象
        BeanUtils.populate(product,request.getParameterMap());
        //分part上传数据，上传二进制文件必须的，就分part接收
        Part upload = request.getPart("upload");
        //将接收到的二进制问价能读出来
        InputStream inputStream = upload.getInputStream();
        String header = upload.getHeader("content-Disposition");
        Integer start=header.indexOf("filename")+10;
        Integer end =header.length()-1;
        String name=header.substring(start,end);
        //确认读到哪去。
        String path = request.getServletContext().getRealPath("products/1" + name);
        OutputStream out=new FileOutputStream(path);
        byte[] bys=new byte[1024];
        int length=0;
        while((length=inputStream.read(bys))!=-1){
            out.write(bys,0,length);
        }
        out.flush();
        out.close();
        //将写入的文件的地址给封装进peoduct对象里。
        product.setPimage("products/1"+name);
        productService.addProduct(product);
        return "adminProductServlet?method=findByPage&currPage=1";
    }

    //编辑商品信息
    public String edit(HttpServletRequest request, HttpServletResponse response){
        String pid = request.getParameter("pid");
        Product product = new service.ProductServiceImp().findProductByPid(pid);
        request.setAttribute("product",product);
        List<Category> head = new service.ProductServiceImp().findHead();
        request.setAttribute("csList",head);
        return "admin/product/edit.jsp";
    }

    //编辑完成提交
    public String updateByPid(HttpServletRequest request, HttpServletResponse response){
        System.out.println("---------------");
        OutputStream out=null;
        Product product=new Product();
        //将前端粗哈利阿德数据封装成product对象
        try {
            BeanUtils.populate(product,request.getParameterMap());
            Part upload = request.getPart("upload");
            //将接收到的二进制问价能读出来
            if (upload != null) {
                InputStream inputStream = upload.getInputStream();
                String header = upload.getHeader("content-Disposition");
                Integer start=header.indexOf("filename")+10;
                Integer end =header.length()-1;
                String name=header.substring(start,end);
                //确认读到哪去。
                String path = request.getServletContext().getRealPath("products/1" + name);
                out=new FileOutputStream(path);
                byte[] bys=new byte[1024];
                int length=0;
                while((length=inputStream.read(bys))!=-1){
                    out.write(bys,0,length);
                }
                product.setPimage("products/1"+name);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out!=null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(product);

        return null;
    }

}
