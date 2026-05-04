package com.Usage_Based.API.Billing.Platform.MeterFlow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.User;


//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// User findByEmail(String email);
	 Optional<User> findByEmail(String email);
	 //Optional<User> findByEmailIgnoreCase(String email);
	 Optional<User> findById(Long id);
	 

	 
	 
}
