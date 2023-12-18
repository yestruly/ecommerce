package ecommerce.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "cart")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "purchase_method")
    private String purchaseMethod;
    private String address;
    private String approve;

    @Column(name = "product_name")
    private String productName;
    private Long price;

    @CreatedDate
    private LocalDateTime create_date;

    @LastModifiedDate
    private LocalDateTime update_date;
}
