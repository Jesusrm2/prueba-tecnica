package com.jesus.pruebatec.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHanding {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handelValidation (MethodArgumentNotValidException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        List<String> errors = new ArrayList<>();
        List<FieldError> fieldError =  exception.getFieldErrors();

        for (FieldError fe:fieldError){
            errors.add(fe.getDefaultMessage());
        }
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }
}
