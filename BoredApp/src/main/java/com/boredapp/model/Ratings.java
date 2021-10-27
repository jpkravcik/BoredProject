package com.boredapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="rating")
public class Ratings {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	
	
	@ManyToOne
    @JoinColumn(name="activity_id", nullable=false)
	private Activity activity;
	
	
	private Integer rating;


	
	


}
