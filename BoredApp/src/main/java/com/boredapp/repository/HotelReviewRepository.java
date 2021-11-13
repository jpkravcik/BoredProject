package com.boredapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.boredapp.model.*;
import com.boredapp.nosql.*;

public interface HotelReviewRepository extends MongoRepository<HotelReview,Integer>{

    HotelReview findByName(String name);


    
}
