package com.boredapp.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.ToString.Exclude;

@Data
@NoArgsConstructor
@Entity
@Table(name="category")
public class Category {
	@lombok.EqualsAndHashCode.Exclude
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="name",nullable =false)
	private String name;

	@lombok.EqualsAndHashCode.Exclude
	@Exclude
	@OneToMany(mappedBy="category",cascade=CascadeType.PERSIST)
	List<Incategory> incategories=new ArrayList<>();

	

	


	
	
	
	

}
