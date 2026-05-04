package com.Usage_Based.API.Billing.Platform.MeterFlow.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(unique = true)
    private String email;
    private String password;
    
//extra addon
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Roles> roles = new HashSet<>();
    
    
	

	public User(Long id, String email, String password, Set<Roles> roles) {
	super();
	this.id = id;
	this.email = email;
	this.password = password;
	this.roles = roles;
}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




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


	public User() {
		// TODO Auto-generated constructor stub
	}

}
