package com.Usage_Based.API.Billing.Platform.MeterFlow.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiUsage;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiUsageRepository;


@Service
public class UsageService {



	//testing
	 private final ApiUsageRepository usageRepository;
	 public UsageService(ApiUsageRepository usageRepository) {
	 	this.usageRepository=usageRepository;
	 }
	 	   
	 	    public void logUsage(String apiKey,
	                 Long projectId,  
	 	                         String endpoint,
	 	                         int statusCode,
	 	                         long responseTime) {

	 	        ApiUsage usage = new ApiUsage();
	 	        usage.setApiKey(apiKey);
	 	        usage.setProjectId(projectId); //new addon
	 	        usage.setEndpoint(endpoint);
	 	        usage.setStatusCode(statusCode);
	 	        usage.setResponseTime(responseTime);
	 	        usage.setTimestamp(LocalDateTime.now());

	 	        usageRepository.save(usage);
	 	    }

	 	   

	 	    
	 	    public long getUsageByProject(Long projectId) {
	 	        return usageRepository.countByProjectId(projectId);
	 	    }

	 	    
	 	    public long getUsageByApiKey(String apiKey) {
	 	        return usageRepository.countByApiKey(apiKey);
	 	    }
	 	    
	 	    public long getUsage(String apiKey) {
	 	        return usageRepository.countByApiKey(apiKey);
	 	    }

	 	   
	 	    public long getTodayUsage(String apiKey) {
	 	        return usageRepository.countTodayUsage(apiKey);
	 	    }
	 	}
	 	
	