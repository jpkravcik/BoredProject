package com.boredapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

import com.boredapp.model.*;
import com.boredapp.nosql.*;
import com.boredapp.repository.*;
import com.boredapp.service.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.boredapp.service.UserAlreadyExistException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.*;

@RequestMapping("singleActivity")
@SessionAttributes({"user", "activities"})
@Controller
public class SingleActivityController {
        
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserService userService;

    @Autowired
    IncategoryRepository incategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ActivityId_UserIdRepository activityId_userRepository;



    @GetMapping("/{activityId}")
    public ModelAndView filter(@PathVariable (name="activityId") Integer activityId, Model model){
    
    ModelAndView mv = new ModelAndView("singleActivity");
    ActivityInCategory activity = null;
    ArrayList <ActivityInCategory> activities = (ArrayList <ActivityInCategory>) model.asMap().get("activities");
    for(ActivityInCategory act: activities){
        if(act.getActivityId()== activityId){
            activity = act;
            break;
        }
    }
    mv.addObject("activity", activity);
    User user = (User) model.asMap().get("user");
    mv.addObject("user", user);      
    return mv;
    }
}
