package vn.maxtrann.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.maxtrann.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
