package vn.maxtrann.controller.api;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.maxtrann.entity.CategoryEntity;
import vn.maxtrann.model.Response;
import vn.maxtrann.service.ICategoryService;
import vn.maxtrann.service.IStorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/category")
public class CategoryAPIController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    IStorageService storageService;

    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok().body(categoryService.findAll()); // trả về danh sách category dưới dạng JSON mà ko cần Response
        //return new ResponseEntity<Response>(new Response(true, "Thành công", categoryService.findAll()), HttpStatus.OK);
    }

    @PostMapping(path = "/getCategory")
    public ResponseEntity<?> getCategory(@Validated @RequestParam("id") Long id) {
        Optional<CategoryEntity> category = categoryService.findById(id);  
        if(category.isPresent()){
            return new ResponseEntity<Response>(new Response(true, "Thành công", category.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(new Response(false, "Không tìm thấy danh mục", null), HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping(path = "/addCategory")
    public ResponseEntity<?> addCategory(@Validated @RequestParam("categoryName") String categoryName, @Validated @RequestParam("icon") MultipartFile icon) {
        Optional<CategoryEntity> optCategory = categoryService.findByName(categoryName);

        if (optCategory.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Catory đã tồn tại trong hệ thống");
        }
        else {
            CategoryEntity category = new CategoryEntity();
            // kiểm tra tồn tại file, lưu file 
            if (!icon.isEmpty()){
                UUID uuid = UUID.randomUUID();
                String uuString = uuid.toString();
                //lưu file vào trường Images
                category.setIcon(storageService.getStoredFileName(icon, uuString));
                storageService.store(icon, category.getIcon());
            }
            category.setName(categoryName);
            categoryService.save(category);

            return new ResponseEntity<Response>(new Response(true, "Thêm thành công", category), HttpStatus.OK);
        }
    }

    @PutMapping(path = "/updateCategory")
    public ResponseEntity<?> updateCategory(@Validated @RequestParam("categoryId") Long categoryId,
    @Validated @RequestParam("categoryName") String categoryName,
    @Validated @RequestParam("icon") MultipartFile icon) 
    {
        Optional<CategoryEntity> optCategory = categoryService.findById(categoryId);
        if (optCategory.isEmpty()) {
            return new ResponseEntity<Response>(new Response(false, "Không tìm thấy Category", null), HttpStatus.BAD_REQUEST);
        }
        else if(optCategory.isPresent()) {
            //kiểm tra tồn tại file, lưu file
            if(!icon.isEmpty()) {
                UUID uuid = UUID.randomUUID();
                String uuString = uuid.toString();
                //lưu file vào trường Images
                optCategory.get().setIcon(storageService.getStoredFileName(icon, uuString));
                storageService.store(icon, optCategory.get().getIcon());
            }
            optCategory.get().setName(categoryName);
            categoryService.save(optCategory.get());
            //return ResponseEntity.ok().body(category);
            return new ResponseEntity<Response>(new Response(true, "Cập nhật Thành công", optCategory.get()), HttpStatus.OK);
        }
        return null;
    }
    
    @DeleteMapping(path = "/deleteCategory")
    public ResponseEntity<?> deleteCategory(@Validated @RequestParam("categoryId") Long categoryId){
        Optional<CategoryEntity> optCategory = categoryService.findById(categoryId);
        if (optCategory.isEmpty()) {
            return new ResponseEntity<Response>(new Response(false, "Không tìm thấy Category", null), HttpStatus.BAD_REQUEST);
        }
        else if (optCategory.isPresent()){
            categoryService.delete(optCategory.get());
            return ResponseEntity.ok().body(optCategory.get()); // trả về thông tin category vừa xóa
            //return new ResponseEntity<Response>(new Response(true, "Xóa thành công", optCategory.get()), HttpStatus.OK);
        }

        return null;
    }


}
