package com.boredapp.model;

import java.util.*;

import lombok.Data;

@Data
public class CustomActivity {
    Activity activity=new Activity();
    List<Category> categories=new ArrayList<>();

}
