package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter @Setter
public class UserForm {
    private String email;
    private String password;
    private String password_re;
    private String nickname;
}
