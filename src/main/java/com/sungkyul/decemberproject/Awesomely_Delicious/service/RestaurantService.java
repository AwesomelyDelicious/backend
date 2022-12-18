package com.sungkyul.decemberproject.Awesomely_Delicious.service;

import com.sungkyul.decemberproject.Awesomely_Delicious.api.RestaurantForm;
import com.sungkyul.decemberproject.Awesomely_Delicious.api.UpdateRestaurantForm;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.Restaurant;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ApiException;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ExceptionEnum;
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
        if (user== null) throw new ApiException(ExceptionEnum.NonExistent_User);
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(form.getRestaurant_name());
        if(form.getMemo().length() > 255) throw new ApiException(ExceptionEnum.Memo_OutOfRange);
        restaurant.setMemo(form.getMemo());
        restaurant.setX(form.getX());
        restaurant.setY(form.getY());
        if(form.getStar_count() >5 || form.getStar_count() <= 0) throw new ApiException(ExceptionEnum.StarCount_OutOfRange);
        restaurant.setStarCount(form.getStar_count());
        restaurant.setUser(user);
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant== null) throw new ApiException(ExceptionEnum.NonExistent_Restaurant);
        restaurantRepository.deleteById(restaurantId);
    }

    public void updateRestaurant(UpdateRestaurantForm form, Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant== null) throw new ApiException(ExceptionEnum.NonExistent_Restaurant);
        if(form.getMemo().length() > 255) throw new ApiException(ExceptionEnum.Memo_OutOfRange);
        restaurant.setMemo(form.getMemo());
        if(form.getStar_count() >5 || form.getStar_count() <= 0) throw new ApiException(ExceptionEnum.StarCount_OutOfRange);
        restaurant.setStarCount(form.getStar_count());
    }

}
