package com.boredapp.controller;



import com.boredapp.service.UserService;

import javax.servlet.http.HttpSession;

import com.boredapp.model.User;
import com.boredapp.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@SessionAttributes({"user"})
@Controller
public class LoginController {

    @Autowired
    UserService userService;


    @Autowired 
    CityRepository cityRepository;



    @GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("newUser", new User());
		return "login";
		
	}
	
	
	@PostMapping("/user")
	public String login(Model model,@ModelAttribute(name="newUser") User newUser) {
		
		String username=newUser.getEmail();
        String password=newUser.getPassword();


      
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        

		User user;
        try {
            user = userService.findByEmail(username);
            model.addAttribute("user", user);
           
           if(!passwordEncoder.matches(password, user.getPassword())){
                model.addAttribute("password", "password");
                return "login";
            }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "login";
        }
		
        /*Iterable<City> cities=cityRepository.findAll();
        model.addAttribute("cities", cities);*/

        
        
		return "redirect:/gohome";
		
	}

    @GetMapping("logout")
    public String logOut(Model model,HttpSession session) {
        
        session.invalidate();
        return "redirect:/";

    }


}
