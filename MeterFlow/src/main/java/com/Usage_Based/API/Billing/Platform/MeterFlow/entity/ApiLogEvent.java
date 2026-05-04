package com.Usage_Based.API.Billing.Platform.MeterFlow.entity;

import java.time.LocalDateTime;

public class ApiLogEvent {

	 private String apiKey;
	    private String endpoint;
	    private String method;
	    private int status;
	    private long latency;
	    private String userId;
	    private LocalDateTime timestamp;
	}
