package com.boredapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.*;
import java.util.stream.Collectors;

import com.boredapp.model.*;
import com.boredapp.repository.*;

@Controller
@SessionAttributes({"user"}) 
public class FavouriteController {
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    ActivityId_UserIdRepository activityId_userRepository;

    

    @GetMapping("/favorites")
    public String  displayFavourites(Model model){
     User user = (User) model.asMap().get("user");
     List<Integer> favorites =activityId_userRepository.findByUserId(user.getId());
     HashSet<Integer>favoriteHashSet =new HashSet<>();

     favorites.stream().forEach(fav->{
        favoriteHashSet.add(fav);
     });;
 
     List<Activity> favourActivities=favoriteHashSet.stream().
     map(id->activityRepository.findById(id).get())
     .collect(Collectors.toList());
 
     model.addAttribute("favorites", favourActivities);
 
         return "favorites";
 
    }

    
    @PostMapping("/favorites/{activityId}")
    public String delete(Model model,@PathVariable(name="activityId") Integer id){
        User user = (User) model.asMap().get("user");
        activityId_userRepository.deleteByUserAndActivity(user.getId(),id);

		return "redirect:/favorites";
        
    }
 
    
}
