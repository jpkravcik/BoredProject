package com.boredapp.model;

import javax.persistence.Entity;

import javax.persistence.*;
import lombok.*;



@Data
@NoArgsConstructor
@Entity
@IdClass(CategoryKey.class)
public class Incategory {
	
	
	@Id
	@ManyToOne
    @JoinColumn(name="activity_name", nullable=false)
	private Activity activity;
	
	@Id
	@ManyToOne
    @JoinColumn(name="category_name", nullable=false)
	private Category category;
	
	
	
	
}
