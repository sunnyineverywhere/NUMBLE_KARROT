package web.karrot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karrot.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
