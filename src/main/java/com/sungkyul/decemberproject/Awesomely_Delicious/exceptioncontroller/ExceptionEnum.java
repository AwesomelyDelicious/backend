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
    Incorrected_Email_Format(HttpStatus.BAD_REQUEST, "400", "잘못된 이메일 형식입니다."),
    NonExistent_Restaurant(HttpStatus.NOT_FOUND, "404", "맛집 리스트가 존재하지 않습니다"),
    NonExistent_User(HttpStatus.NOT_FOUND, "404", "사용자가 존재하지 않습니다."),
    OutOfRange_Memo(HttpStatus.NOT_FOUND, "404", "메모에 작성할 수 있는 글자 수를 초과했습니다. "),
    OutOfRange_StarCount(HttpStatus.NOT_FOUND, "404", "설정할 수 있는 별점의 범위를 이탈했습니다. 별점은 "),
    Empty_Value(HttpStatus.NOT_FOUND, "404", "빈 문자열은 사용 할 수 없습니다."),

    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001");

//    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"),
//    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
//    SECURITY_01(HttpStatus.UNAUTHORIZED, "S0001", "권한이 없습니다."),
//    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002");


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