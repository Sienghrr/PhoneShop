package com.sieng.java.phoneshop_sieng.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.sieng.java.phoneshop_sieng.entity.Brand;

public interface BrandService {
	
	Brand create(Brand brand);
	Brand getbyId(Long id);
	Brand update(Long id ,Brand brandUpdate);
	List<Brand> getBrands(String name);
	//List<Brand> getBrands(Map<String, String>params);
	Page<Brand> getBrands(Map<String, String>params);
	//Brand remove(Long id);



}
