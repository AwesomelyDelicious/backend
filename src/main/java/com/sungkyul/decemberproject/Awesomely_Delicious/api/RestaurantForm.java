package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NotNull
public class RestaurantForm {
    private String restaurant_name;
    private Long user_id;
    private String memo;
    private float x;
    private float y;
    private float star_count;
}
