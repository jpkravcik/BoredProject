package com.boredapp.service;

import java.util.Optional;

import com.boredapp.model.User;
import com.boredapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User register(User user) throws UserAlreadyExistException {
		// TODO Auto-generated method stub
		
		if(emailExists(user.getEmail())) {
			throw new UserAlreadyExistException(user.getEmail());
		}else {
			userRepository.save(user);
		}
		return user;
		
	}



    private boolean emailExists(String email) {
		// TODO Auto-generated method stub

        Optional<User> user=userRepository.findByEmail(email);

       
		return user.isPresent();
	}



    public User findByEmail(String username) {

        return  userRepository.findByEmail(username).get();
    }
}
