package service;

import dao.OrderListDao;
import domain.*;
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

    //分页查询
    @Override
    public OrderListPage findOrderListpageByUid(String uid, OrderListPage orderListPage) {
        //获取当前用户下有多少订单项
        Integer count=orderListDao.getCount(uid);
        //当传入的pageNo大于最大页数时，将最大页数赋给查询的页数
        orderListPage.setTotalCount(count);
        if(orderListPage.getPageNo()>orderListPage.getTotalPage()){
            orderListPage.setPageNo(orderListPage.getTotalPage());
        }
        orderListPage.setTotalPage(orderListPage.getTotalPage());
        //获取当前页面所需展示的订单项集合
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
