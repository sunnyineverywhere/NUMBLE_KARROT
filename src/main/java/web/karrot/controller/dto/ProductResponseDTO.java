package web.karrot.controller.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.karrot.domain.entity.Product;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResponseDTO {
    public Long productId;
    public String category;
    public Long price;
    public String image;
    public String title;
    public String contents;
    public LocalDateTime createdAt;
    public MemberResponseDTO seller;

    @Builder
    public ProductResponseDTO(Product entity){
        this.productId = entity.getProductId();
        this.category = entity.getCategory();
        this.price = entity.getPrice();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.createdAt = entity.getCreatedAt();
        this.seller = new MemberResponseDTO(entity.getMember());
    }
}
