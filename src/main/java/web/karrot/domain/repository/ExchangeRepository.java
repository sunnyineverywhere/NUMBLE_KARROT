package web.karrot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karrot.domain.entity.Exchange;
import web.karrot.domain.entity.Member;
import web.karrot.domain.entity.Product;

import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    Optional<Exchange> deleteExchangeByProduct(Product product);
    Optional<Exchange> findByProductAndIsCompleted(Product product, Boolean isCompleted);

    Optional<Exchange> findExchangeByBuyerAndProduct(Member buyer, Product product);
}
