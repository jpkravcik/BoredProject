package com.boredapp.repository;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import com.boredapp.model.ActivityId_UserId;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ActivityId_UserIdRepository extends CrudRepository<ActivityId_UserId,Integer> {
    @Transactional
	@Modifying
	@Query(value="SELECT activityid from activityid_userid  WHERE userid=:userId" ,nativeQuery=true)
    ArrayList<Integer> findByUserId(@Param("userId")Integer userId);

    @Transactional
	@Query(value="SELECT * from activityid_userid  WHERE userid=:userId  AND activityid=:activityid" ,nativeQuery=true)
	Optional<ActivityId_UserId> checkExist(@Param("userId")Integer id, @Param("activityid")Integer activityId);


	@Transactional
	@Modifying
    @Query(value="DELETE from  activityid_userid WHERE userid=:userid AND activityid=:activityid",nativeQuery = true)
	void deleteByUserAndActivity(@Param("userid") Integer userid,@Param("activityid") Integer activityId);
	
	
}
