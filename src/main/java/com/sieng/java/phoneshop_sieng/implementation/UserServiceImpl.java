package com.sieng.java.phoneshop_sieng.implementation;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.Exception.ApiException;
import com.sieng.java.phoneshop_sieng.config.security.AuthUser;
import com.sieng.java.phoneshop_sieng.config.security.UserService;
import com.sieng.java.phoneshop_sieng.entity.User;
import com.sieng.java.phoneshop_sieng.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public Optional<AuthUser> getUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
		.orElseThrow(()-> new ApiException(HttpStatus.NOT_FOUND, "User Not Found"));
		
		AuthUser authUser = 
				AuthUser
				.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(user.getRole().getAuthorities())
				.accountNonExpired(user.isAccountNonExpired())
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.build();
		return Optional.ofNullable(authUser);
	}

}
