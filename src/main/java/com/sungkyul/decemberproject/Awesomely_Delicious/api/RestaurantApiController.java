package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.Restaurant;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.UserRepository;
import com.sungkyul.decemberproject.Awesomely_Delicious.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RestaurantApiController {
    private final RestaurantService restaurantService;

    @PostMapping("/api/v1/restaurant")
    public void addRestaurantV1(@RequestBody RestaurantForm form){
        restaurantService.addRestaurant(form);
    }


    @DeleteMapping("/api/v1/restaurant")
    public void deleteRestaurantV1(@RequestParam(value = "restaurant_id") Long restaurantId){
        restaurantService.deleteRestaurant(restaurantId);
    }
}
