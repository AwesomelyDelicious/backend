package com.sungkyul.decemberproject.Awesomely_Delicious.controller;

import com.sungkyul.decemberproject.Awesomely_Delicious.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberRepository memberRepository;

    }

