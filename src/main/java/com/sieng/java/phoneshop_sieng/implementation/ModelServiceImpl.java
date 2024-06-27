package com.sieng.java.phoneshop_sieng.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.Exception.ResourceNotFoundException;
import com.sieng.java.phoneshop_sieng.entity.Model;
import com.sieng.java.phoneshop_sieng.repository.ModelRepository;
import com.sieng.java.phoneshop_sieng.service.ModelService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
	
	private final ModelRepository modelRepository;

	@Override
	public Model create(Model model) {
		return modelRepository.save(model);
	}

	@Override
	public List<Model> getByBrand(Long brandId) {
		return modelRepository.findByBrandId(brandId);
		
	}

	@Override
	public Model getById(Long id) {
		return modelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Model", id));
	}

}
