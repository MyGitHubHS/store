package service;

import domain.Product;

public interface OrderService {

    void addOrderitem(Integer num, Product product, String oid);

    void placeOrder(String oid, Double totalMoney, String address, String username, String telephone, String uid);
}
