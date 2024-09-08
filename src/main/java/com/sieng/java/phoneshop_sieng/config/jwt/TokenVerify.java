package com.sieng.java.phoneshop_sieng.config.jwt;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenVerify extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader("Authorization");
		if(Objects.isNull(authorizationHeader)||!authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
						
		}
		
		String token = authorizationHeader.replace("Bearer ", "");
		String secretkeys = "siensweetheart123456siensweetheart123456siensweetheart123456";
		
		Jws<Claims> claimsJws = Jwts
		.parser()
		.setSigningKey(Keys.hmacShaKeyFor(secretkeys.getBytes()))
		.build()
		.parseClaimsJws(token);
		
		Claims body = claimsJws.getBody();
		String username = body.getSubject();
		java.util.List<Map<String, String>> authorities =(java.util.List<Map<String,String>>)body.get("authorities");
		
		Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
		.map(x ->new SimpleGrantedAuthority(x.get("authority")))
		.collect(Collectors.toSet());
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);		
		filterChain.doFilter(request, response);
	}

}
