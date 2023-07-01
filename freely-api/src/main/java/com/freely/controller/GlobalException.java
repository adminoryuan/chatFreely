package com.freely.controller;

import lombok.extern.slf4j.Slf4j;
import org.freely.commom.core.ApiResponse;
import org.freely.commom.core.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        log.error(ex.getMessage());
        // 在这里处理异常并返回适当的响应
        ApiResponse<String> apiResponse = new ApiResponse<String>(500, ex.getMessage(), null);
        return ResponseEntity.ok(apiResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        // 在这里处理自定义异常并返回适当的响应
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

}
