package com.sungkyul.decemberproject.Awesomely_Delicious.domain.controller;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class MemberController {

    private final MemberRepository memberRepository;

    }

