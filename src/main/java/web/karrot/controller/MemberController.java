package web.karrot.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.karrot.config.annotation.AuthMember;
import web.karrot.controller.dto.LogInRequestDTO;
import web.karrot.controller.dto.SignInRequestDTO;
import web.karrot.controller.dto.response.BodyMessage;
import web.karrot.controller.dto.response.CustomResponseEntity;
import web.karrot.controller.dto.response.ResponseMessage;
import web.karrot.controller.dto.response.StatusEnum;
import web.karrot.domain.entity.Member;
import web.karrot.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public CustomResponseEntity<BodyMessage> signIn(@RequestBody SignInRequestDTO dto){
        String memberId = memberService.registerMember(dto);
        BodyMessage body = BodyMessage.builder()
                .status(StatusEnum.OK)
                .message(ResponseMessage.CREATED_USER)
                .data(memberId)
                .build();
        return new CustomResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/login")
    public CustomResponseEntity<BodyMessage> logIn(@RequestBody LogInRequestDTO dto){
        return memberService.login(dto);
    }

    @GetMapping("/current")
    public CustomResponseEntity<BodyMessage> findMember(@AuthMember Member member){
        BodyMessage body = BodyMessage.builder()
                .status(StatusEnum.OK)
                .message(ResponseMessage.CREATED_USER)
                .data(member)
                .build();
        return new CustomResponseEntity<>(body, HttpStatus.OK);
    }

}
