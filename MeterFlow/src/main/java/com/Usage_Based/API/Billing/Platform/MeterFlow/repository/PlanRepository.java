package com.Usage_Based.API.Billing.Platform.MeterFlow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>{

	
	 Optional<Plan> findByName(String name);
}
