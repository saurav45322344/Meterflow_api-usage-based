package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiKey;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.User;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiKeyRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiUsageRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.UserRepository;

@RestController
@RequestMapping("/api/dashboard")

public class DashboardController {


	
	private final ApiUsageRepository usageRepo;
    private final ApiKeyRepository keyRepo;
    private final UserRepository userRepository;
    
    public DashboardController(ApiUsageRepository usageRepo
    		,ApiKeyRepository keyRepo,
    		UserRepository userRepository) {
    	this.usageRepo=usageRepo;
    	this.keyRepo=keyRepo;
    	this.userRepository=userRepository;
    }
	
    @GetMapping
    public Map<String, Object> getDashboard(Authentication auth) {

        String email = auth.getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        List<ApiKey> keys = keyRepo.findAllByUserId(user.getId());

        long total = 0;

        for (ApiKey key : keys) {
            total += usageRepo.countByApiKey(key.getKeyValue());
        }

        double revenue = Math.max(0, (total - 100) * 0.005);

        return Map.of(
            "totalRequests", total,
            "revenue", revenue,
            "activeApis", keys.size()
        );
    }
}