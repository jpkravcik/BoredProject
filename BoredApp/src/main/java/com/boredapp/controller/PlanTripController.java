package com.boredapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Generated;
import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;

import com.boredapp.model.Activity;
import com.boredapp.model.AutomatedTrip;
import com.boredapp.model.Booking;
import com.boredapp.model.BookingDto;
import com.boredapp.model.Category;
import com.boredapp.model.City;

import com.boredapp.model.User;
import com.boredapp.repository.ActivityRepository;
import com.boredapp.repository.BookingRepository;
import com.boredapp.repository.CategoryRepository;
import com.boredapp.repository.CityRepository;
import com.boredapp.repository.HotelRepository;
import com.boredapp.repository.IncategoryRepository;
import com.boredapp.repository.UserRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlanTripController {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    IncategoryRepository incategoryRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ActivityRepository activityRepository;
    
    @Autowired
    BookingRepository bookingRepository;
    @GetMapping("/plantrip/{id}")
    public String planTrip(@PathVariable (name="id") Integer id,Model model){
        User user=userRepository.findById(id).get();

        model.addAttribute("user", user);
        return "trip";

    }

    @GetMapping("/automatedtrip/{id}")
    public String automatedtrip(@PathVariable (name="id") Integer id,Model model){

        //retrieve the user and all the available cities
       // User user=userRepository.findById(id).get();
        Iterable<City> cities=cityRepository.findAll();

        
        
        //retrieve all the cities
        Iterable<Category> categories=categoryRepository.findAll();

        AutomatedTrip automatedTrip= new AutomatedTrip();
        

        //put the list of cities , an empty automatedTrip for the form to be filled , a list of categories, and the user object
        model.addAttribute("cities", cities);
        model.addAttribute("automatedTrip", automatedTrip);
        model.addAttribute("categories", categories);
        model.addAttribute("userId", id);
        


        return "automated_trip";

        

    }
    @GetMapping("/customtrip/{id}")
    public String customtrip(@PathVariable (name="id") Integer id,Model model){
       // User user=userRepository.findById(id).get();
        
        //List<Activity> activities=activityService.findAllActivities();
        List<Activity> activities=activityRepository.findAll();

        //model.addAttribute("user", user);
        model.addAttribute("userId", id);
        model.addAttribute("allactivities", activities);
        BookingDto bookingDto= new BookingDto();
       
        model.addAttribute("bookingDto", bookingDto);

        
        return "customtrip";

        

    }

    @PostMapping("/automatedtrippost/{id}")
    public String automatedtrip(@PathVariable (name="id") Integer id,
    @ModelAttribute(name="automatedTrip") AutomatedTrip automatedTrip,Model model){
        
        /*collect the user options for the automated trip
        then  retrieve the category objects from the repository
        */
        List<Category> categories=automatedTrip.getCategory()
        .stream()
        .map(cat->categoryRepository
        .findById(cat).get())
        .collect(Collectors.toList());

        //retrieve the city
        int cityId=automatedTrip.getCity();
        City city=cityRepository.findById(cityId).get();

        List<Activity> generatedActivities= new ArrayList<>();
       
        
        
        /*
            1.Loop through the List of categories
            2.retrieve all activities in the category within a category
            3.select a random activity from the list
            4. add to the array for the user to decide if they want to proceed.

        */
       categories.stream().forEach(cat->{

        List<String> activityName=incategoryRepository.FindByCategory_name(cat.getName());
        int i=random(0, activityName.size()-1);
        generatedActivities.add(activityRepository.findByName(activityName.get(i)));
        });
       
        generatedActivities.forEach( a->{
            System.out.println(a);
        });

        /**
         * put the list of selected activitis and a bookingDto object for the user to select which activities tey want to 
         * partake in .
         */
       model.addAttribute("generatedActivities", generatedActivities);
       BookingDto bookingDto= new BookingDto();
       
       model.addAttribute("userId", id);
       model.addAttribute("bookingDto", bookingDto);
       

        return "booktrip";
    }



    public int random(int min,int max){
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return  random_int;


    }


    @PostMapping("/bookedtrip/{id}")
    public String bookedTrip(@ModelAttribute(name="bookingDto") BookingDto bookingDto,Model model,
    @PathVariable (name="id") Integer id){

        

        User user =userRepository.findById(id).get();

       model.addAttribute("num",bookingDto.getActivities().size());
       
        List<Activity> activities=bookingDto.
        getActivities().stream()
        .map(actId->activityRepository
        .findById(actId).get()).collect(Collectors.toList());


        activities.forEach(activity->{
            Booking  booking =new Booking();
            booking.setActivity(activity);
            booking.setUser(user);
            booking.setCost(activity.getCost());

            System.out.println(activity);
            bookingRepository.save(booking);
        });
        model.addAttribute("activities",activities);

        model.addAttribute("user", user);
        model.addAttribute("userId", id);
       
    return "booking";
        
    }
    

    
    
    

}
