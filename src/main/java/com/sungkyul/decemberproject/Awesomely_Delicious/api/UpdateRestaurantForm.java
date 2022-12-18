package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NotNull
public class UpdateRestaurantForm {
    private float star_count;
    private String memo;
}
