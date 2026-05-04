package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import java.nio.charset.StandardCharsets;
import java.util.HexFormat;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Payment;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.PaymentStatus;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.User;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.PaymentRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.UserRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.security.JwtUtil;
import com.Usage_Based.API.Billing.Platform.MeterFlow.service.PaymentService;
import com.Usage_Based.API.Billing.Platform.MeterFlow.service.SubscriptionService;



@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepo;

	@Value("${razorpay.secret}")
	private String razorpaySecret;

	@Autowired
	private PaymentRepository paymentRepo;
	
	private final SubscriptionService subscriptionService;

	public PaymentController(SubscriptionService subscriptionService) {
	    this.subscriptionService = subscriptionService;
	}
	
	@PostMapping("/create-order")
	public ResponseEntity<?> createOrder(
			@RequestHeader("Authorization") String token,
			@RequestBody Map<String, String> req
			) throws Exception {

		String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found for email: " + email));

		String plan = req.get("plan");

		return ResponseEntity.ok(
				paymentService.createOrder(user.getId(), plan)
				);
	}

	@PostMapping("/verify")
	public ResponseEntity<?> verify(@RequestBody Map<String, String> data, Authentication auth) {

	    try {
	        String orderId = data.get("razorpay_order_id");
	        String paymentId = data.get("razorpay_payment_id");
	        String signature = data.get("razorpay_signature");

	        String payload = orderId + "|" + paymentId;

	        boolean isValid = verifySignature(payload, signature);

	        if (!isValid) {
	            System.out.println("INVALID SIGNATURE");
	            return ResponseEntity.status(400).body("Verification Failed");
	        }

	        System.out.println("✅ VERIFIED SUCCESS");

	    
	        Payment payment = paymentRepo.findByOrderId(orderId)
	                .orElseThrow(() -> new RuntimeException("Payment not found"));

	        payment.setPaymentId(paymentId);
	        payment.setStatus(PaymentStatus.SUCCESS);

	        paymentRepo.save(payment);

	        subscriptionService.activatePlan(
	                payment.getUserId(),
	                payment.getPlanName()
	        );

	        System.out.println("Plan Activated: " + payment.getPlanName());

	        return ResponseEntity.ok("Payment Verified & Plan Activated");

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error");
	    }
	}
	
	private boolean verifySignature(String payload, String actualSignature) {
		try {
			String secret = razorpaySecret;

			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(
					secret.getBytes(StandardCharsets.UTF_8),
					"HmacSHA256"
					);

			sha256_HMAC.init(secret_key);

			byte[] hash = sha256_HMAC.doFinal(payload.getBytes(StandardCharsets.UTF_8));

			String generatedSignature = HexFormat.of().formatHex(hash);

			return generatedSignature.equals(actualSignature);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
