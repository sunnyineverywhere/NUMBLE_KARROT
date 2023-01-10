package web.karrot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karrot.domain.entity.Member;

import java.util.Map;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Map<Object, Object> findByEmail(String memberInfo);
}
