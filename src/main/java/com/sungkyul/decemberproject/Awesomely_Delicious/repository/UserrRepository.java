package com.sungkyul.decemberproject.Awesomely_Delicious.repository;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Repository
@Log4j2
@RestController
@RequiredArgsConstructor
public class UserrRepository {

    private final EntityManager em;
    public void save(User user) {em.persist(user);}

    public User findById(Long id) {
        return em.find(User.class,id);
    }

    /**
     * userId를 통해 email, nickname, restaurantlist 조화
     */
    public Map<String, Object> findUserInfoById(Long id) {
        Map<String, Object> result = new HashMap<>();
        User user =  em.find(User.class,id);
        result.put("email", user.getEmail());
        result.put("nick_name", user.getNickname());
        result.put("restaurant_list", user.getRestaurants());
        return result;
    }
}
