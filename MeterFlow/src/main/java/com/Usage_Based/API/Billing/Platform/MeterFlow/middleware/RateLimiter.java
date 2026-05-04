package com.Usage_Based.API.Billing.Platform.MeterFlow.middleware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class RateLimiter {
	
	//pro level code
	
	 private final Map<String, Integer> requestCount = new ConcurrentHashMap<>();
	    private final Map<String, Long> lastResetTime = new ConcurrentHashMap<>();

	    public boolean allowRequest(String apiKey, int limit) {

	        long currentTime = System.currentTimeMillis();

	        lastResetTime.putIfAbsent(apiKey, currentTime);

	        if (currentTime - lastResetTime.get(apiKey) > 60000) {
	            requestCount.put(apiKey, 0);
	            lastResetTime.put(apiKey, currentTime);
	        }

	        requestCount.putIfAbsent(apiKey, 0);

	        if (requestCount.get(apiKey) >= limit) {
	            return false;
	        }

	        requestCount.put(apiKey, requestCount.get(apiKey) + 1);
	        return true;
	    }
}