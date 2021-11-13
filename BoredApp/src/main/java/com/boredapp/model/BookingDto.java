package com.boredapp.model;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class BookingDto {
    
    List<Integer> activities=new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String day;
    
    
}
