package com.boredapp.model;


import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "activity_id"}) })
//@IdClass(BookingKey.class)
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;


	@ManyToOne(targetEntity=User.class)
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	
	//@Id


	@ManyToOne(targetEntity=Activity.class)
    @JoinColumn(name="activity_id", nullable=false)
	private Activity activity;
	
	private Double cost;
	
	private String activityName;

	

	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
	
	/*@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String userEmail;
	private String activityName;
	private Double cost;*/

	


	
	/*@Column(name="day")
	private Date date;*/
	
	
	
	
	
	
	
	
	
	
	
	
	

}
