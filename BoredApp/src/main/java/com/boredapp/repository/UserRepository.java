package com.boredapp.repository;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import com.boredapp.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

   public Optional<User> findByEmail(String username);
   


}
