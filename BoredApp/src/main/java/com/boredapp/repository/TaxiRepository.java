package com.boredapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boredapp.nosql.Taxi;



public interface TaxiRepository extends MongoRepository<Taxi,Integer>{
    
}
