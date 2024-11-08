package com.example.sentra.exceptions;

public class CustomException extends RuntimeException {
    
    private String errorCode;

    public CustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
         }

    public String getErrorCode() {
        return errorCode;
    }

}