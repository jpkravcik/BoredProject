package com.boredapp.model;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import lombok.*;
import lombok.ToString.Exclude;


@Data
@NoArgsConstructor
@Entity
@Table(name="userdata")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="firstname",nullable =false)
    private String firstName;
    @Column(name="lastname",nullable =false)
    private String lastName;
    @Column(name="email",nullable =false,unique = true)
    private String email;
    @Size(min=5,max=150)
    @Column(name="password",nullable =false)
    private String password;
    @Transient
    private String repeatPassword;
    
	@OneToMany(mappedBy="user",cascade=CascadeType.PERSIST)
	List<Review> review=new ArrayList<>();
	
	
	@Exclude
	@OneToMany(mappedBy="user",cascade=CascadeType.PERSIST)
	List<Booking> Booking=new ArrayList<>();
    
	
    
    
   



}
