package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter

public class RestaurantForm {
    @NotEmpty
    private String restaurant_name;
    private Long user_id;
    @NotEmpty
    private String memo;
    private float x;
    private float y;
    private float star_count;
}



