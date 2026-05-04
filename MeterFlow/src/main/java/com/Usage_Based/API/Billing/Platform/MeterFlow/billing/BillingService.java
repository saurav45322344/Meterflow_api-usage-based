package com.Usage_Based.API.Billing.Platform.MeterFlow.billing;

import org.springframework.stereotype.Service;

import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiUsageRepository;

@Service
public class BillingService {
	
	private final ApiUsageRepository usageRepository;
	public BillingService(ApiUsageRepository usageRepository) {
		this.usageRepository=usageRepository;
	}
	public double calculateBill(String apiKey) {

	    long count = usageRepository.countByApiKey(apiKey);

	    if (count <= 2) return 0;

	    return (count - 2) * 0.005;
	}
	
	  // ✅ ADD THIS METHOD
    public long getUsage(String apiKey) {
        return usageRepository.countByApiKey(apiKey);
    }
}
