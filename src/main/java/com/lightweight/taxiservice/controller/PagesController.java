package com.lightweight.taxiservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagesController {

    @GetMapping("/")
    public String helloPage(){
        return "hello";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam (name = "successRegister",defaultValue = "false") boolean success,
                            Model model){
        model.addAttribute("success" ,success);
        return "login";
    }

    @GetMapping("/taxi")
    public String taxiPage(){
        return "user/taxi";
    }
}
