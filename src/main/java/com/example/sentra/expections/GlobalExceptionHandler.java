package com.example.sentra.expections;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.sentra.base.ApiResponse;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<String>> handleApiException(ApiException ex) {
        ApiResponse<String> response = ApiResponse.failure(
            ex.getMessage(), 
            HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

   @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponse<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    StringBuilder errorMessage = new StringBuilder();

    ex.getBindingResult().getFieldErrors().forEach(error -> 
        errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(", ")
    );

    if (errorMessage.length() > 0) {
        errorMessage.setLength(errorMessage.length() - 2);
    }

    ApiResponse<String> response = ApiResponse.failure(
        errorMessage.toString(), 
        HttpStatus.BAD_REQUEST.value()
    );
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        ApiResponse<String> response = ApiResponse.failure(
            "An unexpected error occurred: " + ex.getMessage(), 
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
