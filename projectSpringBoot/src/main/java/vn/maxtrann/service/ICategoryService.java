package vn.maxtrann.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.maxtrann.entity.CategoryEntity;

public interface ICategoryService {
	void deleteAll();
    void delete(CategoryEntity entity);
    void deleteById(Long id);
    long count();
    Optional<CategoryEntity> findById(Long id);
    List<CategoryEntity> findAllById(Iterable<Long> ids);
    List<CategoryEntity> findAll(Sort sort);
    Page<CategoryEntity> findAll(Pageable pageable);
    List<CategoryEntity> findAll();
    <S extends CategoryEntity> S save(S entity);
    
    List<CategoryEntity> findByNameContaining(String name);
    Page<CategoryEntity> findByNameContaining(String name, Pageable pageable);
    Optional<CategoryEntity> findByName(String name);
}
