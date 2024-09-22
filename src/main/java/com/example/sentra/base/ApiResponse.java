package com.example.sentra.base;



public class ApiResponse<T> {

    private boolean success;
    private T data;
    private String errorMessage;
    private int statusCode;

    // Success Response Constructor
    public ApiResponse(boolean success, T data, int statusCode) {
        this.success = success;
        this.data = data;
        this.statusCode = statusCode;
    }

    // Failure Response Constructor
    public ApiResponse(boolean success, String errorMessage, int statusCode) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    // Static method for success
    public static <T> ApiResponse<T> success(T data, int statusCode) {
        return new ApiResponse<>(true, data, statusCode);
    }

    // Static method for failure
    public static ApiResponse<String> failure(String errorMessage, int statusCode) {
        return new ApiResponse<>(false, errorMessage, statusCode);
    }
}
