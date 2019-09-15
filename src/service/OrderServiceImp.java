package service;

import dao.OrderItemDao;
import domain.Orders;
import domain.Orderitem;
import domain.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import utils.UUIDUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class OrderServiceImp implements OrderService {
    private static OrderItemDao orderItemDao =null;
    private static SqlSession sqlSession =null;

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
        orderItemDao =sqlSession.getMapper(OrderItemDao.class);
    }
    //提交订单项
    @Override
    public void addOrderitem(Integer num, Product product, String oid) {
        String itemid = UUIDUtils.getUUID();
        itemid=itemid.replaceAll("-","");
        Orderitem orderitem = new Orderitem();
        orderitem.setItemid(itemid);
        orderitem.setCount(num);
        orderitem.setSubtotal(product.getShopPrice()*num);
        orderitem.setPid(product.getPid());
        orderitem.setOid(oid);
        orderItemDao.addOrderitem(orderitem);
        sqlSession.commit();

    }

    //提交订单
    @Override
    public void placeOrder(String oid, Double totalMoney, String address, String username, String telephone, String uid) {
        Orders order = new Orders();
        order.setOid(oid);
        order.setOrdertime(new Date());
        order.setTotal(totalMoney);
        order.setState(1);
        order.setAddress(address);
        order.setName(username);
        order.setTelephone(telephone);
        order.setUid(uid);
        orderItemDao.addOrder(order);
        sqlSession.commit();

    }
}
