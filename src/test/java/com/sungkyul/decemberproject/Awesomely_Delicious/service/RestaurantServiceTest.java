package com.sungkyul.decemberproject.Awesomely_Delicious.service;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.Restaurant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    @Test
    void saveRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName("육꼬");
        restaurant.setX(123.5f);
        restaurant.setY(33.92f);
        restaurant.setMemo("맛있어요");
        restaurant.setStarCount(4.5f);

    }
}