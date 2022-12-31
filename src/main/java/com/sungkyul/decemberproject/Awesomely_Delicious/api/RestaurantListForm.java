package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.Restaurant;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestaurantListForm {
    private String restaurant_name;
    private float x;
    private float y;
    private String restaurant_id;
    private float star_count;
    private String memo;

    public RestaurantListForm(Restaurant restaurant) {
        this.restaurant_name = restaurant.getRestaurantName();
        this.x = restaurant.getX();
        this.y = restaurant.getY();
        this.restaurant_id = restaurant.getRestaurant_id();
        this.star_count = restaurant.getStarCount();
        this.memo = restaurant.getMemo();
    }
}
