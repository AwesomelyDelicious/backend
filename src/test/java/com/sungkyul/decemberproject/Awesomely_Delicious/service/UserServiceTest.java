package com.sungkyul.decemberproject.Awesomely_Delicious.service;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Member;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
class UserServiceTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;
    @Autowired
    private UserService userService;

    @Test
    void join() {
        User user = new User();
        user.setNickname("유건");
        user.setEmail("you");
        user.setPassword("456");
        Long saveId = userService.join(user);
    }
}