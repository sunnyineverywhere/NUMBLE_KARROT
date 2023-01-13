package web.karrot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.karrot.controller.dto.response.BodyMessage;
import web.karrot.controller.dto.response.CustomResponseEntity;
import web.karrot.controller.dto.response.StatusEnum;
import web.karrot.domain.entity.Heart;
import web.karrot.domain.entity.Member;
import web.karrot.domain.entity.Product;
import web.karrot.domain.repository.HeartRepository;
import web.karrot.domain.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CustomResponseEntity<BodyMessage> heartAdd(Member member, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        if(heartRepository.findByMemberAndProduct(member, product).isPresent()){
            return new CustomResponseEntity<>(BodyMessage.builder()
                    .message("좋아요를 취소합니다.")
                    .status(StatusEnum.OK)
                    .data(heartRepository.deleteByMemberAndProduct(member, product))
                    .build(),
                    HttpStatus.OK
                    );
        }
        else if(product.getMember().equals(member)){
            return new CustomResponseEntity<>(BodyMessage.builder()
                    .message("회원님께서 등록한 상품입니다.")
                    .status(StatusEnum.BAD_REQUEST)
                    .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        else{
            Heart heart = Heart.builder()
                    .member(member)
                    .product(product)
                    .build();
            Heart savedHeart = heartRepository.save(heart);
            return new CustomResponseEntity<>(
                    BodyMessage.builder()
                            .message("좋아요한 상품에 추가되었습니다.")
                            .status(StatusEnum.OK)
                            .data(savedHeart)
                            .build(),
                    HttpStatus.OK
            );
        }
    }
}
