package com.boredapp.repository;

import com.boredapp.nosql.AppReview;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppReviewRepository extends MongoRepository<AppReview,String>{
    
}
