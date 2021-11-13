package com.boredapp.nosql;

import javax.persistence.*;

import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import javax.persistence.*;

@Data
@Document
public class Transport {
    @Id
	private String id;
   
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportDescription_id", referencedColumnName = "id")
    private TransportDescription transportDescription;

    
    
}
