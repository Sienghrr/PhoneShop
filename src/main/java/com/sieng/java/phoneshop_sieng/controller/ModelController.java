package com.sieng.java.phoneshop_sieng.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sieng.java.phoneshop_sieng.dto.ModelDTO;
import com.sieng.java.phoneshop_sieng.entity.Model;
import com.sieng.java.phoneshop_sieng.mapper.ModelEntityMapper;
import com.sieng.java.phoneshop_sieng.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/models")
public class ModelController {
	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;
	
	@RolesAllowed("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
		Model model = modelMapper.toModel(modelDTO);
		model = modelService.create(model);
		return ResponseEntity.ok(modelMapper.toModelDTO(model));
	}

}
