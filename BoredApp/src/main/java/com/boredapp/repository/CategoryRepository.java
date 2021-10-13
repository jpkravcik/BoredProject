package com.boredapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boredapp.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
	
	
}
