package com.murebackend.murebackend.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleException(HttpMessageNotReadableException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "The body empty, you can't do that :(");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
