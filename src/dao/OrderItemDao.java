package dao;

import domain.Orders;
import domain.Orderitem;

public interface OrderItemDao {
    void addOrderitem(Orderitem orderitem);

    void addOrder(Orders order);
}
