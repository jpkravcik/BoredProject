package com.boredapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;


@Data
@NoArgsConstructor
@Entity
@Table(name="activity")
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
	@OneToMany(mappedBy="activity",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Booking> Booking=new ArrayList<>();
	
	@OneToMany(mappedBy="activity",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<UserActivity> userActivity=new ArrayList<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	





	/*@lombok.EqualsAndHashCode.Exclude
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="city_name", nullable=false)
	private City city;*/
	
	
	
}
