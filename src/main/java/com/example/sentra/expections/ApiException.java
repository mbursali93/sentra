package com.example.sentra.expections;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus statusCode;

    public ApiException(String message, String errorCode, HttpStatus statusCode) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
