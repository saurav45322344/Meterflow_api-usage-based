package com.Usage_Based.API.Billing.Platform.MeterFlow.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Payment;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.PaymentStatus;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.PaymentRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.service.SubscriptionService;
import com.razorpay.Utils;

@RestController
@RequestMapping("/api/webhook")
public class RazorpayWebhookController {

	 @Value("${razorpay.secret}")
	    private String secret;

	    @Autowired
	    private PaymentRepository paymentRepo;

	    @Autowired
	    private SubscriptionService subscriptionService;

	    @PostMapping
	    public ResponseEntity<?> handleWebhook(
	            @RequestBody String payload,
	            @RequestHeader("X-Razorpay-Signature") String signature
	    ) {

	        try {
	            boolean isValid = Utils.verifyWebhookSignature(payload, signature, secret);

	            if (!isValid) return ResponseEntity.status(400).body("Invalid");

	            JSONObject json = new JSONObject(payload);
	            String event = json.getString("event");

	            if (event.equals("payment.captured")) {

	                JSONObject entity = json
	                        .getJSONObject("payload")
	                        .getJSONObject("payment")
	                        .getJSONObject("entity");

	                String orderId = entity.getString("order_id");
	                String paymentId = entity.getString("id");

	                Payment payment = paymentRepo.findByOrderId(orderId).orElseThrow() ;

	                
	                if ("SUCCESS".equals(payment.getStatus())) {
	                    return ResponseEntity.ok("Already processed");
	                }

	                payment.setPaymentId(paymentId);
	               // payment.setStatus("SUCCESS");
	                payment.setStatus(PaymentStatus.SUCCESS);
	                paymentRepo.save(payment);

	                
               //subscriptionService.activatePlan(payment.getUserId());
	                subscriptionService.activatePlan(
	                	    payment.getUserId(),
	                	    payment.getPlanName() 
	                	);

	            }

	            return ResponseEntity.ok("Webhook processed");

	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error");
	        }
	    }
	}
