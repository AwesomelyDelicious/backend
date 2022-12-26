package com.sungkyul.decemberproject.Awesomely_Delicious.service;

import com.sungkyul.decemberproject.Awesomely_Delicious.api.UserIdDto;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ExceptionEnum;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ApiException;
import com.sungkyul.decemberproject.Awesomely_Delicious.api.UserForm;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.UserrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserrRepository userrRepository;
    private final EntityManager em;

    /** 회원가입 */
    public UserIdDto join(UserForm form){

        if (!form.getPassword().equals(form.getPassword_re())) throw new ApiException(ExceptionEnum.Incorrected_Password);

        List<User> result = em.createQuery("select m from User m where m.email = : email", User.class)
                .setParameter("email", form.getEmail())
                .getResultList();

        if (!result.isEmpty()) throw new ApiException(ExceptionEnum.Duplicated_Email);

        User user = new User();
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setNickname(form.getNickname());
        userrRepository.save(user);

        UserIdDto dto = new UserIdDto();
        dto.setId(user.getUser_id());
        return dto;
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