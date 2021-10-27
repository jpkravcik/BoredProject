package com.boredapp.nosql;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;


@NoArgsConstructor
@Data
@Document
public class Location {
    @Id
   private Integer id;
    @Indexed(unique = true)
    private String name;
    
    @OneToMany(mappedBy="location")
    List<Taxi> taxis;


    @OneToMany(mappedBy="location")
    List<TourGuide> tourGuide;

    @OneToMany(mappedBy="location")
    List<BusPass> busPass;

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

	public List<Taxi> getTaxis() {
		return taxis;
	}

	public void setTaxis(List<Taxi> taxis) {
		this.taxis = taxis;
	}

	public List<TourGuide> getTourGuide() {
		return tourGuide;
	}

	public void setTourGuide(List<TourGuide> tourGuide) {
		this.tourGuide = tourGuide;
	}

	public List<BusPass> getBusPass() {
		return busPass;
	}

	public void setBusPass(List<BusPass> busPass) {
		this.busPass = busPass;
	}



  
    
}
