package web.karrot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import web.karrot.controller.dto.ProductAddRequestDTO;
import web.karrot.controller.dto.ProductResponseDTO;
import web.karrot.controller.dto.response.BodyMessage;
import web.karrot.controller.dto.response.CustomResponseEntity;
import web.karrot.controller.dto.response.ResponseMessage;
import web.karrot.controller.dto.response.StatusEnum;
import web.karrot.domain.entity.Member;
import web.karrot.domain.entity.Product;
import web.karrot.domain.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public CustomResponseEntity<BodyMessage> addProduct(Member member, ProductAddRequestDTO requestDTO) {
        Product product = Product.builder()
                .category(requestDTO.getCategory())
                .price(requestDTO.getPrice())
                .image(requestDTO.getImage())
                .title(requestDTO.getTitle())
                .contents(requestDTO.getContents())
                .member(member)
                .build();
        Product savedProduct = productRepository.save(product);

        if(savedProduct.getProductId() != null){
            BodyMessage body = BodyMessage.builder()
                    .status(StatusEnum.OK)
                    .message(ResponseMessage.CREATED_PRODUCT)
                    .data(savedProduct.getProductId() + "번 상품 등록 성공, " + savedProduct.getMember().getEmail() + "님의 상품")
                    .build();
            return new CustomResponseEntity<>(body, HttpStatus.OK);
        }
        else{
            BodyMessage body = BodyMessage.builder()
                    .status(StatusEnum.BAD_REQUEST)
                    .message(ResponseMessage.DB_ERROR)
                    .data("등록 실패")
                    .build();
            return new CustomResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    public CustomResponseEntity<BodyMessage> findProductList() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDTO> responseDTOList = new ArrayList<>();

        for(Product p : productList){
            ProductResponseDTO dto = new ProductResponseDTO(p);
            responseDTOList.add(dto);
        }

        BodyMessage body = BodyMessage.builder()
                .status(StatusEnum.OK)
                .message(ResponseMessage.DB_SELECT_SUCCESS)
                .data(responseDTOList)
                .build();
        return new CustomResponseEntity<>(body, HttpStatus.OK);
    }

    public CustomResponseEntity<BodyMessage> findProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("상품 조회에 실패했습니다."));
        BodyMessage body = BodyMessage.builder()
                .status(StatusEnum.OK)
                .message(ResponseMessage.DB_SELECT_SUCCESS)
                .data(new ProductResponseDTO(product))
                .build();
        return new CustomResponseEntity<>(body, HttpStatus.OK);
    }

    public CustomResponseEntity<BodyMessage> findProductListByMember(Member member) {
        List<Product> productList = productRepository.findAllByMember(member);
        List<ProductResponseDTO> responseDTOList = new ArrayList<>();

        for(Product p : productList){
            ProductResponseDTO dto = new ProductResponseDTO(p);
            responseDTOList.add(dto);
        }

        BodyMessage body = BodyMessage.builder()
                .status(StatusEnum.OK)
                .message(ResponseMessage.DB_SELECT_SUCCESS)
                .data(responseDTOList)
                .build();
        return new CustomResponseEntity<>(body, HttpStatus.OK);
    }
}
