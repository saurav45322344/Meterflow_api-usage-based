package com.Usage_Based.API.Billing.Platform.MeterFlow.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Subscription {


	@Id @GeneratedValue
	private Long id;

	private Long userId;
	private String plan; // FREE / PRO
	private String status; // ACTIVE / EXPIRED

	private LocalDateTime startDate;
	private LocalDateTime expiryDate;
	private Long requestLimit;

	private boolean active;
	
	
	public Subscription(Long id, Long userId, String plan, String status, LocalDateTime startDate,
			LocalDateTime expiryDate, Long requestLimit, boolean active) {
		super();
		this.id = id;
		this.userId = userId;
		this.plan = plan;
		this.status = status;
		this.startDate = startDate;
		this.expiryDate = expiryDate;
		this.requestLimit = requestLimit;
		this.active = active;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Long getRequestLimit() {
		return requestLimit;
	}
	public void setRequestLimit(Long requestLimit) {
		this.requestLimit = requestLimit;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}
	//	public Subscription(Long id, Long userId, String plan, String status, LocalDateTime startDate,
	//			LocalDateTime expiryDate) {
	//		super();
	//		this.id = id;
	//		this.userId = userId;
	//		this.plan = plan;
	//		this.status = status;
	//		this.startDate = startDate;
	//		this.expiryDate = expiryDate;
	//	}
	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}
}