package web.karrot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;
import web.karrot.config.jwt.JwtTokenProvider;
import web.karrot.controller.dto.LogInRequestDTO;
import web.karrot.controller.dto.SignInRequestDTO;
import web.karrot.controller.dto.response.BodyMessage;
import web.karrot.controller.dto.response.CustomResponseEntity;
import web.karrot.controller.dto.response.ResponseMessage;
import web.karrot.controller.dto.response.StatusEnum;
import web.karrot.domain.entity.Member;
import web.karrot.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public CustomResponseEntity<BodyMessage> registerMember(SignInRequestDTO dto) {

        if(memberRepository.findByEmail(dto.getEmail()).isPresent() || memberRepository.findByPhoneNumber(dto.getPhoneNumber()).isPresent()){
            BodyMessage body = BodyMessage.builder()
                    .status(StatusEnum.BAD_REQUEST)
                    .message(ResponseMessage.EXISTED_USER)
                    .data(dto)
                    .build();
            return new CustomResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        Member member = Member.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .nickname(dto.getNickname())
                .profile(dto.getProfile())
                .build();
        Member newMember = memberRepository.save(member);
        BodyMessage body = BodyMessage.builder()
                .status(StatusEnum.OK)
                .message(ResponseMessage.CREATED_USER)
                .data(member.getMemberId())
                .build();
        return new CustomResponseEntity<>(body, HttpStatus.OK);
    }

    public CustomResponseEntity<BodyMessage> login(LogInRequestDTO requestDTO) {
        Member member = memberRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("???????????? ?????? ???????????????."));

        HttpHeaders headers = new HttpHeaders();
        String accessToken = jwtTokenProvider.createAccessToken(member.getMemberId());
        headers.set("Authorization", accessToken);

        if (accessToken == null) {
            BodyMessage body = BodyMessage.builder()
                    .status(StatusEnum.BAD_REQUEST)
                    .message(ResponseMessage.LOGIN_FAIL)
                    .data("?????? ?????? ??????, ?????? ??????????????????.")
                    .build();
            return new CustomResponseEntity<>(body, HttpStatus.BAD_GATEWAY);
        } else {
            BodyMessage body = BodyMessage.builder()
                    .status(StatusEnum.OK)
                    .message(ResponseMessage.LOGIN_SUCCESS)
                    .data(member.getEmail() + " ????????? ??????")
                    .build();
            return new CustomResponseEntity<>(body, headers, HttpStatus.OK);
        }
    }
}
