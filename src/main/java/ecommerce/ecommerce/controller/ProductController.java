package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.dto.ProductDto;
import ecommerce.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/seller/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> registerProduct(@Validated @RequestBody ProductDto.RegisterProduct product, Principal principal){
        productService.registerProduct(product, principal);
        return ResponseEntity.ok("등록 성공");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct( @PathVariable Long id,@Validated @RequestBody ProductDto.UpdateProduct updateProduct, Principal principal){
        productService.updateProduct(id, updateProduct, principal);
        return ResponseEntity.ok("수정 성공");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, Principal principal){
        productService.deleteProduct(id, principal);
        return ResponseEntity.ok("삭제 성공");
    }

}
