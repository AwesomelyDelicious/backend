package com.sungkyul.decemberproject.Awesomely_Delicious.repository;

import com.sungkyul.decemberproject.Awesomely_Delicious.api.UserIdDto;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ApiException;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        result.put("restaurant_list", user.getRestaurants());
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
            user = em.createQuery("select m from User m where m.email = : email "
                            + "AND m.password = : password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {throw new ApiException(ExceptionEnum.NonExistent_User);}


        dto.setId(user.getUser_id());
        return dto;
    }
}
