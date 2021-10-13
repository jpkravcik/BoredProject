package com.boredapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boredapp.nosql.BusPass;

public interface BusPassRepository extends MongoRepository<BusPass,Integer> {


    
    
}
