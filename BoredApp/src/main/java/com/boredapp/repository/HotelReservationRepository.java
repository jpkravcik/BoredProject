package com.boredapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import javax.transaction.Transactional;

import com.boredapp.model.HotelReservation;
import com.boredapp.model.User;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Integer> {

    @Transactional
    List<HotelReservation> findByUser(User user);
    
    @Transactional
    void deleteById(Integer id);



}
