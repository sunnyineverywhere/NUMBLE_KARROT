package web.karrot.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNumber;

    @Column
    private String nickname;

    @Column
    private String profile;

    @Builder
    public Member(Long memberId, String email, String password, String phoneNumber, String nickname, String profile) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.profile = profile;
    }

}
