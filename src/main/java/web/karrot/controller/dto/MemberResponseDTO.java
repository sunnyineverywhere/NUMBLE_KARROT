package web.karrot.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.karrot.domain.entity.Member;

@Getter
@NoArgsConstructor
public class MemberResponseDTO {
    public String email;
    public String nickname;
    public String profile;
    public String phoneNumber;

    @Builder
    public MemberResponseDTO(Member entity) {
        this.email = entity.getEmail();
        this.nickname = entity.getEmail();
        this.profile = entity.getProfile();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
