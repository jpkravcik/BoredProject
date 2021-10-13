package com.boredapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boredapp.nosql.TourGuide;



public interface TourGuideRepository extends MongoRepository<TourGuide, Integer> {

}
