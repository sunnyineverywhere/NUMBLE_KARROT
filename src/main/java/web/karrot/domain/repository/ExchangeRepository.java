package web.karrot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karrot.domain.entity.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
}
