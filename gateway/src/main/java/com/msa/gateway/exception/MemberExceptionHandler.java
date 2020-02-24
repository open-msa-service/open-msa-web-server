package com.msa.gateway.exception;


import com.msa.gateway.dtos.ResponseMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MemberExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> noSuchElementException(NoSuchElementException ex){
        String errorMessage = getErrorMessage(ex.getMessage(), ex.toString());
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "가입되지 않은 아이디이거나, 비밀번호가 일치하지 않습니다.", errorMessage);
        return new ResponseEntity<Object>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException ex){
        String errorMessage = getErrorMessage(ex.getMessage(), ex.toString());
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "회원가입에 실패 했습니다.", errorMessage);
        return new ResponseEntity<Object>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<Object> httpClientErrorException(HttpClientErrorException ex){
        String errorMessage = getErrorMessage(ex.getMessage(), ex.toString());
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "회원가입에 실패 했습니다.", errorMessage);
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public ResponseEntity<Object> duplicateKeyException(DuplicateKeyException ex){
        String errorMessage = getErrorMessage(ex.getMessage(), ex.toString());
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "아이디가 중복됩니다.", errorMessage);
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    private String getErrorMessage(String message, String s) {
        String errorMessage = message;
        if (StringUtils.isEmpty(errorMessage))
            errorMessage = s;
        return errorMessage;
    }

}
