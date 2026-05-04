package com.Usage_Based.API.Billing.Platform.MeterFlow.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ApiProject {

	@Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;
    private String endpoint; // /api/project/{id}

    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String method; // ✅ GET / POST

    @Column(columnDefinition = "TEXT")
    private String response; // ✅ ADD THIS (CRITICAL)
    
	public ApiProject(Long id, Long userId, String name, String endpoint, boolean active, LocalDateTime createdAt,
			String method, String response) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.endpoint = endpoint;
		this.active = active;
		this.createdAt = createdAt;
		this.method = method;
		this.response = response;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public ApiProject() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}