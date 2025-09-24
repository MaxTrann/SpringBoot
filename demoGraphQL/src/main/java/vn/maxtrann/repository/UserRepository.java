package vn.maxtrann.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.maxtrann.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // ví dụ thêm finder hay dùng
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
