package com.sieng.java.phoneshop_sieng.implementation;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.Exception.ApiException;
import com.sieng.java.phoneshop_sieng.config.security.AuthUser;
import com.sieng.java.phoneshop_sieng.config.security.UserService;
import com.sieng.java.phoneshop_sieng.entity.Role;
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
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User Not Found"));

		AuthUser authUser = AuthUser.builder().username(user.getUsername()).password(user.getPassword())
				.authorities(getAuthorities(user.getRoles()))
				.accountNonExpired(user.isAccountNonExpired()).accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired()).enabled(user.isEnabled()).build();
		return Optional.ofNullable(authUser);
	}

	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles) {
		
		Set<SimpleGrantedAuthority> authorities1 = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName()))
			 .collect(Collectors.toSet());

		 Set<SimpleGrantedAuthority> authorities = roles.stream()
					.flatMap(role -> toStream(role))
					.collect(Collectors.toSet());
		 authorities.addAll(authorities1);
		 return authorities;

	}

	private Stream<SimpleGrantedAuthority> toStream(Role role) {
		return role.getPermissions().stream()
									.map(permission -> new SimpleGrantedAuthority(permission.getName()));
	}

}
