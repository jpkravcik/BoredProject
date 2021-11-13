package com.boredapp.nosql;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document
public class TransportDescription {
    
    @Id
	private String  id;
    private String Description;
    @OneToOne(mappedBy = "transportDescription")
    private Transport transport;

}
