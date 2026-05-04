package com.Usage_Based.API.Billing.Platform.MeterFlow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiKey;


public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

	
	//ApiKey findByKeyValue(String keyValue);
	
	Optional<ApiKey> findByKeyValue(String keyValue);
	
    long countByActiveTrue(); 
    
    Optional<ApiKey> findById(Long id);
    
    Optional<ApiKey> findByUserId(Long userId);
    
    List<ApiKey> findAllByUserId(Long userId);

	long countByUserIdAndActiveTrue(Long userId);
 
	Optional<ApiKey> findByKeyValueAndActiveTrue(String keyValue);

    
  
}
