package com.boredapp.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.boredapp.nosql.UserActivity;
/**
 * For Pagination and Other JPA functionality beyond base CRUD services
 * @see https://docs.spring.io/spring-data/data-jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
 */

public interface UserActivityRepository extends MongoRepository<UserActivity, String> {

}
