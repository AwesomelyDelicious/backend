package com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 오류 메시지 커스텀
 */
@Getter
@ToString
public enum ExceptionEnum {

    Duplicated_Email(HttpStatus.BAD_REQUEST, "400", "이미 존재하는 Email입니다."),
    Incorrected_Password(HttpStatus.BAD_REQUEST, "400", "비밀번호가 일치하지 않습니다."),
    NonExistent_Restaurant(HttpStatus.NOT_FOUND, "404", "맛집 리스트가 존재하지 않습니다"),
    NonExistent_User(HttpStatus.NOT_FOUND, "404", "사용자가 존재하지 않습니다."),
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
    SECURITY_01(HttpStatus.UNAUTHORIZED, "S0001", "권한이 없습니다.");


    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}