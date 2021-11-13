package com.boredapp.model;


import javax.persistence.*;

import lombok.*;

@Entity
@Data
public class UserActivity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	
	
	@ManyToOne
    @JoinColumn(name="activity_id", nullable=false)
	private Activity activity;



    
}
