package web.karrot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karrot.domain.entity.Member;

import java.util.Map;
import java.util.Objects;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Objects findByEmail(String memberInfo);
}
