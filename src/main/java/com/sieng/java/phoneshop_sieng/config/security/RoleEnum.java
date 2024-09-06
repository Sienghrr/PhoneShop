package com.sieng.java.phoneshop_sieng.config.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import static com.sieng.java.phoneshop_sieng.config.security.PermissionEnum.*;

@Getter
@AllArgsConstructor
public enum RoleEnum {
	
	ADMIN(Set.of(BRAND_READ,BRAND_WRITE,MODEL_READ,MODEL_WRITE)),
	SALE(Set.of(BRAND_READ,MODEL_READ));
	
	private Set<PermissionEnum> permissions;
	
	public Set<SimpleGrantedAuthority> getAuthorities(){
		Set<SimpleGrantedAuthority> Grantedauthorities = this.permissions.stream()
		.map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
		.collect(Collectors.toSet());
		
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+this.name());
		Grantedauthorities.add(role);
		
		return Grantedauthorities;
	}

}
