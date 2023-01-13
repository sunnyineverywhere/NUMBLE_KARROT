package web.karrot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karrot.domain.entity.Heart;
import web.karrot.domain.entity.Member;
import web.karrot.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByMemberAndProduct(Member member, Product product);

    Optional<Heart> deleteByMemberAndProduct(Member member, Product product);

    List<Heart> findAllByMember(Member member);
}
