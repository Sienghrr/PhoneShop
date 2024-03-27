package com.sieng.java.phoneshop_sieng.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.Exception.ResourceNotFoundException;
import com.sieng.java.phoneshop_sieng.entity.Brand;
import com.sieng.java.phoneshop_sieng.repository.BrandRepository;
import com.sieng.java.phoneshop_sieng.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
		
		return brandRepository.save(brand);
	}

	@Override
	public Brand findbyId(Integer id) {
		return brandRepository.findById(id)
				//.orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format( "Brand with id = %d not found", id)));
				.orElseThrow(()->new ResourceNotFoundException("brand", id));
		
		
	}

	@Override
	public Brand update(Integer id, Brand brandUpdate) {
		Brand brand = findbyId(id);
		brand.setName(brandUpdate.getName());
		return brandRepository.save(brand);
	}

	@Override
	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}

	@Override
	public List<Brand> getBrands(String name) {
		return brandRepository.findByNameContaining(name);
	}

}
