package com.Usage_Based.API.Billing.Platform.MeterFlow.dto;

public class AuthResponse {

	public String token;
    public String message;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AuthResponse(String token, String message) {
		super();
		this.token = token;
		this.message = message;
	}
    

}
