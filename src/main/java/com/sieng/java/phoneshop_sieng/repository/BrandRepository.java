package com.sieng.java.phoneshop_sieng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sieng.java.phoneshop_sieng.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	List<Brand> findByNameContaining(String name);

}
