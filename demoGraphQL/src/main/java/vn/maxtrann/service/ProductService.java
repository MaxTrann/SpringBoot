package vn.maxtrann.service;

import vn.maxtrann.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    Product update(Long id, Product product);
    void delete(Long id);

    List<Product> findAllByPriceAsc();
    List<Product> findByCategory(Long categoryId);
}
