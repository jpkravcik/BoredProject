package com.boredapp.repository;


import org.springframework.data.repository.CrudRepository;

import com.boredapp.model.City;

public interface CityRepository extends CrudRepository<City, Integer> {

}
