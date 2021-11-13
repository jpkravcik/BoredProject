package com.boredapp.service;

import javax.annotation.PostConstruct;

import com.boredapp.nosql.*;
import com.boredapp.repository.HotelReservationRepository;
import com.boredapp.repository.HotelReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelReviewService {
    @Autowired
    HotelReviewRepository hotelReviewRepository;

    @PostConstruct
    public void pupolateDocument(){

        HotelReview Lowell=new HotelReview();
        Lowell.setId(1);
        Lowell.setName("The Lowell");
        Lowell.setStars(5);
        Lowell.setReview("A landmark luxury hotel, near central park, The Lowell rises 17-floors above an iconic residential neighborhood , a stone’s throw from the celebrated fashion houses of Madison Avenue and footsteps from Fifth Avenue and the vast oasis of Central Park ."
        +"\n"+"Established in 1927, The Lowell's boutique hotel in NYC reflects the timeless elegance and soul of the Upper East Side . The warmth of a familiar smile, attention to detail and discreet service welcome discerning guests as they move through chic classical spaces." 
        +"\n"+"Wood-burning fireplaces—a rare find in New York—and landscaped terraces with sweeping city views are enjoyed in sun-filled rooms and suites fashioned in the hotel’s distinctive style with the refined comforts of a luxury apartment NYC ."+
        "Legendary restaurateur Charles Masson oversees the property’s newest upper east side restaurants  where guests come to escape the bustle of the city over seasonal cuisine at Majorelle and linger over the perfect martini at Jacques Bar ."+"\n"+
        "The Club Room at The Lowell offers a sophisticated setting to unwind, while the reimagined Pembroke Room upholds tradition with its beloved breakfast and afternoon tea .");
        hotelReviewRepository.save(Lowell);

        HotelReview  Chatwal=new HotelReview();
        Chatwal.setId(2);
        Chatwal.setName("The Chatwal, a Luxury Collection Hotel");
        Chatwal.setStars(4);
        Chatwal.setReview("In the heart of Manhattan, most conveniently located on 44th Street, The Chatwal, New York is steps from the best of entertainment, dining, shopping, Broadway theaters and the city’s media and financial giants." 
        +"\n"+"This beautiful landmark hotel built in 1905 by Stanford White has been restored and renewed by master architect Thierry Despont, re-launched in 2010. "+"\n"+
        "The Chatwal’s 76 keys includes 29 stunning Suites and 14 ‘themed’ suites with spacious designer terraces as well as bespoke services including a team of professional Butlers, 24-hour in-room dining and distinct private bars.");
        hotelReviewRepository.save(Chatwal);


        HotelReview  Beekman=new HotelReview();
        Beekman.setId(3);
        Beekman.setName("The Beekman, a Thompson Hotel");
        Beekman.setStars(3);
        Beekman.setReview("Old world glamour meets modern luxury at The Beekman—the centerpiece of Manhattan’s most dynamic and historical downtown neighborhood."+
        "\n"+"Shakespeare’s Hamlet has its New York debut at the Chapel Street Theatre."+"\n"+
        "The heritage of The Beekman is ingrained with history’s most influential cultural icons."+"\n"+
        "Clinton Hall: Home of NYU & the Mercantile Library Association with illustrious members including Ralph Waldo Emerson, Mark Twain, and Henry David Thoreau. Edgar Allan Poe publishes his paper The Broadway Journal on this site."+"\n"+
        "Prior to the construction of this historic building, the site hosted a succession of creative and academic achievements.");
        hotelReviewRepository.save(Beekman);




        HotelReview  Carlyle=new HotelReview();
        Carlyle.setId(4);
        Carlyle.setName("The Carlyle, a Rosewood Hotel");
        Carlyle.setStars(5);
        Carlyle.setReview("Coming Fall 2021. Celebrate a new era of dining with the introduction of Dowling’s at The Carlyle. Newly appointed Executive Chef Sylvain Delpique (formerly of 21 Club) has created a menu that features his interpretations of timeless New York favorites and throwback dishes from another era. "+"\n"+
        "Originally opened in 1955, Café Carlyle offers a classic cabaret experience with incredible talent – including past classic performers such as Bobby Short, Elaine Stritch and Eartha Kitt. Guests can now experience modern-day acts such as Isaac Mizrahi, Judy Collins, Alan Cumming, Rita Wilson, Steve Tyrell and John Pizzarelli."+"\n"+ 
        "The intimate supper club is highlighted by music-themed murals by Oscar-winning French artist, Marcel Vertès."
        +"\n"+"Inspired by the Topkapi Palace in Turkey, The Gallery was designed under the creative direction of the legendary Renzo Mongiardino. The space features hand-painted wallpaper by Milanese artist Enrico Brus and intaglio-like painted views of landmarks such as Venice’s Church of Santa Maria del Salute and Istanbul’s Hagia Sophia."+"\n" +
        "Guests can enjoy an all-day a la carte menu, afternoon tea or cocktails."
        );
        hotelReviewRepository.save(Carlyle);

        HotelReview  Baccarat=new HotelReview();
        Baccarat.setId(5);
        Baccarat.setName("Baccarat Hotel");
        Baccarat.setStars(3);
        Baccarat.setReview("In white daylight, rainbows cascading, a spectrum of swatches cast from a trillion facets."+"\n"
        +"By night, dimmed shimmer and champagne flutes. Gem-red spheres of fresh roses."+"\n"+
        "Rooms & Suites"+"\n"+
        "Private Parisian pied-à-terre living in the heart of New York City"+"\n"+
        "A vividly artistic atmosphere with true Manhattan energy with infinite potential for intimate social moments."+"\n"+
        "Spa De La Mer"+"\n"+
        "Awash with ambient light and sound inspired by the sea, the four intimate treatment rooms evoke a sense of time suspended."
        );
        hotelReviewRepository.save(Baccarat);

    }
    
}
