package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/controller1")
public class Controller1 {

    @GetMapping("/hallo")
    public String returnHallo(){
        return "index";
    }

}
