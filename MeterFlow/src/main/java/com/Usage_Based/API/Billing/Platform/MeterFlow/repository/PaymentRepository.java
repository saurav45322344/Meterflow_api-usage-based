package com.Usage_Based.API.Billing.Platform.MeterFlow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	
	   Optional<Payment> findByOrderId(String orderId);
	   
	   List<Payment> findByUserId(Long userId);
}
