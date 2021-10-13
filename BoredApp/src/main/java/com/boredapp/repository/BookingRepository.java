package com.boredapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boredapp.model.Booking;
import com.boredapp.model.BookingKey;

public interface BookingRepository extends JpaRepository<Booking, BookingKey> {

}
