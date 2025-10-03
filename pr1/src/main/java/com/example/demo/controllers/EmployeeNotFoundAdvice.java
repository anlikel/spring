package com.example.demo.controllers;

import com.example.demo.exceptions.NotFoundEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeNotFoundAdvice {

    @ExceptionHandler(NotFoundEmployeeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(NotFoundEmployeeException e){
        return e.getMessage();
    }
}
