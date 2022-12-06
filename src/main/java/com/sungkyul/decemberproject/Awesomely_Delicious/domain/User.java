package com.sungkyul.decemberproject.Awesomely_Delicious.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long user_id;
    @OneToMany(mappedBy = "user")
    private List<Restaurant> restaurants = new ArrayList<>();
    private String email;
    private String password;
    @Column(name = "nick_name")
    private String nickname;
}
