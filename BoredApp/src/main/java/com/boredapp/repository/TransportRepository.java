package com.boredapp.repository;

import com.boredapp.nosql.*;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransportRepository extends MongoRepository<Transport,Integer>{

    
}
