package com.liverpool.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> handleException(Exception ex){
        return Map.of("status","500",
                "errorMessage","INTERNAL SERVER ERROR",
                "message",ex.getMessage()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleIllegalArgumentException(IllegalArgumentException ex){
        return Map.of("status","400",
                "errorMessage","BAD REQUEST",
                "message",ex.getMessage()
        );
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> badFields = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
                FieldError::getField,
                FieldError::getDefaultMessage,
                (existing,duplicate) -> existing
        ));
        return Map.of("status","400",
                "errorMessage","BAD REQUEST",
                "message",badFields
        );
    }
}
