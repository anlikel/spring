package com.example.demo.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long id){
        super("cant find order "+id);
    }
}
