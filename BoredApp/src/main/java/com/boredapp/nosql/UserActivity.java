package com.boredapp.nosql;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="userActivities")
public class UserActivity {
    @Id
	String name;
	String description;
	double cost;
	
	
	
	
	
}
