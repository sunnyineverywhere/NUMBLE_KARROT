package web.karrot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.karrot.controller.dto.ExchangeReqeustDTO;
import web.karrot.controller.dto.response.BodyMessage;
import web.karrot.controller.dto.response.CustomResponseEntity;
import web.karrot.controller.dto.response.ResponseMessage;
import web.karrot.controller.dto.response.StatusEnum;
import web.karrot.domain.entity.Exchange;
import web.karrot.domain.entity.Member;
import web.karrot.domain.entity.Product;
import web.karrot.domain.repository.ExchangeRepository;
import web.karrot.domain.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final ProductRepository productRepository;
    public CustomResponseEntity<BodyMessage> addExchange(Member member, ExchangeReqeustDTO reqeustDTO) {
        Product product = productRepository.findById(reqeustDTO.getProductId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        if(product.getMember().equals(member)){
            return new CustomResponseEntity<>(
                    BodyMessage.builder()
                            .status(StatusEnum.BAD_REQUEST)
                            .message("회원님이 등록한 상품입니다.")
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        else{
            Exchange exchange = Exchange.builder()
                    .product(product)
                    .buyer(member)
                    .isCompleted(false)
                    .build();

            return new CustomResponseEntity<>(
                    BodyMessage.builder()
                            .status(StatusEnum.OK)
                            .message("거래 예약 완료")
                            .data(exchangeRepository.save(exchange)).build(),
                    HttpStatus.OK
            );
        }
    }

    @Transactional
    public CustomResponseEntity<BodyMessage> removeExchange(Member member, ExchangeReqeustDTO reqeustDTO) {
        Product product = productRepository.findById(reqeustDTO.getProductId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        Exchange exchange = exchangeRepository.findByProductAndIsCompleted(product, false).orElseThrow(() -> new IllegalArgumentException("거래 내역을 삭제할 수 없습니다."));
        if(product.getMember().equals(member)) {
            return new CustomResponseEntity<>(
                    BodyMessage.builder()
                            .status(StatusEnum.OK)
                            .message("등록하신 상품의 거래 예약을 삭제하였습니다.")
                            .data(exchangeRepository.deleteExchangeByProduct(product))
                            .build(),
                    HttpStatus.OK
            );
        }
        else if(exchange.getBuyer().equals(member)){
            exchangeRepository.deleteById(exchange.getExchangeId());
            return new CustomResponseEntity<>(
                    BodyMessage.builder()
                            .status(StatusEnum.OK)
                            .message("신청하신 거래 예약을 삭제하였습니다.")
                            .build(),
                    HttpStatus.OK
            );
        }
        return new CustomResponseEntity<>(
                BodyMessage.builder()
                        .status(StatusEnum.BAD_REQUEST)
                        .message("잘못된 요청입니다.").build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
