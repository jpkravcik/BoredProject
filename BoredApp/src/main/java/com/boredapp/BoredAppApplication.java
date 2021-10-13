package com.boredapp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.boredapp.model.*;
import com.boredapp.repository.*;
//import com.boredapp.service.*;


@SpringBootApplication

@EnableAutoConfiguration
public class BoredAppApplication {
	
	/*@Autowired
	ActivityTicketService ActivityTicketService;*/
	@Autowired
	ActivityRepository  ActivityRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(BoredAppApplication.class, args);
	}
	

    @Bean 
    public  CommandLineRunner saveticket(CategoryRepository categoryRepository,ActivityRepository activityRepository){
		return (args)->{
			/*
			User user=new User();
			user.setEmail("olisajc@gmail.com");
			user.setFirstName("olisa");
			user.setLastName("johnny");
			
			user.setPassword("oooooooooo");
			
			 
			 
			 Optional<Activity> activity=ActivityRepository.findById(1);
			 
			 activity.ifPresentOrElse((a->{
				 ActivityTicket activityticket=new ActivityTicket();
				 activityticket.setName(user.getFirstName());
				 activityticket.setUserEmail(user.getEmail());
				 activityticket.setActivity(a.getName());
				// ActivityTicketService.saveTicket(activityticket);
			 }), ()->{
				 System.out.println("ERROR");
			 });
			 
			 */
			Optional<Category> category=categoryRepository.findById(1);
			
			
			category.ifPresentOrElse(c->{
				 System.out.println(c.getName());
			}, ()->{
				 System.out.println("ERROR");
			});
			
			
			Optional<Activity> activity=activityRepository.findById(1);
			
			
			activity.ifPresentOrElse(a->{
				 System.out.println(a.getName());
			}, ()->{
				 System.out.println("ERROR");
			});
			
		};
    	
    }
	
    

}
