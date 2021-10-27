package com.boredapp.security;

import com.boredapp.model.User;
import com.boredapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public class CustomUserService implements UserDetailsService {
	
	@Autowired
	UserRepository UserRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=UserRepository.findByEmail(username).get();
		
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomerUserDetails(user);
	}

}
