package service;

import domain.OrderList;
import domain.OrderListPage;

import java.util.List;

public interface OrderListService {
    List<OrderList> findOrderListByUid(String uid);

    OrderListPage findOrderListpageByUid(String uid, OrderListPage orderListPage);
    List<OrderList> findPageOrderListByUid(String uid,Integer pageNo);
}
