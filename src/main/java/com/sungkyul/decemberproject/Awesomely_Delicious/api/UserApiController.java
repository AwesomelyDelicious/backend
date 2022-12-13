package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.MemberRepository;
import com.sungkyul.decemberproject.Awesomely_Delicious.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Transactional
@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/v1/user/new")
    public Long login(@RequestBody UserForm form) {
        return userService.join(form);
    }
}
