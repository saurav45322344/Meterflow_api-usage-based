package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiProject;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.User;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.ApiProjectRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/api/project")
public class ApiProjectController {
	
	private  final UserRepository userRepository;
	private final ApiProjectRepository apiRepository;
	
	public ApiProjectController(UserRepository userRepository , ApiProjectRepository apiRepository) {
		this.userRepository=userRepository;
		this.apiRepository=apiRepository;
	}
	

	@PostMapping("/create")
	public ApiProject createApi(Authentication auth,
	                             @RequestBody Map<String, String> req) {

	    String email = auth.getName();

	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    ApiProject api = new ApiProject();
	    api.setUserId(user.getId());
	    api.setName(req.get("name"));
	    api.setMethod(req.getOrDefault("method", "GET"));
	    api.setResponse(req.getOrDefault("response", "{\"msg\":\"Hello {{name}}\"}"));

	    ApiProject saved = apiRepository.save(api);

	    saved.setEndpoint("/api/project/" + saved.getId());

	    return apiRepository.save(saved);
	}
	
@GetMapping("/all")
public List<ApiProject> getAll(Authentication auth) {

    String email = auth.getName();
    User user = userRepository.findByEmail(email)
            .orElseThrow();

    return apiRepository.findByUserId(user.getId());
}


@GetMapping("/{id}")
public ResponseEntity<?> executeApi(
        @PathVariable Long id,
        @RequestParam(required = false) Map<String, String> queryParams
) {

    ApiProject api = apiRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("API not found"));

    String response = api.getResponse();

    if (queryParams != null) {
        for (String key : queryParams.keySet()) {
            response = response.replace(
                    "{{" + key + "}}",
                    queryParams.get(key)
            );
        }
    }

    try {
        Object json = new ObjectMapper().readValue(response, Object.class);
        return ResponseEntity.ok(json);
    } catch (Exception e) {
        return ResponseEntity.ok(response);
    }
}
@PostMapping("/{id}")
public ResponseEntity<?> executePost(
        @PathVariable Long id,
        @RequestBody Map<String, Object> body
) {

    ApiProject api = apiRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("API not found"));

    String response = api.getResponse();

    
    for (String key : body.keySet()) {
        response = response.replace("{{" + key + "}}", body.get(key).toString());
    }

    try {
        Object json = new ObjectMapper().readValue(response, Object.class);
        return ResponseEntity.ok(json);

    } catch (Exception e) {
        return ResponseEntity.ok(response);
    }
}
}
