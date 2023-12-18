package ecommerce.ecommerce.service;

import ecommerce.ecommerce.dto.CartDto;
import ecommerce.ecommerce.entity.Cart;
import ecommerce.ecommerce.entity.Member;
import ecommerce.ecommerce.entity.Product;
import ecommerce.ecommerce.repository.CartRepository;
import ecommerce.ecommerce.repository.MemberRepository;
import ecommerce.ecommerce.repository.ProductRepository;
import java.security.Principal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CartService {

  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final MemberRepository memberRepository;


  public List<Product> searchProduct(String name) {
    return productRepository.findByProductNameContaining(name);

  }

  public void addCart(String productName, Long count, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    Product product = productRepository.findByProductName(productName);

    cartRepository.save(new Cart().builder()
            .member(member)
            .product(product)
            .price(product.getPrice()*count)
            .count(count)
        .build());

  }

  public void updateCart(Long id, Long count, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    Cart cart = cartRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("장바구니에 존재하지 않는 내역"));

    if (member.getId() != cart.getMember().getId()) {
      throw new RuntimeException("자기 장바구니만 접근 가능");
    }

    cart.setPrice(cart.getProduct().getPrice()*count);
    cart.setCount(count);
    cartRepository.save(cart);
  }

  public void deleteCart(Long id, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    Cart cart = cartRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("장바구니에 존재하지 않는 내역"));

    if (member.getId() != cart.getMember().getId()) {
      throw new RuntimeException("자기 장바구니만 접근 가능");
    }

    cartRepository.delete(cart);

  }


}
