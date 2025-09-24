package vn.maxtrann.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.maxtrann.entity.Category;
import vn.maxtrann.repository.CategoryRepository;
import vn.maxtrann.service.CategoryService;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Category not found: " + id));
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category update(Long id, Category newCategory) {
        Category c = findById(id);
        c.setName(newCategory.getName());
        c.setImages(newCategory.getImages());
        return categoryRepo.save(c);
    }

    @Override
    public void delete(Long id) {
        categoryRepo.deleteById(id);
    }
}
