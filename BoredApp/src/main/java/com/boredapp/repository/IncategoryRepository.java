package com.boredapp.repository;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



import com.boredapp.model.CategoryKey;
import com.boredapp.model.Incategory;

public interface IncategoryRepository extends CrudRepository<Incategory, CategoryKey> {
	
	
	@Transactional
	@Modifying
	@Query(value="SELECT activity_name from incategory  WHERE category_name=:category_name" ,nativeQuery=true)
	List<String> FindByCategory_name(@Param("category_name")String category_name);
	
	
	
	@Transactional
	@Modifying
	@Query(value="SELECT category_name from incategory  WHERE activity_name=:activity_name" ,nativeQuery=true)
	List<String> FindByActivity_name(@Param("activity_name")String activity_name);


	
	
	
	
}
