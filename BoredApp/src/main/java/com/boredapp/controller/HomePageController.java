package com.boredapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    
    @GetMapping("")
    public String viewHomePage() {
        return "welcome";
    }
}
