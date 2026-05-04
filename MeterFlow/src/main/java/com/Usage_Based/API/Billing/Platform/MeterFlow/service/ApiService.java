package com.Usage_Based.API.Billing.Platform.MeterFlow.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiKey;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiUsage;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.User;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiKeyRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiUsageRepository;


@Service
public class ApiService {

	@Autowired
	private  ApiKeyRepository apiKeyRepo;
	@Autowired
    private  ApiUsageRepository usageRepo;

//    public ApiKey getKey(String key) {
//        return apiKeyRepo.findByKeyValue(key);
//    }
	
	public ApiKey getKey(String key) {
	    return apiKeyRepo.findByKeyValue(key).orElse(null);
	}

    public boolean validateKey(String key) {
        return getKey(key) != null;
    }
//not in use if you want you can remove
    public String generateApiKey(Long userId) {
        String key = UUID.randomUUID().toString();
        ApiKey apiKey = new ApiKey();
        apiKey.setKeyValue(key);
        apiKey.setUserId(userId);
        
        apiKey.setRateLimit(100);
        apiKeyRepo.save(apiKey);
        return key;
    }

    public ApiKey getOrCreateKey(User user) {

        return apiKeyRepo.findByUserId(user.getId())
            .orElseGet(() -> {
                ApiKey key = new ApiKey();
                key.setKeyValue(UUID.randomUUID().toString());
                key.setUserId(user.getId());
                key.setRateLimit(100);
                key.setActive(true);

                return apiKeyRepo.save(key);
            });
    }
    
//    public void logUsage(String key, String endpoint, int status, long time) {
//
//        ApiUsage usage = new ApiUsage();
//        usage.setApiKey(key);
//        usage.setEndpoint(endpoint);
//        usage.setStatusCode(status);
//        usage.setResponseTime(time);
//        usage.setTimestamp(LocalDateTime.now());
//
//        usageRepo.save(usage);
//    }
    
 //  REVOKE API KEY
    public String revokeKey(Long id) {

        ApiKey key = apiKeyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("API Key not found"));

        key.setActive(false);
        apiKeyRepo.save(key);

        return "API Key revoked successfully";
    }
}

