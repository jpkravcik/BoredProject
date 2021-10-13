package com.boredapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boredapp.model.Ratings;

public interface RatingRepository extends JpaRepository<Ratings, Integer> {

}
