package com.Usage_Based.API.Billing.Platform.MeterFlow.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class UsageProducer {

	 private final KafkaTemplate<String, String> kafkaTemplate;

	 public UsageProducer(KafkaTemplate<String, String> kafkaTemplate) {
		 this.kafkaTemplate =kafkaTemplate;
	 }
	 
	    public void send(String msg) {
	        kafkaTemplate.send("usage-topic", msg);
	    }
	}