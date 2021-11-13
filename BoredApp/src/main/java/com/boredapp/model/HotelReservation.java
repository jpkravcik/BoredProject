package com.boredapp.model;
import java.util.Date;

import javax.persistence.Column;
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
@Table(name="hotel_reservation")
public class HotelReservation {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer TripNo;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="hotel_id", nullable=false)
	private Hotel hotel;
	
	
	
	
	
	
	
	
	
	
	
	
	
}
