package com.demo.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class maincontroller {
    @GetMapping("")
    public String showHomepage(){
        return "index";
    }
}
