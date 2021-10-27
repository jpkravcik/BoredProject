package com.boredapp.repository;



import org.springframework.data.repository.CrudRepository;

import com.boredapp.model.Booking;
import com.boredapp.model.BookingKey;

public interface BookingRepository extends CrudRepository<Booking, BookingKey> {

}
