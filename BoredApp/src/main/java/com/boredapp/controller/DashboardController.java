package com.boredapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import com.boredapp.model.Activity;
import com.boredapp.model.Incategory;
import com.boredapp.model.User;
import com.boredapp.repository.ActivityRepository;
import com.boredapp.repository.IncategoryRepository;
import com.boredapp.repository.UserRepository;
import com.boredapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import com.boredapp.service.UserAlreadyExistException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@RequestMapping("userhomepage")
@SessionAttributes({"user"})
@Controller
public class DashboardController {
    
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserService userService;

    @Autowired
    IncategoryRepository incategoryRepository;

    
    @GetMapping("/cost/{value}")
    public ModelAndView filterByCost(@PathVariable (name="value") Integer value, Model model){
        ModelAndView mv = new ModelAndView("userhomepage");
       ArrayList<Activity> activities=(ArrayList<Activity>) activityRepository.findAll();
       int index = (int)(Math.random() * activities.size());
        mv.addObject("randomActivity", activities.get(index));

    activities.removeIf(activity->(activity.getCost()>value));
    User user = (User) model.asMap().get("user");
    mv.addObject("user", user);      
    mv.addObject("activities", activities);
        mv.addObject("value", value);
       return mv;

    }
    
    @GetMapping
    public ModelAndView viewMain(Model model) {
        ModelAndView mv = new ModelAndView("userhomepage");
        User user = (User) model.asMap().get("user");
        mv.addObject("user", user);
        mv.addObject("value", 1000);
        ArrayList<Activity> activities = (ArrayList<Activity>) activityRepository.findAll();
        mv.addObject("activities", activities);
        int index = (int)(Math.random() * activities.size());
        mv.addObject("randomActivity", activities.get(index));
        return mv;
    }
    

    @PostMapping
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
        ArrayList<Activity> activities=(ArrayList<Activity>) activityRepository.findAll();
       int index = (int)(Math.random() * activities.size());
        mv.addObject("randomActivity", activities.get(index));
        mv.addObject("value", 1000);
        mv.addObject("activities",activityRepository.findAll());

        return mv;
    }

 
}
