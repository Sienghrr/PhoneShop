package com.sieng.java.phoneshop_sieng.config.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService {
	private final PasswordEncoder  passwordEncoder;

	@Override
	public Optional<AuthUser> getUserByUsername(String username) {
		
		List<AuthUser> users =  List.of(
				new AuthUser("yo", passwordEncoder.encode("yo123"), RoleEnum.SALE.getAuthorities(), true, true, true, true),
				new AuthUser("ya", passwordEncoder.encode("ya123"), RoleEnum.ADMIN.getAuthorities(), true, true, true, true)
				);
		return users.stream()
			.filter(user-> user.getUsername().equals(username))
			.findFirst();		
	
	}

}
