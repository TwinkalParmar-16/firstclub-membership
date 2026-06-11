package com.firstclub.membership.dto;

import java.time.LocalDateTime;

/**
 * @param <T>: Standard API response wrapper.
 */
public class ApiResponseWrapper<T> {
    private final boolean success;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;

    private ApiResponseWrapper(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponseWrapper<T> success(String message, T data) {
        return new ApiResponseWrapper<>(true, message, data);
    }

    public static <T> ApiResponseWrapper<T> success(String message) {
        return new ApiResponseWrapper<>(true, message, null);
    }

    public static <T> ApiResponseWrapper<T> error(String message) {
        return new ApiResponseWrapper<>(false, message, null);
    }
}
