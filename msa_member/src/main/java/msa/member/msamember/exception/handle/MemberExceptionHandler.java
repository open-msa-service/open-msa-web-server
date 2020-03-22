package msa.member.msamember.exception.handle;

import msa.member.msamember.exception.ErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return getErrorMessageResponseEntity(ex.getMessage(), ex.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex) {
        return getErrorMessageResponseEntity(ex.getMessage(), ex.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(NoSuchElementException ex) {
        return getErrorMessageResponseEntity(ex.getMessage(), ex.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleNullPointerException(NullPointerException ex) {
        return getErrorMessageResponseEntity(ex.getMessage(), ex.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileUploadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleFileUploadException(FileUploadException ex) {
        return getErrorMessageResponseEntity(ex.getMessage(), ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessage> getErrorMessageResponseEntity(String message, String s, HttpStatus httpStatus) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTime(LocalDateTime.now());
        errorMessage.setMessage(message);
        errorMessage.setErrorCode(httpStatus.value());
        errorMessage.setDetail(s);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }
}
