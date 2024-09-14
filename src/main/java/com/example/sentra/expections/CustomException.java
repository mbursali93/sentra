package com.example.sentra.expections;

public class CustomException extends RuntimeException {
    
    private String errorCode;

    public CustomException(String message, String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
