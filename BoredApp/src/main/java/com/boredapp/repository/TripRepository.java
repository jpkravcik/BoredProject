package com.boredapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.boredapp.model.*;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip,Integer>{
    @Transactional
	@Modifying
	@Query(value="SELECT trip_no from trip  WHERE userid=:id" ,nativeQuery=true)
    List<Integer> findTripNumbers(@Param("id")Integer id);
    
}
