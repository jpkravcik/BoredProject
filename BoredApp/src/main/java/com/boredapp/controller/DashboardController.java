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

@RequestMapping("userhomepage")
@SessionAttributes({"user", "category", "cost", "activities"})
@Controller
public class DashboardController {
    
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



    @GetMapping("/cost/{cost}/category/{category}")
    public ModelAndView filter(@PathVariable (name="cost") Integer cost,  @PathVariable (name="category") String category, Model model){
       ModelAndView mv = new ModelAndView("userhomepage");
       ArrayList<Activity> activities= (ArrayList<Activity>) activityRepository.findAll();

       int index = (int)(Math.random() * activities.size());
    

    ArrayList<ActivityInCategory> activityInCategories = initializeActivityInCategory();
    mv.addObject("randomActivity", activityInCategories.get(index));
    activityInCategories.removeIf(activity->(activity.getCost()>cost));
    if(!category.equals("ALL") && !activityInCategories.isEmpty()){
        activityInCategories.removeIf(activity->(!activity.getCategory().equals(category)));
    }

    mv.addObject("cost", cost);
    mv.addObject("category", category);

    User user = (User) model.asMap().get("user");
    mv.addObject("user", user);      
    mv.addObject("activities", activityInCategories);
    return mv;
    }


    
 

    @PostMapping("/{activityId}")
    public String addActivity(@PathVariable (name="activityId") Integer activityId, Model model){
        User user = (User) model.asMap().get("user");
        ArrayList<ActivityId_UserId> activity_user = (ArrayList<ActivityId_UserId>) activityId_userRepository.findAll();
        ActivityId_UserId actUser = new ActivityId_UserId();
        Optional<ActivityId_UserId> alreadyExist=activityId_userRepository.checkExist(user.getId(),activityId);

        
        if(!alreadyExist.isPresent()){
            actUser.setUserid(user.getId());
            actUser.setActivityid(activityId);
            activityId_userRepository.save(actUser);
        }
        

       return "redirect:/gohome";
    }


   

   
    //Initialize session attributes here:
    
    /*@PostMapping
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


        mv.addObject("activities", initializeActivityInCategory());
        mv.addObject("cost", 1000);
        mv.addObject("category", "ALL");
       int index = (int)(Math.random() * activities.size());
        mv.addObject("randomActivity", activities.get(index));

        return mv;
    }*/


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
