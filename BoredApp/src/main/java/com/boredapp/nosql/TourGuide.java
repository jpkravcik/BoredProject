package com.boredapp.nosql;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class TourGuide {
    @Id
    private Integer id;
    private String name;
    private Double cost;

    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private Location location;
    
    
    
    

    

}
