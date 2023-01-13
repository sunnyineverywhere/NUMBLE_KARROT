package web.karrot.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.karrot.domain.entity.Heart;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartResponseDTO {

    public Long heartId;
    public LocalDateTime createdAt;
    public ProductResponseDTO productResponseDTO;

    public HeartResponseDTO(Heart entity){
        this.heartId = entity.getHeartId();
        this.createdAt = entity.getCreatedAt();
        this.productResponseDTO = new ProductResponseDTO(entity.getProduct());
    }
}
