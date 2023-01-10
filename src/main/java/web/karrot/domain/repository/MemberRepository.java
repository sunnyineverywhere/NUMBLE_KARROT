package web.karrot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karrot.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
