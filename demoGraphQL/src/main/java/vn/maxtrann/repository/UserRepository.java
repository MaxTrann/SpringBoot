package vn.maxtrann.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.maxtrann.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserNameContaining(String userName);
    Page<User> findByUserNameContaining(String name, Pageable pageable);
    Optional<User> findByUserName(String name);
}
