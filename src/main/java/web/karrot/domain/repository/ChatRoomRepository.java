package web.karrot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karrot.domain.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
