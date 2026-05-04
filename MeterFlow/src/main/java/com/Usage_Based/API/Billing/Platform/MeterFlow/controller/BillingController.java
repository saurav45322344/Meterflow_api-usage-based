package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.billing.BillingService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/billing")
@RequiredArgsConstructor
public class BillingController {
	
	@Autowired
	private  BillingService billingService;
	
	@GetMapping
	public Map<String, Object> getBill(@RequestParam String apiKey) {

	    double amount = billingService.calculateBill(apiKey);
	    long usage = billingService.getUsage(apiKey);

	    return Map.of(
	        "apiKey", apiKey,
	        "totalRequests", usage,
	        "amount", amount
	    );
	}
}
	