package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.dto.AuthResponse;
import com.Usage_Based.API.Billing.Platform.MeterFlow.dto.LoginRequest;
import com.Usage_Based.API.Billing.Platform.MeterFlow.dto.SignupRequest;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Roles;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.User;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.UserRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor
public class AuthController {

	 private final UserRepository userRepo;
	    private final JwtUtil jwtUtil;
	    private final PasswordEncoder encoder;

	 
	    public AuthController(UserRepository userRepo,
	                          JwtUtil jwtUtil,
	                          PasswordEncoder encoder) {
	        this.userRepo = userRepo;
	        this.jwtUtil = jwtUtil;
	        this.encoder = encoder;
	    }

	   
	    @PostMapping("/signup")
	    public AuthResponse signup(@RequestBody SignupRequest req) {

	        if (userRepo.findByEmail(req.email).isPresent()) {
	            throw new RuntimeException("Email already exists");
	        }

	        User user = new User();
	        user.setEmail(req.email);
	        user.setPassword(encoder.encode(req.password));

	      
	        if (req.roles == null || req.roles.isEmpty()) {
	            user.setRoles(Set.of(Roles.ROLE_CONSUMER));
	        } else {
	            user.setRoles(req.roles);
	        }

	        userRepo.save(user);

	        String token = jwtUtil.generateToken(user.getEmail());

	        return new AuthResponse(token, "User registered successfully");
	    }

	    
	    @PostMapping("/login")
	    public AuthResponse login(@RequestBody LoginRequest req) {

	        User user = userRepo.findByEmail(req.email)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        if (!encoder.matches(req.password, user.getPassword())) {
	            throw new RuntimeException("Invalid credentials");
	        }

	        String token = jwtUtil.generateToken(user.getEmail());

	        return new AuthResponse(token, "Login successful");
	    }
	}
	