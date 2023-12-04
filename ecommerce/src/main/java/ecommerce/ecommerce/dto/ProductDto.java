package ecommerce.ecommerce.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String product_name;
    private Long price;
    private String detail;
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    @Data
    public static class RegisterProduct{
        private String product_name;
        private Long price;
        private String detail;

    }

    @Data
    public static class UpdateProduct{
        private Long price;
        private String detail;
    }
}
