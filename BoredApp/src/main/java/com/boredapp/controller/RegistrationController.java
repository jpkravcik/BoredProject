package com.boredapp.controller;

import java.util.ArrayList;

import com.boredapp.model.*;
import com.boredapp.model.User;
import com.boredapp.repository.*;
import com.boredapp.service.UserAlreadyExistException;
import com.boredapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"user"})
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    ActivityRepository activityRepository;

    

    @Autowired
    IncategoryRepository incategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ActivityId_UserIdRepository activityId_userRepository;


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

        /*ArrayList<Activity> activities=(ArrayList<Activity>) activityRepository.findAll();


        model.addAttribute("activities", initializeActivityInCategory());
        model.addAttribute("cost", 1000);
        model.addAttribute("category", "ALL");
        int index = (int)(Math.random() * activities.size());
        model.addAttribute("randomActivity", activities.get(index));*/
         
        //return "userhomepage";
        return "redirect:/gohome";
    }

    
}
