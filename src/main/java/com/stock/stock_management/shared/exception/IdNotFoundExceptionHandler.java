package com.stock.stock_management.shared.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IdNotFoundExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Object> handle(IdNotFoundException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
