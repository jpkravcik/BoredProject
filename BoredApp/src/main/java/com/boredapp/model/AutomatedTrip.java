package com.boredapp.model;




import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AutomatedTrip {
    
   

    List<Integer> category=new ArrayList<>();

    Integer city;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String day;
    
}
