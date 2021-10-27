package com.boredapp.repository;


import org.springframework.data.repository.CrudRepository;

import com.boredapp.model.Category;

public interface CategoryRepository extends CrudRepository<Category,Integer> {
	
	
}
