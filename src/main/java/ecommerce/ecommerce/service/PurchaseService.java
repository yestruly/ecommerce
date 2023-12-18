package ecommerce.ecommerce.service;

import static ecommerce.ecommerce.entity.constants.Role.ROLE_CUSTOMER;
import static ecommerce.ecommerce.entity.constants.Role.ROLE_SELLER;

import ecommerce.ecommerce.dto.PurchaseDto;
import ecommerce.ecommerce.entity.Cart;
import ecommerce.ecommerce.entity.Member;
import ecommerce.ecommerce.entity.Product;
import ecommerce.ecommerce.entity.Purchase;
import ecommerce.ecommerce.entity.constants.ApprovalStatus;
import ecommerce.ecommerce.entity.constants.PurchaseMethod;
import ecommerce.ecommerce.repository.CartRepository;
import ecommerce.ecommerce.repository.MemberRepository;
import ecommerce.ecommerce.repository.ProductRepository;
import ecommerce.ecommerce.repository.PurchaseRepository;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseService {

  private final MemberRepository memberRepository;
  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final PurchaseRepository purchaseRepository;


  public void customerRegisterPurchaseByCard(PurchaseDto purchaseDto, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));
    Product product = productRepository.findByProductName(purchaseDto.getProductName());
    Cart cart = cartRepository.findByMemberAndProduct(member, product);

    purchaseRepository.save(new Purchase().builder()
        .member(member)
        .cart(cart)
        .purchaseMethod(String.valueOf(PurchaseMethod.CARD))
        .approve(String.valueOf(ApprovalStatus.WAIT))
        .address(purchaseDto.getAddress())
        .productName(purchaseDto.getProductName())
        .price(cart.getPrice())
        .build());


  }

  public void customerRegisterPurchaseByCash(PurchaseDto purchaseDto, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    if(member.getRole() != String.valueOf(ROLE_CUSTOMER)){
      throw new RuntimeException("회원만 구매 가능");
    }
    Product product = productRepository.findByProductName(purchaseDto.getProductName());
    Cart cart = cartRepository.findByMemberAndProduct(member, product);

    purchaseRepository.save(new Purchase().builder()
        .member(member)
        .cart(cart)
        .product(product)
        .purchaseMethod(String.valueOf(PurchaseMethod.BANK_TRANSFER))
        .approve(String.valueOf(ApprovalStatus.WAIT))
        .address(purchaseDto.getAddress())
        .productName(purchaseDto.getProductName())
        .price(cart.getPrice())

        .build());
  }

  public void customerCancelPurchase(Long id, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));
    if(member.getRole() != String.valueOf(ROLE_CUSTOMER)){
      throw new RuntimeException("회원이 취소 가능");
    }

    Purchase purchase = purchaseRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 구매건"));

    purchase.setApprove(String.valueOf(ApprovalStatus.CANCEL));
    purchaseRepository.save(purchase);
  }


  public void sellerApprovePurchase(Long id, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    if(member.getRole() != String.valueOf(ROLE_SELLER)){
      throw new RuntimeException("판매자만 승인 가능");
    }

    Product product = productRepository.findByMember(member);

    Purchase purchase = purchaseRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 구매건"));

    if (purchase.getProduct() != product) {
      throw new RuntimeException("일치하지 않는 상품");
    }

    purchase.setApprove(String.valueOf(ApprovalStatus.APPROVE));
    purchaseRepository.save(purchase);

  }

  public void sellerApproveCancel(Long id, Principal principal) {
    String userName = principal.getName();
    Member member = memberRepository.findByUsername(userName)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

    if(member.getRole() != String.valueOf(ROLE_SELLER)){
      throw new RuntimeException("판매자만 취소 가능");
    }

    Product product = productRepository.findByMember(member);

    Purchase purchase = purchaseRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 구매건"));

    if (purchase.getProduct() != product) {
      throw new RuntimeException("일치하지 않는 상품");
    }

    purchase.setApprove(String.valueOf(ApprovalStatus.CANCEL));
    purchaseRepository.save(purchase);
  }
}
