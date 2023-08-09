package com.lightweight.taxiservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/system")
    public String systemPage(){
        return "admin/system";
    }

    @GetMapping("/taxi")
    public String taxiPage(){
        return "user/taxi";
    }
}
