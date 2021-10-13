package com.boredapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boredapp.model.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
