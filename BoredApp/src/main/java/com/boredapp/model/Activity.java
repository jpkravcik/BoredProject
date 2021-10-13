package com.boredapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.ToString.Exclude;


@Entity
@Table(name="activity")
public class Activity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	@Column(name="name",nullable=true)
	String name;
	@Column(name="description",nullable=true)
	String description;
	@Column(name="cost",nullable=true)
	double cost;
	
	/*@OneToMany(mappedBy="activity",cascade=CascadeType.PERSIST)
	List<Category> categories=new ArrayList<>();*/
	
	@Exclude
	@OneToMany(mappedBy="activity",cascade=CascadeType.PERSIST)
	List<Incategory> incategories=new ArrayList<>();
	
	@Exclude
	@OneToMany(mappedBy="activity",cascade=CascadeType.PERSIST)
	List<Review> review=new ArrayList<>();
	
	
	@Exclude
	@OneToMany(mappedBy="activity",cascade=CascadeType.PERSIST)
	List<Booking> Booking=new ArrayList<>();
	
	
	@ManyToMany(targetEntity = User.class,cascade = CascadeType.ALL )
	private List<Activity> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<Incategory> getIncategories() {
		return incategories;
	}

	public void setIncategories(List<Incategory> incategories) {
		this.incategories = incategories;
	}

	public Integer getId() {
		return id;
	}

	

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}
	
	
	
	
}
