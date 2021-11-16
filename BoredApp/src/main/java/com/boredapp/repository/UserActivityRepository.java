package com.boredapp.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import javax.transaction.Transactional;

import com.boredapp.model.*;
/**
 * For Pagination and Other JPA functionality beyond base CRUD services
 * @see https://docs.spring.io/spring-data/data-jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
 */

public interface UserActivityRepository extends CrudRepository<UserActivity, Integer> {

    List<UserActivity> findByUser(User user);

    @Transactional
    void deleteByActivity(Activity activity);

    @Transactional
    UserActivity findByActivity(Activity activity); 

   

}
