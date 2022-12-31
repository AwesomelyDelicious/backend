package com.sungkyul.decemberproject.Awesomely_Delicious.repository;

import com.sungkyul.decemberproject.Awesomely_Delicious.api.RestaurantListForm;
import com.sungkyul.decemberproject.Awesomely_Delicious.api.UserIdDto;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ApiException;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Log4j2
@RestController
@RequiredArgsConstructor
public class MemberRepository  {

    private final EntityManager em;
    public void save(User user) {
        em.persist(user);
    }

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

        List<RestaurantListForm> list = user.getRestaurants().stream().map(r-> new RestaurantListForm(r)).collect(Collectors.toList());

        result.put("restaurant_list", list);
        return result;
    }

    /**
     * 로그인
     * @return User_id 반환
     */
    public UserIdDto findByUserInfo(String email, String password) {
        User user = new User();
        UserIdDto dto = new UserIdDto();

        try {
            user = em.createQuery("SELECT m from User m where m.email = : email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) { //결과값이 없을 때 -> 유저 정보 없음
            throw new ApiException(ExceptionEnum.NonExistent_User);
        } catch (NonUniqueResultException e) { //결과값이 두 개 이상일 때 -> email 중복(Double Check)
            throw new ApiException(ExceptionEnum.Duplicated_Email);
        }
        //DB에 존재하는 password랑 파라미터로 입력된 password 비교, 일치하지 않으면 에러
        if (!user.getPassword().equals(password)) throw new ApiException(ExceptionEnum.Incorrected_Password);
        else dto.setId(user.getUser_id());

        return dto;
    }
}
