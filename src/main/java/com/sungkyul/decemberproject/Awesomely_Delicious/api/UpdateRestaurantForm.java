package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter

public class UpdateRestaurantForm {
    @NotEmpty(message = "별점은 1이상, 5이하로 입력해야 합니다.")
    @Max(5)
    @Min(1)
    private float star_count;
    @NotEmpty(message = "빈 문자열은 사용 할 수 없습니다.")
    @Size(max = 255)
    private String memo;
}
