package com.boredapp.controller;

import com.boredapp.repository.TransportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"user"})
@Controller
public class TransportController {
    @Autowired
    TransportRepository transportRepository;

    @GetMapping("/transport")
    public String viewTransport(Model model){
        model.addAttribute("transports", transportRepository.findAll());
        return "transport";

    }
    
}
