package web.karrot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.karrot.controller.dto.SignInRequestDTO;
import web.karrot.domain.entity.Member;
import web.karrot.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public String registerMember(SignInRequestDTO dto) {
        Member member = Member.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .nickname(dto.getNickname())
                .profile(dto.getProfile())
                .build();

        Member newMember = memberRepository.save(member);
        return newMember.getMemberId().toString();
    }
}
