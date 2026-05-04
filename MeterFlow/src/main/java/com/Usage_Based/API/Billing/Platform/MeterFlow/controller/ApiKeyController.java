package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiKey;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.User;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiKeyRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.UserRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.service.ApiService;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/key")

public class ApiKeyController {


	private final ApiService apiService;
	private final ApiKeyRepository repo;
	private final UserRepository userRepository;
	public ApiKeyController(ApiService apiService, ApiKeyRepository repo ,UserRepository userRepository) {
		this.apiService= apiService;
		this.repo=repo;
		this.userRepository=userRepository;
	}



	@GetMapping
	public ResponseEntity<?> getApiKey(Authentication auth) {

		String email = auth.getName();

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));

		ApiKey key = repo.findByUserId(user.getId())
				.orElseGet(() -> {
					ApiKey newKey = new ApiKey();
					newKey.setKeyValue(UUID.randomUUID().toString());
					newKey.setUserId(user.getId());
					newKey.setRateLimit(100);
					newKey.setActive(true);
					return repo.save(newKey);
				});

		return ResponseEntity.ok(Map.of(
				"apiKey", key.getKeyValue()
				));
	}
	
	
	@PostMapping("/revoke/{id}")
	public ResponseEntity<?> revokeKey(@PathVariable Long id) {

		String message = apiService.revokeKey(id);

		return ResponseEntity.ok(Map.of(
				"message", message
				));
	}



	@PostMapping("/rotate/{id}")
	public ResponseEntity<?> rotateKey(@PathVariable Long id) {

		ApiKey oldKey = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("Key not found"));

		oldKey.setActive(false);
		repo.save(oldKey);

		ApiKey newKey = new ApiKey();
		newKey.setKeyValue(UUID.randomUUID().toString());
		newKey.setUserId(oldKey.getUserId());
		newKey.setRateLimit(oldKey.getRateLimit());
		newKey.setEnvironment(oldKey.getEnvironment());
		newKey.setActive(true);

		repo.save(newKey);

		return ResponseEntity.ok(newKey);
	}

	@PostMapping("/generate")
	public ResponseEntity<?> generateKey(Authentication auth) {

		if (auth == null) {
			return ResponseEntity.status(401).body("Unauthorized");
		}

		String email = auth.getName();

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));

		ApiKey key = new ApiKey();
		key.setKeyValue(UUID.randomUUID().toString());
		key.setUserId(user.getId());
		key.setRateLimit(100);
		key.setActive(true);

		ApiKey saved = repo.save(key);

		return ResponseEntity.ok(Map.of(
				"apiKey", saved.getKeyValue()
				));
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllKeys(Authentication auth) {

		String email = auth.getName();

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));

		return ResponseEntity.ok(
				repo.findAllByUserId(user.getId())   // ✅ FIXED
				);
	}

}
