package com.boredapp.nosql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class BusPass {
    @Id
   
    private Integer id;
    private String name;
    private Double cost;


    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private Location location;

    
}
