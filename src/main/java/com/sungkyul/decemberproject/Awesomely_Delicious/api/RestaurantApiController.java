package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.Restaurant;
import com.sungkyul.decemberproject.Awesomely_Delicious.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RestaurantApiController {
    private final RestaurantService restaurantService;

    @PostMapping("/api/v1/restaurant")
    public void addRestaurantV1(@Valid @RequestBody RestaurantForm form){
        restaurantService.addRestaurant(form);
    }


    @DeleteMapping("/api/v1/restaurant")
    public void deleteRestaurantV1(@RequestParam(value = "restaurant_id") String restaurantId){ //restaurant_id를 String으로 바꾸고 오류나지 않게 수정
        restaurantService.deleteRestaurant(restaurantId);
    }

    @PatchMapping("/api/v1/restaurant")
    public void updateRestaurantV1(@Valid @RequestBody UpdateRestaurantForm form,
                                       @RequestParam(value = "restaurant_id") String restaurantId){
        restaurantService.updateRestaurant(form,restaurantId);
    }

}
