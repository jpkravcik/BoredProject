package com.boredapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.boredapp.model.*;
import com.boredapp.service.*;
import com.boredapp.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.*;

import com.boredapp.model.Activity;


@Controller
@SessionAttributes({"user", "activities"})

public class HomePageController {
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

    @GetMapping("")
    public String viewHomePage() {
        return "redirect:/login";
    }

    @GetMapping("/gohome")
    public String viewUserHomePage(Model model) {
      

        ArrayList<Activity> activities=(ArrayList<Activity>) activityRepository.findAll();
        ArrayList<ActivityInCategory> catact = initializeActivityInCategory();

        model.addAttribute("activities", catact);
        model.addAttribute("cost", 1000);
        model.addAttribute("category", "ALL");
        int index = (int)(Math.random() * catact.size());
        model.addAttribute("randomActivity", catact.get(index));

        
        return "userhomepage";
    }

    public ArrayList<ActivityInCategory> initializeActivityInCategory(){

        //Create activityInCategoryList
        ArrayList<Category> cat = (ArrayList<Category>) categoryRepository.findAll();

        ArrayList<ActivityInCategory> list = new ArrayList<ActivityInCategory>();
        for(Category c: cat){
            ArrayList<String> str = (ArrayList<String>)incategoryRepository.FindByCategory_name(c.getName());
            
            for(String s: str){
                Activity a = activityRepository.findByName(s);
                ActivityInCategory aInCat = new ActivityInCategory();
                aInCat.setActivityId(a.getId());
                aInCat.setCategory(c.getName());
                aInCat.setCategoryId(c.getId());
                aInCat.setDescription(a.getDescription());
                aInCat.setCost(a.getCost());
                aInCat.setName(a.getName());

                list.add(aInCat);
            }
        }
        return list;


    }


}
