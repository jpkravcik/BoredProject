package com.boredapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boredapp.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
