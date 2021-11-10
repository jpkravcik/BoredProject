package com.boredapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;


@Data
@NoArgsConstructor
@Entity
public class Activity {
	@lombok.EqualsAndHashCode.Exclude
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
	@lombok.EqualsAndHashCode.Exclude
	@Exclude
	@OneToMany(mappedBy="activity",cascade=CascadeType.PERSIST)
	List<Incategory> incategories=new ArrayList<>();
	
	@lombok.EqualsAndHashCode.Exclude
	@Exclude
	@OneToMany(mappedBy="activity",cascade=CascadeType.PERSIST)
	List<Review> review=new ArrayList<>();
	
	@lombok.EqualsAndHashCode.Exclude
	@Exclude
	@OneToMany(mappedBy="activity",cascade=CascadeType.PERSIST)
	List<Booking> Booking=new ArrayList<>();
	
	@lombok.EqualsAndHashCode.Exclude
	@ManyToMany(targetEntity = User.class,cascade = CascadeType.ALL )
	private List<Activity> users;

	/*@lombok.EqualsAndHashCode.Exclude
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="city_name", nullable=false)
	private City city;*/
	
	
	
}
