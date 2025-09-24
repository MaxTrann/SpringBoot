package vn.maxtrann.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.maxtrann.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 1) Lấy tất cả product theo price ↑ (Spring Data tự generate ORDER BY)
    List<Product> findAllByOrderByPriceAsc();

    // 2) Lấy product của 1 category (join qua User ⟷ Category), sort theo price ↑
    @Query("""
        select p from Product p
          join p.user u
          join u.categories c
        where c.id = :catId
        order by p.price asc
    """)
    List<Product> findByCategoryIdOrderByPriceAsc(@Param("catId") Long categoryId);

    // (nếu cần) Lấy product theo category, không sort
    @Query("""
        select p from Product p
          join p.user u
          join u.categories c
        where c.id = :catId
    """)
    List<Product> findByCategoryId(@Param("catId") Long categoryId);
}
