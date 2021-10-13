package com.boredapp.nosql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Taxi {
    @Id
    private Integer id;
    private String name;
    private Double cost;
    private Integer city_id;

    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private Location location;







}
