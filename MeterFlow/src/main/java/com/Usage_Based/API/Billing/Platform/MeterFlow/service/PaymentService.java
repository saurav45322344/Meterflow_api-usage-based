package com.Usage_Based.API.Billing.Platform.MeterFlow.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Payment;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.PaymentStatus;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.User;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {


	@Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @Autowired
    private PaymentRepository paymentRepo;

    
    //working code 
//    public String createOrder(Long userId, int amount) throws Exception {
//
//        RazorpayClient client = new RazorpayClient(key, secret);
//
//        JSONObject options = new JSONObject();
//        options.put("amount", amount * 100);
//        options.put("currency", "INR");
//        options.put("receipt", "user_" + userId);
//
//        Order order = client.orders.create(options);
//
//        Payment p = new Payment();
//        p.setOrderId(order.get("id"));
//        p.setUserId(userId);
//        p.setAmount(amount);
//        p.setStatus("CREATED");
//        p.setCreatedAt(LocalDateTime.now());
//
//        paymentRepo.save(p);
//
//        return order.toString();
//    }
//}
    
    
    public Map<String, Object> createOrder(Long userId, String plan) throws Exception {

        int amount;

        switch (plan) {
            case "BASIC":
                amount = 19900;
                break;
            case "PRO":
                amount = 49900;
                break;
            case "ENTERPRISE":
                amount = 99900;
                break;
            default:
                throw new RuntimeException("Invalid plan");
        }

        RazorpayClient client = new RazorpayClient(key, secret);

        JSONObject options = new JSONObject();
        options.put("amount", amount);
        options.put("currency", "INR");
        options.put("receipt", "user_" + userId);

        Order order = client.orders.create(options);

        
        Payment payment = new Payment();
        payment.setOrderId(order.get("id"));
        payment.setUserId(userId);
        payment.setAmount(amount / 100.0);
        payment.setPlanName(plan); 
       //payment.setStatus("CREATED");
        //payment.setUser(user);
        payment.setStatus(PaymentStatus.CREATED);
        payment.setCreatedAt(LocalDateTime.now());

        paymentRepo.save(payment);

        
        Map<String, Object> response = new HashMap<>();
        response.put("id", order.get("id"));
        response.put("amount", order.get("amount"));
        response.put("currency", order.get("currency"));

        return response;
    }
}
