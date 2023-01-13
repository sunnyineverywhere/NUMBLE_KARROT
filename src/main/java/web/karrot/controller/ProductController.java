package web.karrot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.karrot.config.annotation.AuthMember;
import web.karrot.controller.dto.ProductAddRequestDTO;
import web.karrot.controller.dto.response.BodyMessage;
import web.karrot.controller.dto.response.CustomResponseEntity;
import web.karrot.domain.entity.Member;
import web.karrot.service.ProductService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public CustomResponseEntity<BodyMessage> productAdd(@AuthMember Member member, @RequestBody ProductAddRequestDTO requestDTO) {
        return productService.addProduct(member, requestDTO);
    }

    @GetMapping("/all")
    public CustomResponseEntity<BodyMessage> productList() {
        return productService.findProductList();
    }

    @GetMapping()
    public CustomResponseEntity<BodyMessage> productFind(@RequestParam Long id){
        return productService.findProduct(id);
    }

    @GetMapping("/user")
    public CustomResponseEntity<BodyMessage> productListFindByMember(@AuthMember Member member){
        return productService.findProductListByMember(member);
    }
}
