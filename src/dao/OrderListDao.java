package dao;

import domain.*;

import java.util.List;

public interface OrderListDao {
    List<OrderList> findOrderListByUid(String uid);

    Integer getCount(String uid);

    List<OrderListPage> findOrderlistPage(String uid, Integer beginRows, Integer pageSize);
    //获取所有符合条件的订单
    List<OrderList> findOrderListAllByUid(String uid);
}
