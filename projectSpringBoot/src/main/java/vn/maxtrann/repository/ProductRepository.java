package vn.maxtrann.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.maxtrann.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	List<ProductEntity> findByNameContaining(String name);
    Page<ProductEntity> findByNameContaining(String name, Pageable pageable);
    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findByCreatedDate(Timestamp createdDate);
}
