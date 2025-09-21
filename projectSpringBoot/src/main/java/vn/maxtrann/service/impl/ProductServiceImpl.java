package vn.maxtrann.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import vn.maxtrann.entity.ProductEntity;
import vn.maxtrann.repository.ProductRepository;
import vn.maxtrann.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	@Autowired
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public void delete(ProductEntity entity) {
        productRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductEntity> findAllById(Iterable<Long> ids) {
        return productRepository.findAllById(ids);
    }

    @Override
    public List<ProductEntity> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public <S extends ProductEntity> S save(S entity) {
    	if (entity.getProductId() == null) {
            return productRepository.save(entity);
        } else {
            Optional<ProductEntity> optImages = findById(entity.getProductId());
            if (StringUtils.isEmpty(entity.getImages())) {
                entity.setImages(optImages.get().getImages());
            } else
                entity.setImages(entity.getImages());
        }
        return productRepository.save(entity);
    }

    @Override
    public List<ProductEntity> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public Page<ProductEntity> findByNameContaining(String name, Pageable pageable) {
        return productRepository.findByNameContaining(name, pageable);
    }
}
