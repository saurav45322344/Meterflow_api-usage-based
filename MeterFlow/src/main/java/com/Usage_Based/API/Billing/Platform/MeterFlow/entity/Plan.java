package com.Usage_Based.API.Billing.Platform.MeterFlow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Plan {

	 @Id @GeneratedValue
	    private Long id;

	    private String name;
	    private double price;
	    private Long requestLimit;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public Long getRequestLimit() {
			return requestLimit;
		}
		public void setRequestLimit(Long requestLimit) {
			this.requestLimit = requestLimit;
		}
		public Plan(Long id, String name, double price, Long requestLimit) {
			super();
			this.id = id;
			this.name = name;
			this.price = price;
			this.requestLimit = requestLimit;
		}
		public Plan() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	}


