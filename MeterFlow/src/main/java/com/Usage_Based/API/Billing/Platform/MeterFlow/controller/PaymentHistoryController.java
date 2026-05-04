package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Payment;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.User;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.PaymentRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.UserRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.security.JwtUtil;

@RestController
@RequestMapping("/api/payment")
public class PaymentHistoryController {

	@Autowired
    private PaymentRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/history")
    public ResponseEntity<?> history(@RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userRepo.findByEmail(email).orElseThrow();

        List<Payment> payments = repo.findByUserId(user.getId());

        return ResponseEntity.ok(payments);
    }


}
