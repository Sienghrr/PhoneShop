package com.sieng.java.phoneshop_sieng.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sieng.java.phoneshop_sieng.dto.BrandDTO;
import com.sieng.java.phoneshop_sieng.dto.PageDTO;
import com.sieng.java.phoneshop_sieng.entity.Brand;
import com.sieng.java.phoneshop_sieng.mapper.BrandMapper;
import com.sieng.java.phoneshop_sieng.service.BrandService;

@RestController
@RequestMapping("brands")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.Instance.toBrand(brandDTO);
		brand = brandService.create(brand);		
		
		return ResponseEntity.ok(BrandMapper.Instance.toBrandDTO(brand));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findBrandbyid(@PathVariable Integer id){
		Brand brand = brandService.findbyId(id);
		return ResponseEntity.ok(BrandMapper.Instance.toBrandDTO(brand));
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updatebrand(@PathVariable("id") Integer brandId , @RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.Instance.toBrand(brandDTO);
		Brand updatedBrand = brandService.update(brandId, brand);
		return ResponseEntity.ok(BrandMapper.Instance.toBrandDTO(updatedBrand));
		
	}
	
	
	
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
		Page<Brand> page = brandService.getBrands(params);
		PageDTO pageDTO = new PageDTO(page);
				
		/*List<BrandDTO> list = brandService.getBrands(params)
		.stream()
		.map(br->BrandMapper.Instance.toBrandDTO(br))
		.collect(Collectors.toList());
		return ResponseEntity.ok(list); */
		
		
		return ResponseEntity.ok(pageDTO);
		
		
	}

}
