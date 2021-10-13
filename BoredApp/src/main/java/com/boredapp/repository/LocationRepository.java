package com.boredapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boredapp.nosql.Location;

public interface LocationRepository  extends MongoRepository<Location,Integer>{
    
}
