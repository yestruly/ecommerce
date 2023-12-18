package ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDto {
  private String productName;
  @NotBlank(message = "배송받을 주소를 반드시 입력해야 합니다.")
  private String address;

}
