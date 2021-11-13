package com.boredapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import com.boredapp.model.*;
import com.boredapp.nosql.*;
import com.boredapp.repository.TransportDescriptionRepository;
import com.boredapp.repository.TransportRepository;

@Service
public class TransportDescriptionService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TransportDescriptionRepository transportDescriptionRepository;

    @Autowired
    TransportRepository transportRepository;
    
    @PostConstruct
    public void populateDocument(){
        
       mongoTemplate.dropCollection("transport");
       mongoTemplate.dropCollection("transportDescripton");
        
        
        Transport transport=new Transport();
        transport.setName("MTA");
        TransportDescription transportDescription=new TransportDescription();
        transportDescription.setDescription("If you can’t walk to your destination, mass transit is the next-best way to get around. The City’s rail and bus system is run by the Metropolitan Transportation Authority (MTA) and known as MTA New York City Transit. It’s inexpensive, environmentally friendly and a great way to see sights throughout the five boroughs—and it operates 24 hours a day, seven days a week."+"\n"+
        " The system is accessible to passengers with visual, hearing and mobility disabilities. For more information, consult the MTA’s accessibility guide and its list of accessible subway stations. Consult as well our article on accessible transportation in NYC."+"\n"+
        " Purchasing a MetroCard is your first step to getting around on subways and buses; you must put a minimum value of $5.50 on the card, not including the card fee ($1), when initially buying it. You can do so at subway stations, from either automated machines (large machines accept cash, ATM bank cards and regular credit cards; the small machines do not accept cash) or booth attendants (cash only). If you already have a card, keep it and refill it to avoid paying a new card fee. ");
        transport.setTransportDescription(transportDescription);
        transportRepository.save(transport);
        transportDescriptionRepository.save(transportDescription);



        Transport Subway=new Transport();
        Subway.setName("Subway");
        TransportDescription transportDescription2=new TransportDescription();
        transportDescription2.setDescription("The easiest and quickest way to travel around NYC is by the subway. Riding the subway is also a fantastic way to feel like a local during your stay in New York. "+"\n "+
        "Fast facts: "+"\n"+
        "• Subway trains operate 24 hours a day, seven days a week."+"\n"+
        "• For $2.75 (the cost of a single ride when using a pay-per-ride MetroCard), you can use the system citywide and transfer to other subway lines as many times as you need, as long as you don’t exit through a turnstile. "+"\n"+
        "• You can transfer from subway to local bus or vice versa within two hours of using your pay-per-ride MetroCard (all transfers are free with an Unlimited Ride MetroCard, but it cannot be used at the same subway station or on the same bus route for at least 18 minutes, nor can it be used on an express bus). "+"\n"+
        "• Subway stations on the same line are generally about 8 to 10 blocks apart on local lines (the stops on express trains are usually farther apart). "+"\n"+
        "• The subway does not travel to Staten Island. To get there, board the free Staten Island Ferry or take a bus.");
        Subway.setTransportDescription(transportDescription2);
        transportRepository.save(Subway);
        transportDescriptionRepository.save(transportDescription2);





        Transport buses=new Transport();
        buses.setName("Buses");
        TransportDescription transportDescription3=new TransportDescription();
        transportDescription3.setDescription("Public buses are a scenic way to see the City and reach destinations not convenient to a subway stop."+"\n"+
        "It’s also worth noting that mass transit is central to New York City’s efforts to become more environmentally friendly, and a growing number of NYC’s buses are hybrid-electric and electric models."+ "\n"+ 
        "The MTA even announced in 2018 plans to have an all-electric bus fleet by 2040.");
        buses.setTransportDescription(transportDescription3);
        transportRepository.save(buses);
        transportDescriptionRepository.save(transportDescription3);





        Transport bike=new Transport();
        bike.setName("NYC by Bike");
        TransportDescription transportDescription4=new TransportDescription();
        transportDescription4.setDescription("Biking the City is good for the environment and your body, and can often be faster and cheaper than fuel-powered transportation. Cycling hot spots like Central, Riverside and Prospect Parks are great options for hitting the City on two wheels, as are bike paths along the Hudson and East Rivers and on many bridges—but just about all of NYC is bikeable."+"\n"+ 
        "The NYC Department of Transportation publishes a downloadable bike map and a guide to biking in the City, and Transportation Alternatives provides additional resources for bicyclists."+"\n"+
        "Citi Bike is New York City’s bike-sharing system, and it has gained a quick adoption since its inception in May 2013. There are about 10,000 bikes at around 600 stations, available 24/7 every day of the year. Unlock a bike at any station, ride wherever you want and check in the bike at any other station. Daily, three-day and annual passes are available.");
        
        bike.setTransportDescription(transportDescription4);
        transportRepository.save(bike);
        transportDescriptionRepository.save(transportDescription4);





        Transport taxis=new Transport();
        taxis.setName("Taxis");
        TransportDescription transportDescription5=new TransportDescription();
        transportDescription5.setDescription("The City’s fleet of yellow taxicabs and green Boro Taxis are licensed and regulated by the NYC Taxi & Limousine Commission. Grabbing a cab can be ideal when tired feet, heavy luggage or shopping bags weigh you down. "+"\n"+

        "Fast facts:"+"\n"+
        "• Taxis are available 24 hours a day."+"\n"+
        "• Hail taxis whose numbers are illuminated on top—they’re on duty."+"\n"+
        "• Board and exit the cab curbside."+"\n"+
        "• Hotel doormen can hail a cab for you; a $1 tip is customary for this service."+"\n"+
        "• Minimum metered fare is $2.50, which increases 50 cents every fifth of a mile or every minute, depending on how fast you’re traveling.");
        taxis.setTransportDescription(transportDescription5);
        transportRepository.save(taxis);
        transportDescriptionRepository.save(transportDescription5);












    }
    
    
}
