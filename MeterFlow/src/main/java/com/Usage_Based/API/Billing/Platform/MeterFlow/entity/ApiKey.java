package com.Usage_Based.API.Billing.Platform.MeterFlow.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class ApiKey {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String keyValue;
	    private Long userId;
	   //private String email;
	    private int rateLimit;
		@Column(name = "active")
	    private boolean active = true;
		
		//extra addon
		 @Enumerated(EnumType.STRING)
		    private ApiKeyEnvironment environment;
		 

	public ApiKeyEnvironment getEnvironment() {
			return environment;
		}

		 public void setEnvironment(ApiKeyEnvironment environment) {
			 this.environment = environment;
		 }

	public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

	public ApiKey(Long id, String keyValue, Long userId, int rateLimit) {
		super();
		this.id = id;
		this.keyValue = keyValue;
		this.userId = userId;
		this.rateLimit = rateLimit;
	}

	public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getKeyValue() {
			return keyValue;
		}

		public void setKeyValue(String keyValue) {
			this.keyValue = keyValue;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public int getRateLimit() {
			return rateLimit;
		}

		public void setRateLimit(int rateLimit) {
			this.rateLimit = rateLimit;
		}

	public ApiKey() {
		// TODO Auto-generated constructor stub
	}

}
