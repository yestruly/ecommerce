package ecommerce.ecommerce.cart.entity;

import ecommerce.ecommerce.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    private String product_name;
    private int count;
    private Long price;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
