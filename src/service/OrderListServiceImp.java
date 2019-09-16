package service;

import dao.OrderListDao;
import domain.*;
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

public class OrderListServiceImp implements OrderListService {
    private static SqlSession sqlSession =null;
    private static OrderListDao orderListDao=null;

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
        orderListDao=sqlSession.getMapper(OrderListDao.class);
    }

    //查询所有符合条件的订单，当道缓存区，在根据页数取对应的区域。
    @Override
    public List<OrderList> findPageOrderListByUid(String uid, Integer pageNo) {
        //读取配置文件
        CacheManager cacheManager=CacheManager.create(ProductServiceImp.class.getClassLoader().getResourceAsStream("ehcache.xml"));
        //根据名字获取缓存
        Cache cache = cacheManager.getCache("categoryCache");
        List<OrderList> list=null;
        if (cache.get("orderList")!= null) {
            //如果不为空，返回
            list=(List<OrderList>)cache.get("orderList");

        }else {
            list=orderListDao.findOrderListAllByUid(uid);
            Element element=new Element("orderList",list);
            cache.put(element);
        }
        if (pageNo<1){
            pageNo=1;
        }
        List<OrderList> orderListAllByUid=null;
        for (int i=(pageNo-1)*4;i<pageNo*4;i++) {
            orderListAllByUid.add(list.get(i));
        }


        return  orderListAllByUid;
    }

    //分页查询
    @Override
    public OrderListPage findOrderListpageByUid(String uid, OrderListPage orderListPage) {
        //获取当前用户下有多少订单项
        Integer count=orderListDao.getCount(uid);
        orderListPage.setTotalCount(count);
        //当传入的pageNo大于最大页数时，将最大页数赋给查询的页数
        orderListPage.setTotalCount(count);
        if(orderListPage.getPageNo()>orderListPage.getTotalPage()){
            orderListPage.setPageNo(orderListPage.getTotalPage());
        }
        orderListPage.setTotalPage(orderListPage.getTotalPage());
        List<OrderListPage> list=orderListDao.findOrderlistPage(uid,orderListPage.getBeginRows(),orderListPage.getPageSize());
        orderListPage.setList(list);
        return orderListPage;
    }

    //根据uid查询所有uid下的pid，再查pid下的所有item，根据item里的pid查询对应商品信息，再封装到一个List集合里。
    @Override
    public List<OrderList> findOrderListByUid(String uid) {
        List<OrderList> ordersList=orderListDao.findOrderListByUid(uid);
        return ordersList;
    }
}
