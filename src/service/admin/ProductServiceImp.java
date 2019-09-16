package service.admin;

import dao.ProductDao;
import domain.Page;
import domain.Product;
import org.apache.ibatis.session.SqlSession;
import utils.MyBatisUtils;
import utils.UUIDUtils;

import java.util.Date;
import java.util.List;

public class ProductServiceImp implements  ProductService{

//后台管理添加商品
    @Override
    public void addProduct(Product product) {
        product.setPid(UUIDUtils.getUUID().replaceAll("-",""));
        product.setPdate(new Date());
        product.setPflag(0L);
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        productDao.addProduct(product);
        sqlSession.commit();


    }

    //后台管理分页查询所有商品
    @Override
    public Page findByPage(String currPage) {
        Integer pageNo=Integer.valueOf(currPage);
        Page page=new Page();
        page.setPageNo(pageNo);
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        page.setTotalCount(productDao.getAllCount());
        page.setList(productDao.getProductPage(page.getBeginRows(),page.getPageSize()));
        sqlSession.close();
        return page;
    }
}
