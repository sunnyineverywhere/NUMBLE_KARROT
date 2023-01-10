package web.karrot.controller.dto;

import lombok.Getter;

@Getter
public class SignInRequestDTO {

    private String email;
    private String password;
    private String phoneNumber;
    private String nickname;
    private String profile;

}
