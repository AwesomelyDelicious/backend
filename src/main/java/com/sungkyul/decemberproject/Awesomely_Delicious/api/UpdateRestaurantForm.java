package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter

public class UpdateRestaurantForm {
    @Max(5)
    @Min(1)
    private float star_count;
    @NotEmpty
    @Size(max = 255)
    private String memo;
}
