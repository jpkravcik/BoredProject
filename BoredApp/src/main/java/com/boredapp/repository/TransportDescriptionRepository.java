package com.boredapp.repository;
import com.boredapp.nosql.*;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransportDescriptionRepository extends MongoRepository<TransportDescription,Integer>{
    
    
}
