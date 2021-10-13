package com.boredapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boredapp.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
