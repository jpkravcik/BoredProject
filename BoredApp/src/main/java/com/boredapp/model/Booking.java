package com.boredapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@IdClass(BookingKey.class)
public class Booking {
	@Id
	@ManyToOne(targetEntity=User.class)
    @JoinColumn(name="user_email", nullable=false)
	private User user;
	
	@Id
	@ManyToOne(targetEntity=Activity.class)
    @JoinColumn(name="activity_name", nullable=false)
	private Activity activity;
	
	private Double cost;
	
	/*@Column(name="day")
	private Date date;*/
	
	
	
	
	
	
	
	
	
	
	
	
	

}
