package com.Usage_Based.API.Billing.Platform.MeterFlow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import com.Usage_Based.API.Billing.Platform.MeterFlow.gateway.ApiGatewayFilter;
import com.Usage_Based.API.Billing.Platform.MeterFlow.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {
	
	 private final ApiGatewayFilter apiGatewayFilter;
	    private final JwtAuthFilter jwtAuthFilter;

	    public SecurityConfig(ApiGatewayFilter apiGatewayFilter,
	                          JwtAuthFilter jwtAuthFilter) {
	        this.apiGatewayFilter = apiGatewayFilter;
	        this.jwtAuthFilter = jwtAuthFilter;
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http
	            .csrf(csrf -> csrf.disable())

	            .cors(cors -> {})

	            .sessionManagement(session ->
	                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            )

	            .authorizeHttpRequests(auth -> auth

	                // ✅ PUBLIC APIs
	                .requestMatchers(
	                        "/api/auth/**",
	                        "/api/webhook/**",
	                        "/swagger-ui/**",
	                        "/v3/api-docs/**"
	                ).permitAll()

	                // ✅ JWT PROTECTED APIs
	                .requestMatchers(
	                        "/api/dashboard/**",
	                        "/api/billing/**",
	                       
	                        "/api/payment/**",
	                        "/api/usage/**",
	                        "/api/subscription/**",
	                        "/api/key/**",
	                        "/api/project/**"
	                ).authenticated()

	                // ✅ API KEY PROTECTED (external APIs)
	                .requestMatchers("/api/external/**").permitAll()
	                
	                .requestMatchers("/api/data").permitAll()
	                .requestMatchers("/api/payment/**").permitAll()
	                .requestMatchers("/api/subscription/**").permitAll()
                    .requestMatchers("/api/usage/**").permitAll()
                    .requestMatchers("/api/project/**").permitAll()
	                // ✅ ADMIN
	                .requestMatchers("/admin/**").hasRole("ADMIN")

	                .anyRequest().authenticated()
	            )

	            // 🔐 JWT FILTER FIRST
	            .addFilterBefore(jwtAuthFilter,
	                    org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)

	            // 🔑 API KEY FILTER AFTER JWT
	            .addFilterAfter(apiGatewayFilter, JwtAuthFilter.class);

	        return http.build();
	    }
	}
