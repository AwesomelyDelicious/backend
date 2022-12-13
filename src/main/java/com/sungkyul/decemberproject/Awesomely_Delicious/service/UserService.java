package com.sungkyul.decemberproject.Awesomely_Delicious.service;

import com.sungkyul.decemberproject.Awesomely_Delicious.api.UserForm;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MemberRepository memberRepository;

    public Long join(UserForm form){
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setNickname(form.getNickname());
        memberRepository.save(user);
        return user.getUser_id();
    }
}