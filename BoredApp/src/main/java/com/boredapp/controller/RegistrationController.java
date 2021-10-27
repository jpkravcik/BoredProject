package com.boredapp.controller;

import com.boredapp.model.User;
import com.boredapp.service.UserAlreadyExistException;
import com.boredapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
     
    return "register";
    }


    @PostMapping("/process_register")
    public String processRegister(User user,Model model) {
        
       if(user.getPassword().equals(user.getRepeatPassword())){
            try {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
            userService.register(user);
            model.addAttribute("user", user);
         } catch (UserAlreadyExistException e) {
             //TODO: handle exception
             model.addAttribute("error", e.getMessage());
             return "register";
         }
        }else{
            model.addAttribute("password","Passwords dont match");
            return "register";
        }
         
        return "userhomepage";
    }
}
