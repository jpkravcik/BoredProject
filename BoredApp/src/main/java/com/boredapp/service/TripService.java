package com.boredapp.service;

import com.boredapp.repository.TripRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boredapp.model.*;
import java.util.*;


@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;


    public int generateNumber(User user){
        List<Integer>  numbers=tripRepository.findTripNumbers(user.getId());
        if(numbers==null || numbers.size()==0){
            return 0;
        }


        return max(numbers)+1;
        
    }


    public int max(List<Integer>  numbers){
        int max=numbers.get(0);
        for(Integer num:numbers){
            if(num>max){
                max=num;
            }
        }
        return max;
    }


    
    
}
