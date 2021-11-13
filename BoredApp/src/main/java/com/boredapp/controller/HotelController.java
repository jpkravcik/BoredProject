package com.boredapp.controller;

import java.util.*;

import com.boredapp.model.*;
import com.boredapp.nosql.HotelReview;
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
@SessionAttributes({"user"})
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HotelReservationRepository hotelReservationRepository;

    @Autowired 
    HotelReviewRepository hotelReviewRepository;

    @Autowired
    TripService tripService;



    @GetMapping("/bookhotel")
    public String bookHotel(Model model){

        List<Hotel> hotels=(List<Hotel>) hotelRepository.findAll();
        model.addAttribute("hotels", hotels);

        return "hotel";

    }


    @GetMapping("/hotel/{id}")
    public  String hotelChoice(Model model,@PathVariable("id") Integer id){
        
        Hotel hotel=hotelRepository.findById(id).get();
        HotelReview hotelReview=hotelReviewRepository.findByName(hotel.getName());
        HotelReservation hotelReservation=new HotelReservation();

        model.addAttribute("hotelReview", hotelReview);
        model.addAttribute("hotel", hotel);
        model.addAttribute("hotelReservation", hotelReservation);

        


        

        return "hotel2";

    }

    @PostMapping("/hotelreservation")
    public  String hotelChoice(Model model,@ModelAttribute("hotelReservation") HotelReservation hotelReservation){
        User user =(User)model.asMap().get("user");
        
        int tripNo=tripService.generateNumber(user)-1;
        hotelReservation.setTripNo(tripNo);
        hotelReservation.setUser(user);

        hotelReservationRepository.save(hotelReservation);

        return "redirect:/gohome";

    }


    
}
