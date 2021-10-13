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
import lombok.ToString;
import lombok.ToString.Exclude;


@Entity
@Table(name="category")
public class Category {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	@Column(name="name",nullable =false)
	private String name;
	
	@Exclude
	@OneToMany(mappedBy="category",cascade=CascadeType.PERSIST)
	List<Incategory> incategories=new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Incategory> getIncategories() {
		return incategories;
	}

	public void setIncategories(List<Incategory> incategories) {
		this.incategories = incategories;
	}
	
	
	

}
