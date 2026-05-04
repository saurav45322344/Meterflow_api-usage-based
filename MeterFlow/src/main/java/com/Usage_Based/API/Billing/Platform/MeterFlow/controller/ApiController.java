package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.service.ApiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

	@Autowired
	private  ApiService apiService;
	
	@GetMapping("/data")
	public String getData() {
		return "Hello from MeterFlow";
	}


	//    @GetMapping("/data")
	//    public String getData(@RequestHeader("x-api-key") String key) {
	//        long start = System.currentTimeMillis();
	//
	//        String res = "Hello from MeterFlow";
	//
	//        long time = System.currentTimeMillis() - start;
	//        apiService.logUsage(key, "/data", 200, time);
	//
	//        return res;
	//    }


	@PostMapping("/ai/chat")
	public Map<String, String> chat(@RequestBody Map<String, String> req) {
	    return Map.of("response", "You asked: " + req.get("query"));
	}
	
}
