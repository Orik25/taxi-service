package com.lightweight.taxiservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Hello {

    @GetMapping("/")
    public String helloPage(){
        return "hello";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
