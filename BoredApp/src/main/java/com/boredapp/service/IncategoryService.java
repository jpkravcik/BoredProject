package com.boredapp.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.boredapp.model.*;

@Service
public class IncategoryService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public void save(Incategory incategory){
         jdbcTemplate.update("insert into incategory (activity_name, category_name) values(?,?)",
         incategory.getActivity().getName(),incategory.getCategory().getName());
    }


    
}
