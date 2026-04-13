package com.liverpool.aggregator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(HttpClientErrorException.NotFound e) {
        return Map.of(
                "status","404",
                "error","NOT FOUND",
                "message", e.getMessage()
        );
    }

    @ExceptionHandler(HttpClientErrorException.Conflict.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleConflict(HttpClientErrorException.Conflict e) {
        return Map.of(
                "status","409",
                "error","CONFLICT",
                "message", e.getMessage()
        );
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBadRequest(HttpClientErrorException.BadRequest e) {
        return Map.of(
                "status",  "400",
                "error",   "BAD REQUEST",
                "message", e.getMessage()
        );
    }

    @ExceptionHandler(HttpClientErrorException.UnprocessableContent.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public Map<String, String> handleUnprocessable(HttpClientErrorException.UnprocessableContent e) {
        return Map.of(
                "status",  "422",
                "error",   "UNPROCESSABLE CONTENT",
                "message", e.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(MethodArgumentNotValidException e) {
        Map<String, String> fieldErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, duplicate) -> existing
                ));
        return Map.of(
                "status",   "400",
                "error",    "BAD REQUEST",
                "messages", fieldErrors
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGeneral(Exception e) {
        return Map.of(
                "status",  "500",
                "error",   "INTERNAL SERVER ERROR",
                "message", "Error inesperado"
        );
    }
}
