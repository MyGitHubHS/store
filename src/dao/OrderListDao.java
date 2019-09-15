package dao;

import domain.*;

import java.util.List;

public interface OrderListDao {
    List<OrderList> findOrderListByUid(String uid);

    Integer getCount(String uid);

    List<OrderListPage> findOrderlistPage(String uid, Integer beginRows, Integer pageSize);
}
