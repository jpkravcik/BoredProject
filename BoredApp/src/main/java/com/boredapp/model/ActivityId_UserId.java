package com.boredapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.GeneratedValue;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activityid_userid" ,uniqueConstraints = {@UniqueConstraint(columnNames = { "userid", "activityid"}) })
public class ActivityId_UserId {
    
    @Id
    @GeneratedValue
    private Integer id;

	private Integer activityid;
	
	private Integer userid;



    

}
