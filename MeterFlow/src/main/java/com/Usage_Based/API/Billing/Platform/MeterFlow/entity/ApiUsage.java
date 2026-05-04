package com.Usage_Based.API.Billing.Platform.MeterFlow.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;


@Entity
//@Builder
//@Data
public class ApiUsage {

//    @Id 
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String apiKey;
//    private String endpoint;
//    private int statusCode;
//    private long responseTime;
//    private LocalDateTime timestamp;
//    
//
//	public ApiUsage(Long id, String apiKey, String endpoint, int statusCode, long responseTime,
//			LocalDateTime timestamp) {
//		super();
//		this.id = id;
//		this.apiKey = apiKey;
//		this.endpoint = endpoint;
//		this.statusCode = statusCode;
//		this.responseTime = responseTime;
//		this.timestamp = timestamp;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getApiKey() {
//		return apiKey;
//	}
//
//	public void setApiKey(String apiKey) {
//		this.apiKey = apiKey;
//	}
//
//	public String getEndpoint() {
//		return endpoint;
//	}
//
//	public void setEndpoint(String endpoint) {
//		this.endpoint = endpoint;
//	}
//
//	public int getStatusCode() {
//		return statusCode;
//	}
//
//	public void setStatusCode(int statusCode) {
//		this.statusCode = statusCode;
//	}
//
//	public long getResponseTime() {
//		return responseTime;
//	}
//
//	public void setResponseTime(long responseTime) {
//		this.responseTime = responseTime;
//	}
//
//	public LocalDateTime getTimestamp() {
//		return timestamp;
//	}
//
//	public void setTimestamp(LocalDateTime timestamp) {
//		this.timestamp = timestamp;
//	}
//
//	public ApiUsage() {
//		// TODO Auto-generated constructor stub
//	}
//
//}
	
	
	//testing
//	  @Id 
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	    private Long id;
//	  private Long userId; // 🔥 ADD THIS (CRITICAL FIX)
//
//	    private String apiKey;
//	    private String endpoint;
//	    private int statusCode;
//	    private long responseTime;
//	    private LocalDateTime timestamp;
//	    
//		public ApiUsage(Long id, Long userId, String apiKey, String endpoint, int statusCode, long responseTime,
//				LocalDateTime timestamp) {
//			super();
//			this.id = id;
//			this.userId = userId;
//			this.apiKey = apiKey;
//			this.endpoint = endpoint;
//			this.statusCode = statusCode;
//			this.responseTime = responseTime;
//			this.timestamp = timestamp;
//		}
//		public String getApiKey() {
//			return apiKey;
//		}
//		public void setApiKey(String apiKey) {
//			this.apiKey = apiKey;
//		}
//		public Long getId() {
//			return id;
//		}
//		public void setId(Long id) {
//			this.id = id;
//		}
//		public Long getUserId() {
//			return userId;
//		}
//		public void setUserId(Long userId) {
//			this.userId = userId;
//		}
//
//		public String getEndpoint() {
//			return endpoint;
//		}
//		public void setEndpoint(String endpoint) {
//			this.endpoint = endpoint;
//		}
//		public int getStatusCode() {
//			return statusCode;
//		}
//		public void setStatusCode(int statusCode) {
//			this.statusCode = statusCode;
//		}
//		public long getResponseTime() {
//			return responseTime;
//		}
//		public void setResponseTime(long responseTime) {
//			this.responseTime = responseTime;
//		}
//		public LocalDateTime getTimestamp() {
//			return timestamp;
//		}
//		public void setTimestamp(LocalDateTime timestamp) {
//			this.timestamp = timestamp;
//		}
//
//		public ApiUsage() {
//			super();
//			// TODO Auto-generated constructor stub
//		}
//	    
//}
	
	//testing 1
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String apiKey;

	    private Long projectId;   // ✅ NEW (IMPORTANT)

	    private String endpoint;

	    private int statusCode;

	    private long responseTime;

	    private LocalDateTime timestamp;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

		public Long getProjectId() {
			return projectId;
		}

		public void setProjectId(Long projectId) {
			this.projectId = projectId;
		}

		public String getEndpoint() {
			return endpoint;
		}

		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public long getResponseTime() {
			return responseTime;
		}

		public void setResponseTime(long responseTime) {
			this.responseTime = responseTime;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}

		public ApiUsage(Long id, String apiKey, Long projectId, String endpoint, int statusCode, long responseTime,
				LocalDateTime timestamp) {
			super();
			this.id = id;
			this.apiKey = apiKey;
			this.projectId = projectId;
			this.endpoint = endpoint;
			this.statusCode = statusCode;
			this.responseTime = responseTime;
			this.timestamp = timestamp;
		}

		public ApiUsage() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	}
	    
