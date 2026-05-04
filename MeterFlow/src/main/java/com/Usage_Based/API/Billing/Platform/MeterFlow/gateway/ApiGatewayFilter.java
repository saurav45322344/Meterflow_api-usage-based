package com.Usage_Based.API.Billing.Platform.MeterFlow.gateway;

import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiKey;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiProject;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Subscription;
import com.Usage_Based.API.Billing.Platform.MeterFlow.middleware.RateLimiter;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiKeyRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiProjectRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.SubscriptionRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.service.UsageService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
//@RequiredArgsConstructor
public class ApiGatewayFilter extends OncePerRequestFilter {


	  private final RateLimiter rateLimiter;
	    private final ApiKeyRepository apiKeyRepository;
	    private final SubscriptionRepository subscriptionRepository;
	    private final ApiProjectRepository apiProjectRepository;
	    private final UsageService usageService;

	    public ApiGatewayFilter(ApiKeyRepository apiKeyRepository,
	                            RateLimiter rateLimiter,
	                            UsageService usageService,
	                            SubscriptionRepository subscriptionRepository,
	                            ApiProjectRepository apiProjectRepository) {

	        this.rateLimiter = rateLimiter;
	        this.apiKeyRepository = apiKeyRepository;
	        this.usageService = usageService;
	        this.subscriptionRepository = subscriptionRepository;
	        this.apiProjectRepository = apiProjectRepository;
	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                   HttpServletResponse response,
	                                   FilterChain filterChain)
	            throws ServletException, IOException {

	        String path = request.getRequestURI();


	        if (path.startsWith("/api/auth") ||
	            path.startsWith("/api/key") ||
	            path.startsWith("/api/dashboard") ||
	            path.startsWith("/api/usage") ||
	            path.startsWith("/api/payment") ||
	            path.startsWith("/api/webhook") ||
	            path.startsWith("/api/subscription") ||
	            path.equals("/api/project/create") ||
	         
	            path.equals("/api/project/all")) {

	            filterChain.doFilter(request, response);
	            return;
	        }

	        
	        if (!path.startsWith("/api/project/")) {
	            filterChain.doFilter(request, response);
	            return;
	        }
	     // allow admin APIs
	        if (path.equals("/api/project/create") ||
	            path.equals("/api/project/all")) {
	            filterChain.doFilter(request, response);
	            return;
	        }
	    
//	        String apiKey = request.getHeader("x-api-key");
//
//	        if (apiKey == null) {
//	            response.setStatus(403);
//	            response.getWriter().write("Missing API Key");
//	            return;
//	        }
	        
	        String apiKey = request.getHeader("x-api-key");

	     
	     if (apiKey == null) {
	         apiKey = request.getHeader("X-API-KEY");
	     }

	     if (apiKey == null) {
	         apiKey = request.getHeader("X_Api_Key");
	     }

	     System.out.println("DEBUG API KEY: " + apiKey);

	     if (apiKey == null || apiKey.isBlank()) {
	         response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	         response.getWriter().write("Missing API Key");
	         return;
	     }

	        //ApiKey key = apiKeyRepository.findByKeyValue(apiKey).orElse(null);

//	     ApiKey key = apiKeyRepository
//	    	        .findByKeyValueAndActiveTrue(apiKey)
//	    	        .orElse(null);
//
//	     
//	        if (key == null || !key.isActive()) {
//	            response.setStatus(403);
//	            response.getWriter().write("Invalid API Key");
//	            return;
//	        }
	     ApiKey key = apiKeyRepository.findByKeyValue(apiKey.trim()).orElse(null);

	     if (key == null) {
	         response.setStatus(403);
	         response.getWriter().write("Invalid API Key");
	         return;
	     }

	     if (!key.isActive()) {
	         response.setStatus(403);
	         response.getWriter().write("API Key disabled");
	         return;
	     }
	        
	        Long projectId;
	        try {
	            projectId = Long.parseLong(path.substring("/api/project/".length()));
	        } catch (Exception e) {
	            response.setStatus(400);
	            response.getWriter().write("Invalid project ID");
	            return;
	        }

	    
	        ApiProject project = apiProjectRepository.findById(projectId).orElse(null);


	        if (project == null || !Objects.equals(project.getUserId(), key.getUserId())) {
	            response.setStatus(403);
	            response.getWriter().write("Unauthorized API access");
	            return;
	        }
	
	           
	        
	    
	        Subscription sub = subscriptionRepository
	                .findByUserIdAndActiveTrue(key.getUserId())
	                .orElse(null);

	        //long usage = usageService.getUsageByApiKey(apiKey);
	        long usage = usageService.getUsageByApiKey(apiKey);
	        if (usage < 0) usage = 0;
	        long limit = (sub != null) ? sub.getRequestLimit() : 100;

	        if (usage >= limit) {
	            response.setStatus(402);
	            response.getWriter().write("Upgrade plan required");
	            return;
	        }

	        long start = System.currentTimeMillis();

	        filterChain.doFilter(request, response);

	        long duration = System.currentTimeMillis() - start;

	    
	        usageService.logUsage(
	                apiKey,
	                projectId,
	                path,
	                response.getStatus(),
	                duration
	        );
	    }
	    }

	
	
	
	
	
	
