package com.example.sentra.expections;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        // You can handle error codes dynamically if needed
        HttpStatus status;
        switch (ex.getErrorCode()) {
            case "USER_NOT_FOUND":
                status = HttpStatus.NOT_FOUND;
                break;
            case "USERNAME_ALREADY_EXISTS":
                status = HttpStatus.CONFLICT;
                break;
            default:
                status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(ex.getMessage(), status);
    }
}
