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
import lombok.*;
import lombok.EqualsAndHashCode.Exclude;

@Data
@NoArgsConstructor
@Entity
public class City {
	@lombok.ToString.Exclude
	@Exclude
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;


	
	@Column(unique = true)
	private String name;
	
	@lombok.ToString.Exclude
	@Exclude
	@OneToMany(mappedBy="city",cascade=CascadeType.PERSIST)
	 List<Hotel> hotel=new ArrayList<>();

	/*@lombok.ToString.Exclude
	@Exclude
	@OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
	List<Activity> activity=new ArrayList<>();*/



	

	
	
	 
	 
	 
}
