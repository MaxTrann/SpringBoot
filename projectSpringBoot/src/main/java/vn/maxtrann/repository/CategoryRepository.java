package vn.maxtrann.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.maxtrann.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	List<CategoryEntity> findByNameContaining(String name);

    Page<CategoryEntity> findByNameContaining(String name, Pageable pageable);
    Optional<CategoryEntity> findByName(String name);

}
