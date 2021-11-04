package com.boredapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


import com.boredapp.model.User;
import com.boredapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import com.boredapp.service.UserAlreadyExistException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class HomePageController {
    @Autowired
    UserService userService;
    
    @GetMapping("")
    public ModelAndView viewHomePage() {
        ModelAndView mv = new ModelAndView("welcome");
        mv.addObject("user", new User());
        return mv;
    }

    @GetMapping("/userhomepage")
    public ModelAndView viewMain() {
        ModelAndView mv = new ModelAndView("userhomepage");
        mv.addObject("user", new User());
        return mv;
    }

    @PostMapping("/userhomepage")
    public ModelAndView processRegister(User user) {
        ModelAndView mv = new ModelAndView("userhomepage");
       if(user.getPassword().equals(user.getRepeatPassword())){
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.register(user);
            mv.addObject("user", user);
         } catch (UserAlreadyExistException e) {
            return new ModelAndView("welcome");
         }
        }else{
            return new ModelAndView("welcome");
        }
        return mv;
    }
}
