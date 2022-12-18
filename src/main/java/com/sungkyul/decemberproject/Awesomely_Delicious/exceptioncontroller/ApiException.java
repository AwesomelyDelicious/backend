package com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller;

import lombok.Getter;

/**
 * 에러 구현
 */
@Getter
public class ApiException extends RuntimeException {
    private ExceptionEnum error;

    public ApiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}