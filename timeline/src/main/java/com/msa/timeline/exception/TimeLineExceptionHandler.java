package com.msa.timeline.exception;

import com.msa.timeline.dtos.ResponseMessage;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TimeLineExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException ex){
        String errorMessage = getErrorMessage(ex.getMessage(), ex.toString());
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "게시글 작성에 실패했습니다.", errorMessage);
        return new ResponseEntity<Object>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FileUploadException.class})
    public ResponseEntity<Object> fileUploadException(FileUploadException ex){
        String errorMessage = getErrorMessage(ex.getMessage(), ex.toString());
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "파일 업로드에 실패 했습니다.", errorMessage);
        return new ResponseEntity<Object>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    private String getErrorMessage(String message, String s) {
        String errorMessage = message;
        if (StringUtils.isEmpty(errorMessage))
            errorMessage = s;
        return errorMessage;
    }

}
