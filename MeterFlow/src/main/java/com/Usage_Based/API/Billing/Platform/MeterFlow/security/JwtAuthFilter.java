package com.Usage_Based.API.Billing.Platform.MeterFlow.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.Usage_Based.API.Billing.Platform.MeterFlow.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	
	  @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private UserRepository userRepo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 String path = request.getRequestURI();

		    if (path.startsWith("/api/auth") || path.startsWith("/api/webhook")) {
		        filterChain.doFilter(request, response);
		        return;
		    }

		    String authHeader = request.getHeader("Authorization");

		    if (authHeader != null && authHeader.startsWith("Bearer ")) {

		     //   String token = authHeader.substring(7);
			 String token = authHeader.substring(7).trim();

		        if (jwtUtil.validateToken(token)) {

		            String email = jwtUtil.extractEmail(token);
		            String role = jwtUtil.extractRole(token);

		            UsernamePasswordAuthenticationToken authentication =
		                    new UsernamePasswordAuthenticationToken(
		                            email,
		                            null,
		                       //     List.of(new SimpleGrantedAuthority(role))
							   List.of(new SimpleGrantedAuthority(role)
		                    ));

		            
		            SecurityContextHolder.getContext().setAuthentication(authentication);
		        }
		    }

		    filterChain.doFilter(request, response);
	}
}
	
	    
