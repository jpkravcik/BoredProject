package com.boredapp.repository;


import java.util.List;

import javax.transaction.Transactional;

import com.boredapp.model.Activity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer> {

    @Transactional
	@Query(value="SELECT * from activity  WHERE name=:name" ,nativeQuery=true)
    Activity findByName(@Param("name")String name);


    @Transactional
    @Query(value="SELECT * from activity" ,nativeQuery=true)
	List<Activity> findAll();


    

}
