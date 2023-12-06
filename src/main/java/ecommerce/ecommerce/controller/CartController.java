package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.dto.CartDto;
import ecommerce.ecommerce.dto.ProductDto;
import ecommerce.ecommerce.entity.Cart;
import ecommerce.ecommerce.entity.Product;
import ecommerce.ecommerce.service.CartService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/product/{name}")
    public ResponseEntity<?> getProductList(@PathVariable String name){
        List<Product> productList = cartService.searchProduct(name);
        AtomicLong countProduct = new AtomicLong(0L);
        List<ProductDto.SearchProduct> searchProductList = new ArrayList<>();

        productList.forEach(e ->
        {
            ProductDto.SearchProduct product = ProductDto.SearchProduct.builder()
                    .id(e.getId())
                    .productName(e.getProductName())
                    .price(e.getPrice())
                    .detail(e.getDetail()).build();

            searchProductList.add(product);
            countProduct.getAndIncrement();
        });

        return ResponseEntity.ok("상품 불러오기 성공");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProductToCart(@RequestBody CartDto.AddCart cart, Principal principal){
        cartService.addCart(cart,principal);
        return ResponseEntity.ok("장바구니에 상품 추가 성공");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody CartDto.UpdateCart cart, Principal principal){
        cartService.updateCart(id, cart, principal);
        return ResponseEntity.ok("수정 성공");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, Principal principal){
        cartService.deleteCart(id, principal);
        return ResponseEntity.ok("삭제 성공");
    }


}
