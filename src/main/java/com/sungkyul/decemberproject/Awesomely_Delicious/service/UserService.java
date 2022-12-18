package com.sungkyul.decemberproject.Awesomely_Delicious.service;

import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ExceptionEnum;
import com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller.ApiException;
import com.sungkyul.decemberproject.Awesomely_Delicious.api.UserForm;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MemberRepository memberRepository;
    private final EntityManager em;

    public Long join(UserForm form){

        User user = new User();
        List<User> result = em.createQuery("select m from User m where m.email = : email", User.class)
                .setParameter("email", form.getEmail())
                .getResultList();
        if (!result.isEmpty()) throw new ApiException(ExceptionEnum.Duplicated_Email);
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setNickname(form.getNickname());
        memberRepository.save(user);
        return user.getUser_id();
    }
}