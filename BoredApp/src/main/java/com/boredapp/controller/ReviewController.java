package com.boredapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.boredapp.model.*;
import com.boredapp.nosql.AppReview;
import com.boredapp.repository.*;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@SessionAttributes({"user","act","rating","rev"})

public class ReviewController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    AppReviewRepository appReviewRepository;


    @GetMapping("/review")
    public String review(Model model){
        return "review";
    }

    @GetMapping("/mybooking")
    public String reviewBooking(Model model){
        User user =(User)model.asMap().get("user");

        List<Booking> userBooking=bookingRepository.getBookings(user.getId());

        List<Activity> activities=userBooking.stream().map(booking->booking.getActivity()).collect(Collectors.toList());


        model.addAttribute("activities", activities);

        return "review1";
        
    }

    @GetMapping("/allactivities")
    public String reviewActivity(Model model){
    
        List<Activity> activities=activityRepository.findAll();
        model.addAttribute("activities", activities);

        return "review1";
        
    }

    @GetMapping("/myreview/{id}")
    public String leaveReview(@PathVariable(name="id") Integer id,Model model){
        
        Activity activity=activityRepository.findById(id).get();
        model.addAttribute("activity", activity);


       
       
        Review review=new Review();
        
        model.addAttribute("act", activity);
        model.addAttribute("rev", review);
        model.addAttribute("rating", new Ratings());

        ReviewDto reviewDto=new ReviewDto();
        model.addAttribute("reviewDto", reviewDto);

        int[] stars=new int[5];
        stars[0]=1;
        stars[1]=2;
        stars[2]=3;
        stars[3]=4;
        stars[4]=5;

        model.addAttribute("stars", stars);

        return "leavereview";
        
    }


    @PostMapping("/savereview")
    public String saveReview(Model model,@ModelAttribute("reviewDto") ReviewDto reviewDto){
        User user =(User)model.asMap().get("user");
        Review review=(Review)model.asMap().get("rev");
        Ratings rating=(Ratings)model.asMap().get("rating");

        Activity activity=(Activity)model.asMap().get("act");
        
        if(reviewDto.getRating()!=0){
            rating.setActivity(activity);
            rating.setUser(user);
            rating.setRating(reviewDto.getRating());
            ratingRepository.save(rating);
        }
        if(!reviewDto.getReview().equals(null)){
            review.setReview(reviewDto.getReview());
            review.setUser(user);
            review.setActivity(activity);
            reviewRepository.save(review);
        }
       

        return "redirect:/review";

    }



    @GetMapping("/appreview")
    public String reviewApp(Model model){
       
        AppReview appReview=new AppReview();
       model.addAttribute("stars", List.of(1,2,3,4,5));
        model.addAttribute("appReview", appReview);
        model.addAttribute("reviews", appReviewRepository.findAll());

		return "appreview";
        
    }

    @PostMapping("/saveappreview")
    public String saveAppReview(Model model, @ModelAttribute(name="appReview") AppReview appReview){
        User user =(User)model.asMap().get("user");
        appReview.setUserEmail(user.getEmail());
        appReview.setUserName(user.getFirstName()+" "+user.getLastName());
        appReviewRepository.save(appReview);
		return "redirect:/appreview";

    }

    
}
