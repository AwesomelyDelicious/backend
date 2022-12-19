package com.sungkyul.decemberproject.Awesomely_Delicious.service;

import com.sungkyul.decemberproject.Awesomely_Delicious.api.UserIdDto;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ExceptionEnum;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ApiException;
import com.sungkyul.decemberproject.Awesomely_Delicious.api.UserForm;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MemberRepository memberRepository;
    private final EntityManager em;

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
        memberRepository.save(user);
        UserIdDto dto = new UserIdDto();
        dto.setId(user.getUser_id());
        return dto;
    }
}