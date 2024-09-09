package com.sieng.java.phoneshop_sieng.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sieng.java.phoneshop_sieng.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);

}
