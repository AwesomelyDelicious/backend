package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter

public class UpdateRestaurantForm {
    @NotEmpty
    private float star_count;
    @NotEmpty
    private String memo;
}
