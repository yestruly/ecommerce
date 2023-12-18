package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.entity.Cart;
import ecommerce.ecommerce.entity.Member;
import ecommerce.ecommerce.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  Cart findByMemberAndProduct(Member member, Product product);
}
