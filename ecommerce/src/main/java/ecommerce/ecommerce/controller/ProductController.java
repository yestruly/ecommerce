package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.dto.ProductDto;
import ecommerce.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/seller/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<?> registerProduct(@RequestBody ProductDto.RegisterProduct product, Principal principal){
        productService.registerProduct(product, principal);
        return ResponseEntity.ok("등록 성공");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody ProductDto.UpdateProduct updateProduct, Principal principal){
        productService.updateProduct(id, updateProduct, principal);
        return ResponseEntity.ok("수정 성공");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, Principal principal){
        productService.deleteProduct(id, principal);
        return ResponseEntity.ok("삭제 성공");
    }

}
