package com.boredapp.nosql;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppReview {
    
    @Id
    String id;
    String userName;
    String userEmail;
    String review;
    Integer rating;
	@Override
	public String toString() {
		return "Name :"+userName+"\n"+"email :"+userEmail+"\n"+review+"\n"+rating+" stars";
	}


    

}
