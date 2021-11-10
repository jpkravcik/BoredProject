package com.boredapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import com.boredapp.model.Activity;
import com.boredapp.model.ActivityId_UserId;
import com.boredapp.model.ActivityInCategory;
import com.boredapp.model.Category;
import com.boredapp.model.Incategory;
import com.boredapp.model.SessionData;
import com.boredapp.model.User;
import com.boredapp.repository.ActivityId_UserIdRepository;
import com.boredapp.repository.ActivityRepository;
import com.boredapp.repository.CategoryRepository;
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
@SessionAttributes({"user", "category", "cost"})
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
    mv.addObject("randomActivity", activities.get(index));

    ArrayList<ActivityInCategory> activityInCategories = initializeActivityInCategory();
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


    
    @GetMapping
    public ModelAndView viewMain(Model model) {
        ModelAndView mv = new ModelAndView("userhomepage");
        User user = (User) model.asMap().get("user");
        if(user==null){
            user = new User();
            user.setFirstName("Marija");
            user.setId(100);
        }

        mv.addObject("cost", 1000);
        mv.addObject("category", "ALL");
        mv.addObject("user", user);
        mv.addObject("value", 1000);
        ArrayList<Activity> activities = (ArrayList<Activity>) activityRepository.findAll();

        mv.addObject("activities", initializeActivityInCategory());

        int index = (int)(Math.random() * activities.size());
        mv.addObject("randomActivity", activities.get(index));
        return mv;
    }
    

    @PostMapping("/{activityId}")
    public String addActivity(@PathVariable (name="activityId") Integer activityId, Model model){
        User user = (User) model.asMap().get("user");
        ArrayList<ActivityId_UserId> activity_user = (ArrayList<ActivityId_UserId>) activityId_userRepository.findAll();
        ActivityId_UserId actUser = new ActivityId_UserId();
        actUser.setUserid(user.getId());
        actUser.setActivityid(activityId);
        activityId_userRepository.save(actUser);

       return "redirect:/userhomepage";
    }

    //Initialize session attributes here:
    
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


        mv.addObject("activities", initializeActivityInCategory());
        mv.addObject("cost", 1000);
        mv.addObject("category", "ALL");
       int index = (int)(Math.random() * activities.size());
        mv.addObject("randomActivity", activities.get(index));

        return mv;
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
