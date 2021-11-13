package com.boredapp.repository;



import org.springframework.data.repository.CrudRepository;

import com.boredapp.model.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {
    

}
