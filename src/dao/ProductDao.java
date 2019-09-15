package dao;

import domain.Category;
import domain.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findHotProduct();

    List<Category> findHead();

    List<Product> findProductByCid(String cid,Integer startNo,Integer size);

    Integer getCountBycid(String cid);

    Product findProductByPid(String pid);
}
