package com.sieng.java.phoneshop_sieng.config.security;

import java.util.Optional;

public interface UserService {
	Optional<AuthUser> getUserByUsername(String username);

}
