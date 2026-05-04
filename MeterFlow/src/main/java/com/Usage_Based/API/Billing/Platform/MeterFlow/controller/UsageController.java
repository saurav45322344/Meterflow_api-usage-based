package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiKey;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiKeyRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiUsageRepository;


@RestController
@RequestMapping("/api/usage")
public class UsageController {
	
  
	    private final ApiUsageRepository usageRepo;
	    private final ApiKeyRepository apikeyRepository;
	    public UsageController(ApiKeyRepository apikeyRepository , ApiUsageRepository usageRepo) {
	    	this.apikeyRepository=apikeyRepository;
	    	 this.usageRepo = usageRepo;
	    }
	    
	    
	    
	    
	    
	    @GetMapping
	    public ResponseEntity<?> getUsage(@RequestParam String apiKey) {

	        if (apiKey == null || apiKey.isBlank()) {
	            return ResponseEntity.badRequest().body("API key missing");
	        }

	        ApiKey key = apikeyRepository.findByKeyValue(apiKey)
	                .filter(ApiKey::isActive)
	                .orElseThrow(() -> new RuntimeException("Invalid API Key"));

	        return ResponseEntity.ok(
	                usageRepo.findByApiKey(key.getKeyValue())
	        );
	    }

	    
	    @GetMapping("/chart")
	    public ResponseEntity<?> getUsageChart(@RequestParam String apiKey) {

	        if (apiKey == null || apiKey.isBlank()) {
	            return ResponseEntity.badRequest().body("API key missing");
	        }

	        try {
	            ApiKey key = apikeyRepository.findByKeyValue(apiKey)
	                    .filter(ApiKey::isActive)
	                    .orElseThrow(() -> new RuntimeException("Invalid API Key"));

	            
	            List<Object[]> data = usageRepo.getDailyUsageRaw(key.getKeyValue());

	            List<Map<String, Object>> result = new ArrayList<>();

	            for (Object[] row : data) {
	                Map<String, Object> map = new HashMap<>();
	                map.put("date", row[0].toString());
	                map.put("requests", ((Number) row[1]).longValue());
	                result.add(map);
	            }

	            return ResponseEntity.ok(result);

	        } catch (Exception e) {
	            e.printStackTrace();

	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Failed to load usage chart");
	        }
	    }

	    @GetMapping("/key/{apiKey}/usage")
	    public Map<String, Object> getUsagePerKey(@PathVariable String apiKey) {

	        long total = usageRepo.countByApiKey(apiKey);

	        List<Object[]> daily = usageRepo.getDailyUsageRaw(apiKey);

	        return Map.of(
	            "total", total,
	            "daily", daily
	        );
	    }
}
	