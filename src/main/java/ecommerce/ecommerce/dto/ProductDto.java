package ecommerce.ecommerce.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
        private String productName;
        private Long price;
        private String detail;

    }

    @Data
    public static class UpdateProduct{
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
