package com.boredapp.repository;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.transaction.Transactional;

import com.boredapp.model.Booking;
import com.boredapp.model.BookingKey;

public interface BookingRepository extends CrudRepository<Booking, BookingKey> {

    @Transactional
	@Modifying
	@Query(value="SELECT * from booking  WHERE user_id=:userid" ,nativeQuery=true)
    List<Booking> getBookings(@Param("userid")Integer id);

    @Transactional
    void deleteById(Integer id);

}
