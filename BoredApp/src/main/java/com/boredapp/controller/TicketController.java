package com.boredapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.boredapp.model.*;
import com.boredapp.repository.*;
import java.util.*;
import java.util.stream.*;

import com.boredapp.service.*;

@Controller
@SessionAttributes({"user"})
public class TicketController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    TripService tripService;

    @GetMapping("/viewtickets")
    public String viewTickets(Model model){


        

        //get the user object from the session key
        User user =(User)model.asMap().get("user");
        //get all the user bookings from the the  user repository
        List<Booking> userBooking=bookingRepository.getBookings(user.getId());
        //put the booking on the template
        model.addAttribute("bookings", userBooking);


        return "tickets";
    }


    @GetMapping("/deletebooking/{id}")
    public String deleteTicket(Model model,@PathVariable(name="id") Integer id){

        bookingRepository.deleteById(id);
        return "redirect:/viewtickets";

    }
    
}
