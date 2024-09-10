package com.sieng.java.phoneshop_sieng.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sieng.java.phoneshop_sieng.config.jwt.FilterChainExceptionHandler;
import com.sieng.java.phoneshop_sieng.config.jwt.JwtLonginFilter;
import com.sieng.java.phoneshop_sieng.config.jwt.TokenVerify;

import io.swagger.models.HttpMethod;

@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfig {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService userDetailsService;	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	private FilterChainExceptionHandler filterChainExceptionHandler;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	http.csrf().disable()
		.addFilter(new JwtLonginFilter(authenticationManager(authenticationConfiguration)))
		.addFilterBefore(filterChainExceptionHandler, JwtLonginFilter.class)
		.addFilterAfter(new TokenVerify(), JwtLonginFilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()		
		.authorizeHttpRequests()
	
		.antMatchers("/","index.html","css/**","js/**").permitAll()	
		.antMatchers(org.springframework.http.HttpMethod.PUT ,"/brands/**").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
		.anyRequest()
		.authenticated();	
        return http.build();
		
}

@Bean
AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
}

@Autowired
public void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.authenticationProvider(getAuthenticationProvider());
}

public AuthenticationProvider getAuthenticationProvider() {
	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	authenticationProvider.setUserDetailsService(userDetailsService);
	authenticationProvider.setPasswordEncoder(passwordEncoder);
	return authenticationProvider;
	
}

}
