package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.MemberRepository;
import com.sungkyul.decemberproject.Awesomely_Delicious.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Map;

@Transactional
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final MemberRepository memberRepository;

    /**
     * A-3) [POST] /api/v1/user/new (회원가입)
     * @param form(email, nickname, password, password_re)
     * @return user_Id
     */
    @PostMapping("/user/new")
    public UserIdDto join(@Valid @RequestBody UserForm form) {
        return userService.join(form);
    }



    /**
     * A-2) [GET] /api/v1/user/{userId} (userId로 회원정보 조회)
     * @param userId
     * @return User
     */
    @GetMapping("/user/{userId}")
    public Map<String, Object> getUserInfoByUserId(@PathVariable Long userId) {
        Map<String, Object> result = memberRepository.findUserInfoById(userId);
        return result;
    }

    /**
     * 로그인
     * A-1) [Post] /api/v1/authentication
     * @param email, password
     * @return userId
     */
    @PostMapping("/authentication")
    public UserIdDto getUserId(@RequestParam String email, @RequestParam String password) {
        return memberRepository.findByUserInfo(email, password);
    }
}
