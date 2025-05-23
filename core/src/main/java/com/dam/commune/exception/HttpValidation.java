package com.dam.commune.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpValidation {
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationExceptions(jakarta.validation.ConstraintViolationException ex) {
        StringBuilder message = new StringBuilder();
        ex.getConstraintViolations().forEach(violation -> message.append(violation.getMessage()).append("\n"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.toString());
    }
}
