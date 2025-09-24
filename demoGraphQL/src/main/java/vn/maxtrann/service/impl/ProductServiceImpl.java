package vn.maxtrann.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.maxtrann.entity.Product;
import vn.maxtrann.repository.ProductRepository;
import vn.maxtrann.repository.UserRepository;
import vn.maxtrann.service.ProductService;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public ProductServiceImpl(ProductRepository productRepo, UserRepository userRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found: " + id));
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product update(Long id, Product newProduct) {
        Product p = findById(id);
        p.setTitle(newProduct.getTitle());
        p.setQuantity(newProduct.getQuantity());
        p.setDesc(newProduct.getDesc());
        p.setPrice(newProduct.getPrice());
        p.setUser(newProduct.getUser());
        return productRepo.save(p);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<Product> findAllByPriceAsc() {
        return productRepo.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {
        return productRepo.findByCategoryIdOrderByPriceAsc(categoryId);
    }
}
