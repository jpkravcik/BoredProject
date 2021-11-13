package com.boredapp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class Hotel {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer id;
	 
	 private String name;
	 
	 
	 private Double cost;
	
	@ManyToOne
	@JoinColumn(name="city_name", nullable=true)
	private City city;

	
	@OneToMany(mappedBy="hotel")
	List<HotelReservation> reviews;
	
	
	
	
	
	
}
