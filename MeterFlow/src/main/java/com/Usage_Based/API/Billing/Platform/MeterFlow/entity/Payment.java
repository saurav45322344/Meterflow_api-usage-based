package com.Usage_Based.API.Billing.Platform.MeterFlow.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Payment {

	 @Id
	 //@GeneratedValue
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long userId;

	    private String orderId;

	    private String paymentId;

	    private String signature;

	    private Double amount;

	    private String planName;

	    @Enumerated(EnumType.STRING)
	    private PaymentStatus status;

	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
	    
	 
		
	    
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
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		public String getPaymentId() {
			return paymentId;
		}
		public void setPaymentId(String paymentId) {
			this.paymentId = paymentId;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public String getPlanName() {
			return planName;
		}
		public void setPlanName(String planName) {
			this.planName = planName;
		}
		public PaymentStatus getStatus() {
			return status;
		}
		public void setStatus(PaymentStatus status) {
			this.status = status;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
		public Payment(Long id, Long userId, String orderId, String paymentId, String signature, Double amount,
				String planName, PaymentStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
			super();
			this.id = id;
			this.userId = userId;
			this.orderId = orderId;
			this.paymentId = paymentId;
			this.signature = signature;
			this.amount = amount;
			this.planName = planName;
			this.status = status;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
		}
		public Payment() {
			super();
			// TODO Auto-generated constructor stub
		}
}