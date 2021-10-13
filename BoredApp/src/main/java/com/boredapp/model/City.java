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

@Entity
public class City {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer id;
	 @Column(unique=true)
	 private String name;
	 
	 @OneToMany(mappedBy="city",cascade=CascadeType.PERSIST)
	 List<Hotel> Hotel=new ArrayList<>();

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

	public List<Hotel> getHotel() {
		return Hotel;
	}

	public void setHotel(List<Hotel> hotel) {
		Hotel = hotel;
	}
	 
	 
	 
	 
}
