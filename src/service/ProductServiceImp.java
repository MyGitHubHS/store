package service;

import dao.ProductDao;
import domain.Category;
import domain.Page;
import domain.Product;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductServiceImp implements ProductService {

    private static SqlSession sqlSession =null;
    private static ProductDao productDao=null;

    static {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //通过配置文件创建会话工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //从工厂中获取一个会话
        sqlSession = sessionFactory.openSession();
        productDao=sqlSession.getMapper(ProductDao.class);
    }

    //根据pid获取单个商品信息
    @Override
    public Product findProductByPid(String pid) {
        Product product=productDao.findProductByPid(pid);
        return product;
    }

    //查询分类商品
    @Override
    public Page findProductByCid(String cid, Page page) {
        Integer count=productDao.getCountBycid(cid);
        List<Product> list=productDao.findProductByCid(cid,page.getBeginRows(),page.getPageSize());
        page.setList(list);
        page.setTotalCount(count);
        page.setTotalPage(page.getTotalPage());
        return  page;

    }

    //查询分类
    @Override
    public List<Category> findHead() {
        //读取配置文件
        CacheManager cacheManager=CacheManager.create(ProductServiceImp.class.getClassLoader().getResourceAsStream("ehcache.xml"));
        //根据名字获取缓存
        Cache cache = cacheManager.getCache("categoryCache");
        //添加或删除元素
        if (cache.get("head")!= null) {
            return  (List<Category>)cache.get("head").getObjectValue();

        }
        //没有就查询数据库，并添加到缓存里。
        List<Category> list=productDao.findHead();
        Element element=new Element("head",list);
        cache.put(element);
        return list;
    }

    //查询热门商品
    @Override
    public List<Product> findHotProduct() {
        //读取配置文件
       CacheManager cacheManager=CacheManager.create(ProductServiceImp.class.getClassLoader().getResourceAsStream("ehcache.xml"));
        //根据名字获取缓存
      Cache cache = cacheManager.getCache("categoryCache");
        //添加或删除元素

        //如果缓存里有list集合，则不查询数据可直接返回该list
       if (cache.get("list")!= null) {
          return  (List<Product>)cache.get("list").getObjectValue();

       }
        //没有就查询数据库，并添加到缓存里。
        List<Product> list=productDao.findHotProduct();
        Element element=new Element("list",list);
        cache.put(element);
        return list;
    }
}
