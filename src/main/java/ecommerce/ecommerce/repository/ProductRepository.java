package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.entity.Member;
import ecommerce.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContaining(String productName);
    Product findByProductName(String productName);

    Product findByMember(Member member);
}
