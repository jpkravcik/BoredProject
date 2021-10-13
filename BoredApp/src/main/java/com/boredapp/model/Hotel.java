package com.boredapp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Hotel {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer id;
	 
	 private String name;
	 
	 
	 private Double cost;
	
	@ManyToOne
	@JoinColumn(name="city_id", nullable=false)
	private City city;

	
	@OneToMany(mappedBy="hotel")
	List<HotelReservation> reviews;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<HotelReservation> getReviews() {
		return reviews;
	}

	public void setReviews(List<HotelReservation> reviews) {
		this.reviews = reviews;
	}
	 
	
	
	
}
