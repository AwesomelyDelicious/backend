package com.sungkyul.decemberproject.Awesomely_Delicious.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class Restaurant {
    @Id @GeneratedValue
    @Column(name = "restaurant_id")
    private Long restaurant_id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String restaurantName;
    private float x;
    private float y;
    private float starCount;
    private String memo;

}
