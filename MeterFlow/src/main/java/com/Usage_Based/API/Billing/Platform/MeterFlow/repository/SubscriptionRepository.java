package com.Usage_Based.API.Billing.Platform.MeterFlow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Subscription;

import jakarta.transaction.Transactional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

//    @Query("SELECT s FROM Subscription s WHERE s.userId = :userId AND s.status='ACTIVE'")
//    Subscription findActive(@Param("userId") Long userId);
	
	 Optional<Subscription> findByUserId(Long userId);
	 
	 Optional<Subscription> findByUserIdAndActiveTrue(Long userId);
	 
	   @Modifying
	    @Transactional
	    @Query("UPDATE Subscription s SET s.active = false WHERE s.userId = :userId")
	    void deactivateUserPlans(@Param("userId") Long userId);
	   
	   
	    boolean existsByUserIdAndActiveTrue(Long userId);
	    
	    
	    Optional<Subscription> findTopByUserIdOrderByStartDateDesc(Long userId);
}