package com.sieng.java.phoneshop_sieng.config.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtLonginFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager ;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();// convert json to java  we call it deserialize
							// convert java to json we call it serialize
		try {
			LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String secretkeys = "siensweetheart123456siensweetheart123456siensweetheart123456";
		String token = 
				Jwts
				.builder()
				.setSubject(authResult.getName())
				.setIssuedAt(new Date())
				.claim("authorities", authResult.getAuthorities())
				.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(7)))
				.setIssuer("phoneshop.com")
				.signWith(Keys.hmacShaKeyFor(secretkeys.getBytes()))
				.compact();
		
		response.setHeader("Authorization", "Bearer "+token);
	}
}
