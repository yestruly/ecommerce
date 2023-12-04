package ecommerce.ecommerce.service;

import ecommerce.ecommerce.entity.Member;
import ecommerce.ecommerce.repository.MemberRepository;
import ecommerce.ecommerce.dto.ProductDto;
import ecommerce.ecommerce.entity.Product;
import ecommerce.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;

import static ecommerce.ecommerce.entity.constants.Role.ROLE_SELLER;

@Service
@AllArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final MemberRepository memberRepository;

  @Transactional
  public void registerProduct(ProductDto.RegisterProduct productRegister, Principal principal) {

    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    if (!member.getRole().equals(ROLE_SELLER)) {
      throw new RuntimeException("판매자만 상품 등록 가능");
    }

    productRepository.save(new Product().builder()
        .product_name(productRegister.getProduct_name())
        .price(productRegister.getPrice())
        .detail(productRegister.getDetail())
        .create_date(LocalDateTime.now())
        .build());


  }

  @Transactional
  public void updateProduct(Long id, ProductDto.UpdateProduct updateProduct, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    if (!member.getRole().equals(ROLE_SELLER)) {
      throw new RuntimeException("판매자만 상품 수정 가능");
    }

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("등록되지 않은 상품"));

    if (member.getId() != product.getMember().getId()) {
      throw new RuntimeException("자신이 등록한 상품 정보만 수정 가능");
    }

    product.setPrice(updateProduct.getPrice());
    product.setDetail(updateProduct.getDetail());
    product.setUpdate_date(LocalDateTime.now());

    productRepository.save(product);


  }

  public void deleteProduct(Long id, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    if (!member.getRole().equals(ROLE_SELLER)) {
      throw new RuntimeException("판매자만 상품 삭제 가능");
    }

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("등록되지 않은 상품"));

    if (member.getId() != product.getMember().getId()) {
      throw new RuntimeException("자신이 등록한 상품 정보만 삭제 가능");
    }

    productRepository.delete(product);
  }
}
