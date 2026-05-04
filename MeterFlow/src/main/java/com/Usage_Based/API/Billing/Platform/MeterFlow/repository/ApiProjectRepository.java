package com.Usage_Based.API.Billing.Platform.MeterFlow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiProject;

public interface ApiProjectRepository  extends JpaRepository<ApiProject, Long>{
	
	 List<ApiProject> findByUserId(Long userId);
}
