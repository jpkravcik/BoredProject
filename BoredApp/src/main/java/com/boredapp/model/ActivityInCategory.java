package com.boredapp.model;

import lombok.Data;

@Data
public class ActivityInCategory {
    
    int activityId;
    String name;
    String description;
    double cost;
    String category;
    int categoryId;


    @Override
    public String toString() {
        return "ActivityInCategory [activityId=" + activityId + ", category=" + category + ", categoryId=" + categoryId
                + ", cost=" + cost + ", description=" + description + ", name=" + name + "]";
    }

}
