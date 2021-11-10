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

@RequestMapping("favorites")
@SessionAttributes({"user", "user_favorites"})
@Controller
public class FavoritesController {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IncategoryRepository incategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ActivityId_UserIdRepository activityId_userIdRepository;


    @PostMapping("/{activityId}")
    public String deleteFavorite(@PathVariable (name="activityId") Integer activityUserId, Model model){
        ActivityId_UserId toDelete = null;
        ArrayList<ActivityId_UserId> act = (ArrayList<ActivityId_UserId>)activityId_userIdRepository.findAll();
        for(ActivityId_UserId a: act){
            if(a.getActivityid()==activityUserId){
                toDelete = a;
            }
        }
        activityId_userIdRepository.delete(toDelete);
        model.addAttribute("user_favorites", act);
        return "redirect:/favorites";
    }


    @GetMapping
    public Model getUserFavorites(Model model){
       User user = (User) model.asMap().get("user");
       ArrayList<ActivityId_UserId> activity_user = (ArrayList<ActivityId_UserId>)activityId_userIdRepository.findAll();
       ArrayList<ActivityInCategory> user_favorites = new ArrayList<ActivityInCategory>();
       ArrayList<ActivityInCategory> actInCat = initializeActivityInCategory();

       for(ActivityId_UserId act_usr: activity_user){
           if(act_usr.getUserid()==user.getId()){

                Activity a = activityRepository.findById(act_usr.getActivityid()).orElse(new Activity());
                ActivityInCategory aInCat = new ActivityInCategory();
                aInCat.setActivityId(a.getId());
                aInCat.setDescription(a.getDescription());
                aInCat.setCost(a.getCost());
                aInCat.setName(a.getName());

                for(ActivityInCategory activity: actInCat){
                    if(activity.getName().equals(a.getName())){
                        aInCat.setCategory(activity.getCategory());
                        aInCat.setCategoryId(activity.getCategoryId());
                    }
                }

                user_favorites.add(aInCat);
           }
       }
        model.addAttribute("user", user);
        model.addAttribute("user_favorites", user_favorites);
        return model;
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
