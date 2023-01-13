package web.karrot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karrot.domain.entity.Member;
import web.karrot.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByMember(Member member);

    Optional<Product> deleteProductByProductIdAndMember(Long productId, Member member);
}
