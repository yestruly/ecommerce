package ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private Long memberId;
    private String productName;
    private Long price;
    private String detail;
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    @Data
    public static class RegisterProduct{
        @NotBlank(message = "상품명은 반드시 입력해야 합니다.")
        private String productName;

        @NotBlank(message = "가격은 반드시 입력해야 합니다.")
        private Long price;
        private String detail;

    }

    @Data
    public static class UpdateProduct{
        @NotBlank(message = "가격은 반드시 입력해야 합니다.")
        private Long price;
        private String detail;
    }

    @Builder
    @Data
    public static class SearchProduct{
        private Long id;
        private String productName;
        private Long price;
        private String detail;
    }

    @Data
    public class SearchProductList{
        private Long count;
        private List<SearchProduct> productList;
    }
}
