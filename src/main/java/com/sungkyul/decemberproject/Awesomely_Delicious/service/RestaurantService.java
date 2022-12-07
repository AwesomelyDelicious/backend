package com.sungkyul.decemberproject.Awesomely_Delicious.service;

import com.sungkyul.decemberproject.Awesomely_Delicious.api.RestaurantForm;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.Restaurant;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.MemberRepository;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService {
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    public void addRestaurant(RestaurantForm form){
        User user = memberRepository.findById(form.getUser_id());

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(form.getRestaurant_name());
        restaurant.setMemo(form.getMemo());
        restaurant.setX(form.getX());
        restaurant.setY(form.getY());
        restaurant.setStarCount(form.getStar_count());
        restaurant.setUser(user);

        restaurantRepository.save(restaurant);
    }


}
