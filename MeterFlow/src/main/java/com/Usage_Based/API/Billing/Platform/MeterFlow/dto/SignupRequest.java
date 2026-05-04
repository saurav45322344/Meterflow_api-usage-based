package com.Usage_Based.API.Billing.Platform.MeterFlow.dto;

import java.util.Set;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.Roles;

import lombok.Data;



@Data
public class SignupRequest {
    
	public String email;
    public String password;
    public Set<Roles> roles;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Roles> getRoles() {
		return roles;
	}
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	public SignupRequest(String email, String password, Set<Roles> roles) {
		super();
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public SignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    

}
