package ecommerce.ecommerce.entity;

import ecommerce.ecommerce.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    private String product_name;
    private Long price;
    private String detail;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
