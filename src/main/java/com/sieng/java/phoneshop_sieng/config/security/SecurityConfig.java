package com.sieng.java.phoneshop_sieng.config.security;

import java.util.Collections;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder; // because spring is standard so we cannot use normal password
						//we need to encrypt our password
	
@Override
protected void configure(HttpSecurity http) throws Exception {
	http.authorizeHttpRequests()
		.antMatchers("/","index.html","css/**","js/**").permitAll()
		.antMatchers("/brands").hasRole("SALE")
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic(); 

	// this whole configure is convert from form to basic auth
	//basic spring security
		    
}

@Bean // if our method not void return type we must put @bean
@Override
	protected UserDetailsService userDetailsService() {
	
	//User user1 = new User("sieng", passwordEncoder.encode("sieng123"), Collections.emptyList());
	
	UserDetails user2 = User.builder()
	.username("ly")
	.password(passwordEncoder.encode("ly123"))
	.roles("ADMIN") // ROLE_ADMIN
	.build();
	UserDetails user1 = User.builder()
			.username("sieng")
			.password(passwordEncoder.encode("sieng123"))
			.roles("SALE") // ROLE_SALE
			.build();
	
	UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user2,user1);	
	
		return userDetailsService;
	}
}
