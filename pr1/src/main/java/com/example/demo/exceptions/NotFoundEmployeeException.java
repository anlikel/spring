package com.example.demo.exceptions;

public class NotFoundEmployeeException extends RuntimeException {
    public NotFoundEmployeeException(Long id){
        super("cant find employee "+id);
    }
}
