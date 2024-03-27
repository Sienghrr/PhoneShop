package com.sieng.java.phoneshop_sieng.service;

import java.util.List;

import com.sieng.java.phoneshop_sieng.entity.Brand;

public interface BrandService {
	
	Brand create(Brand brand);
	Brand findbyId(Integer id);
	Brand update(Integer id ,Brand brandUpdate);
	List<Brand> getBrands();
	List<Brand> getBrands(String name);


}
