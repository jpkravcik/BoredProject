package com.boredapp.model;
import java.util.ArrayList;
import com.boredapp.repository.*;
import com.boredapp.model.*;


import org.springframework.beans.factory.annotation.Autowired;

import lombok.*;

@Data
public class SessionData {

    int cost;
    String category;
    Activity randomActivity;
    ArrayList<Activity> allActivities;
    ArrayList<ActivityInCategory> activities;
    

    public SessionData(ArrayList<Activity> activities, ArrayList<ActivityInCategory> activitiesInCategory){
        this.cost = 1000;
        this.category = "ALL";
        this.allActivities= activities;
        int index = (int)(Math.random() * this.allActivities.size());
        this.randomActivity = allActivities.get(index);
        this.activities = activitiesInCategory;
    }


    @Override
    public String toString() {
        return "SessionData [activities=" + activities + ", allActivities=" + allActivities + ", category=" + category + ", cost=" + cost 
                + ", randomActivity=" + randomActivity + "]";
    }



    
}
