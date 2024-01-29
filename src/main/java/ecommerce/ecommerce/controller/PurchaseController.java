package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.dto.PurchaseDto;
import ecommerce.ecommerce.service.PurchaseService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {
  private final PurchaseService purchaseService;

  @PostMapping("/customer/card")
  public ResponseEntity<?> customerRegisterPurchaseByCard(@Validated @RequestBody PurchaseDto purchaseDto,
      String productName, Principal principal){
    purchaseService.customerRegisterPurchaseByCard(purchaseDto, principal);
    return ResponseEntity.ok("결제 성공");
  }

  @PostMapping("/customer/cash")
  public ResponseEntity<?> customerRegisterPurchaseByCase(@Validated @RequestBody PurchaseDto purchaseDto,
      String productName, Principal principal){
    purchaseService.customerRegisterPurchaseByCash(purchaseDto, principal);
    return ResponseEntity.ok("결제 성공");
  }

  @PutMapping("/customer/{id}")
  public ResponseEntity<?> customerCancelPurchase(@PathVariable Long id, Principal principal){
    purchaseService.customerCancelPurchase(id, principal);
    return ResponseEntity.ok("취소 성공");
  }

  @PutMapping("/seller/{id}")
  public ResponseEntity<?> sellerApprovePurchase(@PathVariable Long id, Principal principal){
    purchaseService.sellerApprovePurchase(id, principal);
    return ResponseEntity.ok("결제 승인");
  }

  @PutMapping("/seller/cancel/{id}")
  public ResponseEntity<?> sellerCancelPurchase(@PathVariable Long id, Principal principal){
    purchaseService.sellerApproveCancel(id, principal);
    return ResponseEntity.ok("결제 취소");
  }

}
