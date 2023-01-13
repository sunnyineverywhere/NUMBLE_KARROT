package web.karrot.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Exchange extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long exchangeId;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    public Member buyer;

    @OneToOne
    @JoinColumn(name = "product_id")
    public Product product;

    @Column
    public Boolean isCompleted;

    @Builder
    public Exchange(Long exchangeId, Member buyer, Product product, Boolean isCompleted) {
        this.exchangeId = exchangeId;
        this.buyer = buyer;
        this.product = product;
        this.isCompleted = isCompleted;
    }

    public void setIsCompleted(){
        this.isCompleted = true;
    }
}
