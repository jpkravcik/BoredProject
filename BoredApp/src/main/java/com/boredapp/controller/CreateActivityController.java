package com.boredapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.boredapp.model.*;
import com.boredapp.repository.*;
import com.boredapp.service.IncategoryService;

@Controller
@SessionAttributes({"user","originalActivity"})
public class CreateActivityController {
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    IncategoryRepository incategoryRepository;
    @Autowired
    IncategoryService incategoryService;
    @Autowired
    UserActivityRepository userActivityRepository;

    @GetMapping("/createactivity")
    public String createActivities(Model model){
        //retrieve all the categories
        List<Category> listCategories=(ArrayList<Category>)categoryRepository.findAll();
        //get the user from the session key
        User user =(User)model.asMap().get("user");
        //find all the userActivities
        List<UserActivity> userActivities=userActivityRepository.findByUser(user);
        //get all the activities from the user activities
        List<Activity> myActivities=userActivities.stream().map(a->a.getActivity()).collect(Collectors.toList());

        //add the objects to the template 
        model.addAttribute("myActivities", myActivities);
        //custom activity contains a 
        model.addAttribute("customactivity",new  CustomActivity());
        model.addAttribute("listcategories",listCategories);

        return "createactivity";
        
    }


    @PostMapping("/saveactivity")
    public String saveActivity(Model model,@ModelAttribute(name="customactivity") CustomActivity customActivity){
        
        
        Activity activity=customActivity.getActivity();
        System.out.println(activity.getDescription());

        if(activity!=null){
            activityRepository.save(activity);
        }
        List<Category> categories=customActivity.getCategories();

        Activity retrieveActivity=activityRepository.findByName(activity.getName());
        System.out.println(retrieveActivity);

        categories.stream().forEach(cat->{
            Incategory incategory=new Incategory();
            incategory.setActivity(retrieveActivity);
            incategory.setCategory(cat);
            incategoryService.save(incategory);
        });
        
        User user =(User)model.asMap().get("user");

        UserActivity userActivity=new UserActivity();
        userActivity.setUser(user);
        userActivity.setActivity(retrieveActivity);
        userActivityRepository.save(userActivity);
        

        return "redirect:/createactivity";
        
    }

    @PostMapping("/deleteactivity/{activityid}")
    public String deleteActivity(Model model, @PathVariable(name="activityid") Integer id){


        Activity activity=activityRepository.findById(id).get();
        userActivityRepository.deleteByActivity(activity);
        activityRepository.delete(activity);
		return "redirect:/createactivity";

    }

    @GetMapping("/editactivity/{id}")
    public String editActivity(Model model, @PathVariable(name="id") Integer id){

        Activity activity=activityRepository.findById(id).get();
        Activity originalActivity=activityRepository.findById(id).get();
        model.addAttribute("originalActivity", originalActivity);

        model.addAttribute("activity", activity);

        List<Category> incat=new ArrayList<>();

        model.addAttribute("incat", incat);

        List<Category> listCategories=(ArrayList<Category>)categoryRepository.findAll();
        model.addAttribute("listcategories",listCategories);
		return "editactivity";

    }


    @PostMapping("/updateactivity")
    public String updateActivity(Model model ,@ModelAttribute(name="activity") Activity activity,HttpServletRequest request){
        User user =(User)model.asMap().get("user");
        String[] detailName=request.getParameterValues("categories");

        Activity originalActivity =(Activity)model.asMap().get("originalActivity");
        userActivityRepository.deleteByActivity(originalActivity);
        activityRepository.save(activity);


        UserActivity userActivity=new UserActivity();
        userActivity.setUser(user);
        userActivity.setActivity(activity);
        


        userActivityRepository.save(userActivity);
        

        return "redirect:/createactivity";

    }


    
}
