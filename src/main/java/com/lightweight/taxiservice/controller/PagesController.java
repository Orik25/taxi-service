package com.lightweight.taxiservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String helloPage(){
        return "hello";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
