package web.karrot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.karrot.config.annotation.AuthMember;
import web.karrot.controller.dto.ExchangeReqeustDTO;
import web.karrot.controller.dto.response.BodyMessage;
import web.karrot.controller.dto.response.CustomResponseEntity;
import web.karrot.domain.entity.Member;
import web.karrot.service.ExchangeService;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    @PostMapping()
    public CustomResponseEntity<BodyMessage> exchangeAdd(@AuthMember Member member, @RequestBody ExchangeReqeustDTO reqeustDTO){
        return exchangeService.addExchange(member, reqeustDTO);
    }

    @DeleteMapping()
    public CustomResponseEntity<BodyMessage> exchangeRemove(@AuthMember Member member, @RequestBody ExchangeReqeustDTO reqeustDTO){
        return exchangeService.removeExchange(member, reqeustDTO);
    }

    @PutMapping
    public CustomResponseEntity<BodyMessage> exchangeUpdate(@AuthMember Member member, @RequestBody ExchangeReqeustDTO reqeustDTO){
        return exchangeService.updateExchange(member, reqeustDTO);
    }

}
