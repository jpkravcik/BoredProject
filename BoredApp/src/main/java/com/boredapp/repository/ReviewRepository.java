package com.boredapp.repository;


import org.springframework.data.repository.CrudRepository;

import com.boredapp.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer> {

}
