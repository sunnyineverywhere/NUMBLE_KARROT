package web.karrot.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long messageId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    public ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    public Member sender;

    @Column
    public String message;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public ChatMessage(Long messageId, ChatRoom chatRoom, Member sender, String messages) {
        this.messageId = messageId;
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.message = message;
    }
}
