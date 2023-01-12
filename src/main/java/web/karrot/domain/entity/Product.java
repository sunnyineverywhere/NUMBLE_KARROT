package web.karrot.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long productId;

    @Column
    public String category;

    @Column
    public Long price;

    @Column
    public String image;

    @Column
    public String title;

    @Column
    public String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    public Member member;

    @Builder
    public Product(Long productId, String category, Long price, String image, String title, String contents, Member member) {
        this.productId = productId;
        this.category = category;
        this.price = price;
        this.image = image;
        this.title = title;
        this.contents = contents;
        this.member = member;
    }
}
