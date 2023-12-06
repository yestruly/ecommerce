package ecommerce.ecommerce.service;

import static ecommerce.ecommerce.entity.constants.Role.ROLE_SELLER;

import ecommerce.ecommerce.dto.CartDto;
import ecommerce.ecommerce.dto.CartDto.AddCart;
import ecommerce.ecommerce.dto.CartDto.UpdateCart;
import ecommerce.ecommerce.entity.Cart;
import ecommerce.ecommerce.entity.Member;
import ecommerce.ecommerce.entity.Product;
import ecommerce.ecommerce.repository.CartRepository;
import ecommerce.ecommerce.repository.MemberRepository;
import ecommerce.ecommerce.repository.ProductRepository;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

  public void addCart(CartDto.AddCart cart, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    cartRepository.save(new Cart().builder()
        .member(member)
        .productName(cart.getProduct_name())
        .price(cart.getPrice())
        .count(cart.getCount())
        .create_date(LocalDateTime.now())
        .build());


  }

  public void updateCart(Long id, CartDto.UpdateCart updateCart, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    Cart cart = cartRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("장바구니에 존재하지 않는 내역"));

    if(member.getId() != cart.getMember().getId()){
      throw new RuntimeException("자기 장바구니만 접근 가능");
    }

    cart.setCount(updateCart.getCount());
    cartRepository.save(cart);
  }

  public void deleteCart(Long id, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    Cart cart = cartRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("장바구니에 존재하지 않는 내역"));

    if(member.getId() != cart.getMember().getId()){
      throw new RuntimeException("자기 장바구니만 접근 가능");
    }

    cartRepository.delete(cart);

  }
}
