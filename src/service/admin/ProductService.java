package service.admin;

import domain.Page;
import domain.Product;


public interface ProductService {
    Page findByPage(String currPage);

    void addProduct(Product product);
}
