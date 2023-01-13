package web.karrot.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long roomId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    public Member subscriber;

    @Builder
    public ChatRoom(Long roomId, Product product, Member subscriber) {
        this.roomId = roomId;
        this.product = product;
        this.subscriber = subscriber;
    }
}
