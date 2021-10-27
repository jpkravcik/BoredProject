package com.boredapp.repository;


import org.springframework.data.repository.CrudRepository;

import com.boredapp.model.Ratings;

public interface RatingRepository extends CrudRepository<Ratings, Integer> {

}
