package vn.maxtrann.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.maxtrann.entity.User;
import vn.maxtrann.repository.UserRepository;
import vn.maxtrann.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new RuntimeException("User not found: " + id));
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public User update(Long id, User newUser) {
        User u = findById(id);
        u.setFullname(newUser.getFullname());
        u.setEmail(newUser.getEmail());
        u.setPassword(newUser.getPassword());
        u.setPhone(newUser.getPhone());
        u.setCategories(newUser.getCategories());
        return userRepo.save(u);
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
