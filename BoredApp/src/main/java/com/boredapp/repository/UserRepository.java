package com.boredapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boredapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
