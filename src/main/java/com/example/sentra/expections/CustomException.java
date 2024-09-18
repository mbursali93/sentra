package com.example.sentra.expections;

public class CustomException extends RuntimeException {
    
    private String errorCode;
    private String message;

    public CustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
         }

    public String getErrorCode() {
        return errorCode;
    }

}
