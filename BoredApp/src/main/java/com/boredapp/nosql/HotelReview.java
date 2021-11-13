package com.boredapp.nosql;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@NoArgsConstructor
@Document
public class HotelReview {
    @Id
    Integer id;
    String name;
    int stars;
    String review;


    



    
}
