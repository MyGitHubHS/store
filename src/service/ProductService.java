package service;

import domain.Category;
import domain.Page;
import domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHotProduct();

    List<Category> findHead();

    Page findProductByCid(String cid, Page page);

    Product findProductByPid(String pid);
}
