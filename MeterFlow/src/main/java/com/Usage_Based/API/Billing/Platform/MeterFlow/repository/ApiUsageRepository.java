package com.Usage_Based.API.Billing.Platform.MeterFlow.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Usage_Based.API.Billing.Platform.MeterFlow.entity.ApiUsage;


//@Repository
public interface ApiUsageRepository extends JpaRepository<ApiUsage, Long> {
	
	
	long countByApiKey(String apiKey);

    List<ApiUsage> findByApiKey(String apiKey);

    List<ApiUsage> findByApiKeyAndTimestampBetween(
            String apiKey,
            LocalDateTime start,
            LocalDateTime end
    );

    @Query("SELECT COUNT(u) FROM ApiUsage u WHERE u.apiKey = :apiKey AND DATE(u.timestamp) = CURRENT_DATE")
    long countTodayUsage(@Param("apiKey") String apiKey);
    
    
    @Query("""
    		SELECT DATE(u.timestamp), COUNT(u)
    		FROM ApiUsage u
    		WHERE u.apiKey = :apiKey
    		GROUP BY DATE(u.timestamp)
    		ORDER BY DATE(u.timestamp)
    		""")
    		List<Object[]> getDailyUsageRaw(@Param("apiKey") String apiKey);
    		
    		   long countByProjectId(Long projectId); 
    		
}
	