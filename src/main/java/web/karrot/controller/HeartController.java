package web.karrot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.karrot.config.annotation.AuthMember;
import web.karrot.controller.dto.HeartRequestDTO;
import web.karrot.controller.dto.response.BodyMessage;
import web.karrot.controller.dto.response.CustomResponseEntity;
import web.karrot.domain.entity.Member;
import web.karrot.service.HeartService;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public CustomResponseEntity<BodyMessage> addHeart(@AuthMember Member member, @RequestBody HeartRequestDTO requestDTO){
        return heartService.heartAdd(member, requestDTO.getProductId());
    }

    

}
