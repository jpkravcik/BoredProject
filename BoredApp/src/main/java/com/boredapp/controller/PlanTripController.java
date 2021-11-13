package com.boredapp.controller;


import java.util.ArrayList;

import java.util.List;

import java.util.stream.Collectors;




import com.boredapp.model.*;
import com.boredapp.repository.*;
import com.boredapp.service.TripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"user","bookingDto","automatedTrip"})
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
    @Autowired
    TripService tripService;
    @Autowired
    TripRepository tripRepository;
    

   @GetMapping("/plantrip")
    public String planTrip(Model model){
       
        return "trip";

    }

    /**
     * 
     * This method is mapped if the user decides to let the algorithm decide for them.
     * all the cities and categories are going to be put on the template for the user
     * to choose from . 1 city and many categories to choose from.
     * 
     * The choices will be saved in the  automatedTrip object.
     */
    @GetMapping("/automatedtrip")
    public String automatedtrip(Model model){

        //retrieve the session key user
        User user =(User)model.asMap().get("user");
       
        //retrieve the cities 
        Iterable<City> cities=cityRepository.findAll();

        //retrieve all the categories
        Iterable<Category> categories=categoryRepository.findAll();
        AutomatedTrip automatedTrip= new AutomatedTrip();
        model.addAttribute("cities", cities);
        //add the automated trip object to collect the user choices
        model.addAttribute("automatedTrip", automatedTrip);
        model.addAttribute("categories", categories);

        

        return "automated_trip";

        

    }

    /**
     * 
     * @param automatedTrip
     * @param model
     * The automated trip object contains the user choices. 
     * the user answers on the categories it want and the city
     * an activity from each category will be picked and added to a bookingdto object
     * 
     */
    @PostMapping("/automatedtrippost")
    public String automatedtrip( @ModelAttribute(name="automatedTrip") AutomatedTrip automatedTrip,Model model){
        model.addAttribute("automatedTrip,", automatedTrip);
        //retrieve  the user object
        User user =(User)model.asMap().get("user");
       // model.addAttribute("user", user);

       //get the ctaegories the user chose
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
         * loop through the list of categories
         * get list of all activities in a category
         * get a random  index 
         * find the activity by name from the activity repo
         * store the activity in an array
         * 
         * 
         */
        categories.stream().forEach(cat->{
            //find the activitu in a category
            List<String> activityName=incategoryRepository.FindByCategory_name(cat.getName());
            //get a random number
            int i=random(0, activityName.size()-1);
            //retrieve from the activity category and add to the list
            generatedActivities.add(activityRepository.findByName(activityName.get(i)));
            });
           
            
            generatedActivities.forEach( a->{
                System.out.println(a);
            });
            
            //put the array of reccomended activities on the template
            model.addAttribute("generatedActivities", generatedActivities);
            BookingDto bookingDto= new BookingDto();
            bookingDto.setDay(automatedTrip.getDay());
       
            //put the bookingdto on the template for the user to select the activities 
            model.addAttribute("bookingDto", bookingDto);

       
       
       

        return "booktrip";
    }

    

    /**
     * retrieve a list of all activities
     * add a bookingDto to collect user imput
     */
    @GetMapping("/customtrip")
    public String customtrip(Model model){
       
        List<Activity> activities=activityRepository.findAll();

        //put all the activities on the template
        model.addAttribute("allactivities", activities);
        BookingDto bookingDto= new BookingDto();
       //put  the booking dto on the template
        model.addAttribute("bookingDto", bookingDto);
        
        return "customtrip";

        

    }


    @GetMapping("/select")
    public String chooseActivity(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("activities", activityRepository.findAll());
        model.addAttribute("costs", List.of(20.0,40.0,60.0,80.0,100.0));

		return "select";

    }

    @GetMapping("/bycategory/{id}")
    public String refineByCategory(Model model,@PathVariable(name="id") Integer id){

        Category category=categoryRepository.findById(id).get();

        List<String> activityNames=incategoryRepository.FindByCategory_name(category.getName());

        List<Activity> activities=new ArrayList<>();

        activities=
        activityNames.stream()
        .map(a->activityRepository
        .findByName(a))
        .collect(Collectors.toList());

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("activities", activities);
        model.addAttribute("costs", List.of(20.0,40.0,60.0,80.0,100.0));

		return "select";

    }

    @GetMapping("/bycost/{cost}")
    public String refineByCost(Model model,@PathVariable(name="cost") Double cost){

        

        List<Activity> activities=new ArrayList<>();

        activities=activityRepository.findAll().stream().filter(a->a.getCost()<= cost).collect(Collectors.toList());

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("activities", activities);

        model.addAttribute("costs", List.of(20.0,40.0,60.0,80.0,100.0));

		return "select";

    }

    @GetMapping("/addbooking/{id}")
    public String addBooking(Model model,@PathVariable(name="id") Integer id){
        User user =(User)model.asMap().get("user");

        Booking booking=new Booking();
        try{
            Activity activity=activityRepository.findById(id).get();
            booking.setUser(user);
            booking.setActivity(activity);
            bookingRepository.save(booking);
        }catch(Exception e){

        }

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("activities", activityRepository.findAll());
        model.addAttribute("costs", List.of(20.0,40.0,60.0,80.0,100.0));


		return "select";
        
    }


    

    

    public int random(int min,int max){
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return  random_int;


    }

    //after the form is submited for the custom or automated trip map to this
    @PostMapping("/bookedtrip")
    public String bookedTrip(@ModelAttribute(name="bookingDto") BookingDto bookingDto,Model model){
        
        //add the bookingdto session key
        model.addAttribute("bookingDto", bookingDto);

       
       
    return "redirect:/booking";
        
    }
    

    @GetMapping("/booking")
    public String direct(Model model){
        //retrieve the session key boookng dto
        BookingDto bookingDto =(BookingDto)model.asMap().get("bookingDto");

        model.addAttribute("num",bookingDto.getActivities().size());
        //retrieve the user session key
        User user =(User)model.asMap().get("user");
        

        /**
         * get the ids of the activity and transform to the activity 
         * and save in a list
         */
        List<Activity> activities=bookingDto.
        getActivities().stream()
        .map(actId->activityRepository
        .findById(actId).get()).collect(Collectors.toList());

        /**
         * save your booking
         */
        
        try{
            activities.forEach(activity->{
                Booking  booking =new Booking();
                booking.setActivity(activity);
                booking.setUser(user);
                booking.setActivityName(activity.getName());
                booking.setCost(activity.getCost());
                System.out.println(activity);
                bookingRepository.save(booking);
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        //get all th user booking and store in an array
        List<Booking> userBooking=bookingRepository.getBookings(user.getId());
        //get the latest 
       
        //set the booking list for the user 
        user.setBooking(userBooking);
        //save the user to upload
        userRepository.save(user);
        
        model.addAttribute("bookings",  userBooking);
        model.addAttribute("activities",activities);
        return "booking";

    }
    
    
    

}
