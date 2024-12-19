package com.sieng.java.phoneshop_sieng.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.sieng.java.phoneshop_sieng.config.jwt.JwtLonginFilter;
import com.sieng.java.phoneshop_sieng.config.jwt.TokenVerify;

@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
@Override
protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable()
		.addFilter(new JwtLonginFilter(authenticationManager()))
		.addFilterAfter(new TokenVerify(), JwtLonginFilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// stateless mean not store in db opposite of stateful
		.and()
		.authorizeHttpRequests()
		.antMatchers("/","index.html","css/**","js/**").permitAll()
		//.antMatchers("/brands").hasRole("SALE")
		//.antMatchers(HttpMethod.POST,"/brands").hasAuthority("brand:write")
		//.antMatchers("/models").hasRole(RoleEnum.SALE.name()) // SALE
		//.antMatchers(HttpMethod.POST,"/brands").hasAuthority(BRAND_WRITE.getDescription())
		//.antMatchers(HttpMethod.GET,"/brands").hasAuthority(BRAND_READ.getDescription())
		.anyRequest()
		.authenticated();
		
}

@Bean
@Override
	protected UserDetailsService userDetailsService() {
	
	//User user1 = new User("sieng", passwordEncoder.encode("sieng123"), Collections.emptyList());
	
	UserDetails user2 = User.builder()
	.username("ly")
	.password(passwordEncoder.encode("ly123"))
	//.roles("ADMIN") // ROLE_ADMIN
	.authorities(RoleEnum.ADMIN.getAuthorities())
	.build();
	UserDetails user1 = User.builder()
			.username("sieng")
			.password(passwordEncoder.encode("sieng123"))
			//.roles("SALE") // ROLE_SALE
			.authorities(RoleEnum.SALE.getAuthorities())
			.build();
	
	UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user2,user1);	
	
		return userDetailsService;
	}
}
