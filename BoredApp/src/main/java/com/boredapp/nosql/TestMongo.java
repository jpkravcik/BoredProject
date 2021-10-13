package com.boredapp.nosql;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.boredapp.repository.UserActivityRepository;


@SpringBootApplication
public class TestMongo {
  public static void main(String[] args) {
    SpringApplication.run(TestMongo.class, args);
  }

 /* @Bean
  public CommandLineRunner saveUserActivity(UserActivityRepository repository) {
    return (args) -> {
      UserActivity uActivity1 = new UserActivity();
      uActivity1.setName("Biking");
      uActivity1.setDescription("Rent a bike, use your own or borrwo from a neighbour");
      uActivity1.setCost(0);
      repository.save(uActivity1);

      UserActivity uActivity2 = new UserActivity();
      uActivity2.setName("Go to the lake");
      uActivity2.setDescription("Find the nearest lake and go with a friend");
      uActivity2.setCost(2.33);
      repository.save(uActivity2);    

      System.out.println("From Mongo: " + uActivity1.toString());
      System.out.println("From Mongo: " + uActivity2.toString());

    };
  }*/
}
