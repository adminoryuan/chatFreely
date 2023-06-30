package com.example.freelyrouter.controller;

import org.freely.commom.core.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

public class BaseController {

    protected <T> ResponseEntity<ApiResponse<T>> successResponse(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>(200, message, data);
        return ResponseEntity.ok(response);
    }
    protected ResponseEntity<ApiResponse<Object>> successResponse(String message) {
        ApiResponse<Object> response = new ApiResponse<>(200, message, null);
        return ResponseEntity.ok(response);
    }

    protected <T> ResponseEntity<ApiResponse<T>> errorResponse(String errorMessage) {
        ApiResponse<T> response = new ApiResponse<>(500, errorMessage, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    protected <T> ResponseEntity<ApiResponse<T>> warningResponse(String warningMessage) {
        ApiResponse<T> response = new ApiResponse<>(400, warningMessage, null);
        return ResponseEntity.badRequest().body(response);
    }

}
