package ecommerce.ecommerce.dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
  private Long id;
  private Long memberId;
  private Long productId;
  private String productName;
  private int count;
  private Long price;
  private LocalDateTime create_date;
  private LocalDateTime update_date;

}
