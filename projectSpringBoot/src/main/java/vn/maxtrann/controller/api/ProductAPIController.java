package vn.maxtrann.controller.api;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.maxtrann.entity.CategoryEntity;
import vn.maxtrann.entity.ProductEntity;
import vn.maxtrann.model.ProductModel;
import vn.maxtrann.model.Response;
import vn.maxtrann.service.ICategoryService;
import vn.maxtrann.service.IProductService;
import vn.maxtrann.service.IStorageService;

@RestController
@RequestMapping("/api/product")
public class ProductAPIController {
    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IStorageService storageService; 

    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        return new ResponseEntity<Response>(new Response(true, "Thành công", productService.findAll()), HttpStatus.OK);
    }

    public ResponseEntity<?> saveOrUpdate(
        @Validated @RequestParam("productName") String productName,
        @RequestParam("imageFile") MultipartFile productImages,
        @Validated @RequestParam("unitPrice") Double productPrice,
        @Validated @RequestParam("discount") Double promotionalPrice,
        @Validated @RequestParam("description") String productDescription,
        @Validated @RequestParam("categoryId") Long categoryId,
        @Validated @RequestParam("quantity") Integer quantity,
        @Validated @RequestParam("status") Short status
    ) {
        Optional<ProductEntity> optProduct = productService.findByName(productName);
        if (optProduct.isPresent()){
            return new ResponseEntity<Response>(new Response(false, "Sản phẩm này đã tồn tại trong hệ thống", optProduct.get()), HttpStatus.BAD_REQUEST);
        }
        else {
            ProductEntity product = new ProductEntity();
            Timestamp timestamp = new Timestamp(new Date(System.currentTimeMillis()).getTime());
            
            try {
                ProductModel proModel = new ProductModel();
                // copy từ model sang entity
                BeanUtils.copyProperties(proModel, product);
                // xử lý category liên quan product
                CategoryEntity cateEntity = new CategoryEntity();
                cateEntity.setCategoryId(proModel.getCategoryId());
                product.setCategory(cateEntity);
                // kiểm tra tồn tại file, lưu file
                if (!proModel.getImageFile().isEmpty()){
                    UUID uuid = UUID.randomUUID();
                    String uuString = uuid.toString();
                    // lưu file vào trường Images
                    product.setImages(storageService.getStoredFileName(proModel.getImageFile(), uuString));
                    storageService.store(proModel.getImageFile(), product.getImages());
                }
                product.setCreatedDate(timestamp);
                productService.save(product);
                optProduct = productService.findByCreatedDate(timestamp);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseEntity<Response>(new Response(true, "Thành công", optProduct.get()), HttpStatus.OK);
        }
    }

}
