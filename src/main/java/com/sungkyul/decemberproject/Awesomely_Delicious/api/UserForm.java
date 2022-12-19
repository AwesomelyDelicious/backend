package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class UserForm {

    @NotNull
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    private String password;
    private String password_re;
    private String nickname;
}
