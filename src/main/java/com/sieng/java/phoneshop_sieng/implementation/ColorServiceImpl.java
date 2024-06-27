package com.sieng.java.phoneshop_sieng.implementation;

import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.Exception.ResourceNotFoundException;
import com.sieng.java.phoneshop_sieng.entity.Color;
import com.sieng.java.phoneshop_sieng.repository.ColorRepository;
import com.sieng.java.phoneshop_sieng.service.ColorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ColorServiceImpl  implements ColorService{
	  
	private final ColorRepository colorRepository;
	@Override
	public Color create(Color color) {
		return colorRepository.save(color);
	}

	@Override
	public Color getById(Long id) {
		return colorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Color", id));
	}

}
