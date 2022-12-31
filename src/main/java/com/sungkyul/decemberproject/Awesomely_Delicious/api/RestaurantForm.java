package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter

public class RestaurantForm {
    @NotEmpty
    @Size(max = 255, message = "맛집 이름으로 설정 할 수 있는 글자 수를 초과하였습니다. 크기가 0에서 255 사이여야 합니다.")
    private String restaurant_name;
    private Long user_id;
    @NotEmpty
    @Size(max = 255, message = "메모에 작성 할 수 있는 글자 수를 초과했습니다. 크기가 0에서 255 사이여야 합니다.")
    private String memo;
    private float x;
    private float y;
    @Max(5)
    @Min(1)
    private float star_count;
    private String restaurant_id;
}



