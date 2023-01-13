package web.karrot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karrot.domain.entity.ChatRoom;
import web.karrot.domain.entity.Member;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findAllBySubscriberOrProduct_Member(Member member, Member member2);

}
