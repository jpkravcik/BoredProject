package com.boredapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boredapp.model.HotelReservation;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Integer> {

}
