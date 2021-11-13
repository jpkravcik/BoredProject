package com.boredapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    Integer userid;

    Integer tripNo;

    
}
