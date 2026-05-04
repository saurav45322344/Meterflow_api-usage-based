package com.Usage_Based.API.Billing.Platform.MeterFlow.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiKey;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Subscription;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiKeyRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	private ApiKeyRepository apiKeyRepository;
	
	@Autowired
    private SubscriptionRepository repo;
	
	 public void activatePlan(Long userId, String plan) {
		 
		 //deactivate old pan
		 repo.deactivateUserPlans(userId);

//	        Subscription sub = repo.findByUserId(userId)
//	                .orElse(new Subscription());
		 
		 Subscription sub =new Subscription();

	        sub.setUserId(userId);
	       // sub.setPlan("plan");
	        sub.setPlan(plan);
	        sub.setStartDate(LocalDateTime.now());
	        sub.setActive(true);
	        sub.setStatus("ACTIVE");

	        switch (plan) {

	            case "BASIC":
	            	 sub.setExpiryDate(LocalDateTime.now().plusDays(30));
	                sub.setRequestLimit(1000L);
	                break;

	            case "PRO":
	            	 sub.setExpiryDate(LocalDateTime.now().plusDays(30));
	                sub.setRequestLimit(10000L);
	                break;

	            case "ENTERPRISE":
	            	 sub.setExpiryDate(LocalDateTime.now().plusDays(30));
	                sub.setRequestLimit(100000L);
	                break;

	            default:
	                throw new RuntimeException("Invalid plan");
	        }

	        repo.save(sub);
	    }

	    public Subscription getActivePlan(Long userId) {
	        return repo.findByUserIdAndActiveTrue(userId)
	                .orElse(null);
	    }
	}
	