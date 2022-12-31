package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.Restaurant;

public class RestaurantListForm {
    private String restaurant_name;
    private Long x;
    private Long y;
    private String restaurant_id;
    private Long star_count;
    private String memo;

    public RestaurantListForm(Restaurant restaurant) {
        this.restaurant_name = restaurant_name;
        this.x = x;
        this.y = y;
        this.restaurant_id = restaurant_id;
        this.star_count = star_count;
        this.memo = memo;
    }
}
