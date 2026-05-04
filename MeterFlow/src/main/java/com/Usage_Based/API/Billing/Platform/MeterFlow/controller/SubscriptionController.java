package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Plan;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Subscription;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.User;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.PlanRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.SubscriptionRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.UserRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.security.JwtUtil;
import com.Usage_Based.API.Billing.Platform.MeterFlow.service.SubscriptionService;

@RestController
@RequestMapping("/api/subscription")
@CrossOrigin
public class SubscriptionController {

	 @Autowired
	    private SubscriptionService service;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private UserRepository userRepo;
	    
	    @Autowired
	    private SubscriptionRepository subscriptionRepository;
	    
	    @Autowired
	    private PlanRepository planRepository;

	@GetMapping("/current")
public ResponseEntity<?> getCurrent(Authentication auth) {

    if (auth == null) {
        return ResponseEntity.status(401).body("Unauthorized");
    }

    String email = auth.getName();

    User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Subscription sub = service.getActivePlan(user.getId());

    Map<String, Object> response = new HashMap<>();

    if (sub == null) {
        response.put("plan", "FREE");
        response.put("limit", 100);
        response.put("endDate", null);
    } else {
        response.put("plan", sub.getPlan()); 
        response.put("limit", sub.getRequestLimit());
        response.put("endDate", sub.getExpiryDate());
    }

    return ResponseEntity.ok(response);
}

	
	@PostMapping("/upgrade")
	public ResponseEntity<?> upgradePlan(@RequestParam String planName, Authentication auth) {

	    String email = auth.getName();

	    User user = userRepo.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Plan plan = planRepository.findByName(planName)
	            .orElseThrow(() -> new RuntimeException("Plan not found"));

	    // deactivate old
	    subscriptionRepository.deactivateUserPlans(user.getId());

	    Subscription sub = new Subscription();
	    sub.setUserId(user.getId());
	    sub.setPlan(plan.getName());
	   
	    sub.setStartDate(LocalDateTime.now());
	    sub.setExpiryDate(LocalDateTime.now().plusMonths(1));
	    sub.setStatus("ACTIVE");
	    sub.setActive(true);
	    sub.setRequestLimit(plan.getRequestLimit());
	   

	    subscriptionRepository.save(sub);

	    return ResponseEntity.ok("Upgraded to " + planName);
	}	
	
	
}