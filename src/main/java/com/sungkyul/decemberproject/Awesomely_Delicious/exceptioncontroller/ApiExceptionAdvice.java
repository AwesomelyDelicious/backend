package com.sungkyul.decemberproject.Awesomely_Delicious.exceptioncontroller;

import org.hibernate.hql.internal.ast.ErrorReporter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final ApiException e) {
        //e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getError().getCode())
                        .errorMessage(e.getError().getMessage())
                        .build());
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors()
//                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
//        return ResponseEntity.badRequest().body(errors);
//
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final MethodArgumentNotValidException e){
//        return ResponseEntity
//                .status(ExceptionEnum.Incorrected_Email_Format.getStatus())
//                .body(ApiExceptionEntity.builder()
//                        .errorCode(ExceptionEnum.Incorrected_Email_Format.getCode())
//                        .errorMessage(ExceptionEnum.Incorrected_Email_Format.getMessage())
//                        .build());
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final MethodArgumentNotValidException e){
        ApiExceptionEntity exception = vaildExceptionResponse(e.getBindingResult());
        return new ResponseEntity<ApiExceptionEntity>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final AccessDeniedException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }


//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
//        e.printStackTrace();
//        return ResponseEntity
//                .status(ExceptionEnum.RUNTIME_EXCEPTION.getStatus())
//                .body(ApiExceptionEntity.builder()
//                        .errorCode(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
//                        .errorMessage(e.getMessage())
//                        .build());
//    }



//    @ExceptionHandler({Exception.class})
//    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final Exception e) {
//        e.printStackTrace();
//        return ResponseEntity
//                .status(ExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
//                .body(ApiExceptionEntity.builder()
//                        .errorCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
//                        .errorMessage(e.getMessage())
//                        .build());
//    }


    /**@Vaild 예외처리 메서드*/
    private ApiExceptionEntity vaildExceptionResponse(BindingResult bindingResult){
        String code = "";
        String message = "";
        HttpStatus status = null;

        //에러가 있다면
        if(bindingResult.hasErrors()){

            //DTO에 설정한 meaasge값을 가져온다
            message = bindingResult.getFieldError().getDefaultMessage();

            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다
            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (bindResultCode){
                case "Email":
                    code = ExceptionEnum.Incorrected_Email_Format.getCode();
                    message = ExceptionEnum.Incorrected_Email_Format.getMessage();
                    status = ExceptionEnum.Incorrected_Email_Format.getStatus();
                    break;
                case "NotEmpty":
                    code = ExceptionEnum.Empty_Value.getCode();
                    message = ExceptionEnum.Empty_Value.getMessage();
                    status = ExceptionEnum.Empty_Value.getStatus();
                    break;
                case "Max": case "Min" :
                    code = ExceptionEnum.OutOfRange_StarCount.getCode();
                    status = ExceptionEnum.OutOfRange_StarCount.getStatus();
                    message = ExceptionEnum.OutOfRange_StarCount.getMessage() + message;
                    break;
                case "Size" :
                    code = ExceptionEnum.OutOfRange_Memo.getCode();
                    status = ExceptionEnum.OutOfRange_Memo.getStatus();
                    break;

            }
        }
        return new ApiExceptionEntity(status, code, message);
    }
}