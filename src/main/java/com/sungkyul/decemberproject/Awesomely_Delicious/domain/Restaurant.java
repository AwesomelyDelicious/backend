package com.sungkyul.decemberproject.Awesomely_Delicious.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Restaurant {
    @Id @GeneratedValue
    @Column(name = "restaurant_id")
    private Long restaurant_id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String restaurantName;
    private float x;
    private float y;
    private float starCount;
    private String memo;

}
