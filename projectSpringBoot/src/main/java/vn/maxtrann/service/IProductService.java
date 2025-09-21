package vn.maxtrann.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.maxtrann.entity.ProductEntity;

public interface IProductService {
	void deleteAll();
    void delete(ProductEntity entity);
    void deleteById(Long id);
    long count();
    Optional<ProductEntity> findById(Long id);
    List<ProductEntity> findAllById(Iterable<Long> ids);
    List<ProductEntity> findAll(Sort sort);
    Page<ProductEntity> findAll(Pageable pageable);
    List<ProductEntity> findAll();
    <S extends ProductEntity> S save(S entity);
    List<ProductEntity> findByNameContaining(String name);
    Page<ProductEntity> findByNameContaining(String name, Pageable pageable);
}
