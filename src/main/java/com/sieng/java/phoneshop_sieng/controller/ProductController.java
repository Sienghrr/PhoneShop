package com.sieng.java.phoneshop_sieng.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sieng.java.phoneshop_sieng.dto.BrandDTO;
import com.sieng.java.phoneshop_sieng.dto.ModelDTO;
import com.sieng.java.phoneshop_sieng.dto.PageDTO;
import com.sieng.java.phoneshop_sieng.dto.PriceDto;
import com.sieng.java.phoneshop_sieng.dto.ProductDTO;
import com.sieng.java.phoneshop_sieng.dto.ProductImportDto;
import com.sieng.java.phoneshop_sieng.entity.Brand;
import com.sieng.java.phoneshop_sieng.entity.Model;
import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.mapper.BrandMapper;
import com.sieng.java.phoneshop_sieng.mapper.ModelEntityMapper;
import com.sieng.java.phoneshop_sieng.mapper.ProductMapper;
import com.sieng.java.phoneshop_sieng.service.BrandService;
import com.sieng.java.phoneshop_sieng.service.ModelService;
import com.sieng.java.phoneshop_sieng.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	private final ProductMapper productMapper;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
		 Product product = productMapper.toProduct(productDTO);
	     product = productService.create(product);		
		
		return ResponseEntity.ok(product);
	}
	
	@PostMapping("importProduct")
	public ResponseEntity<?> importProduct (@RequestBody @Valid ProductImportDto importDto){
		productService.importProduct(importDto);		
		return ResponseEntity.ok().build();
		
	}
	
	@PostMapping("{productId}/setSalePrice")
	public ResponseEntity<?> setSalePrice (@PathVariable Long productId , @RequestBody PriceDto priceDto){
		productService.setSalePrice(productId, priceDto.getPrice());	
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping("uploadProduct")
	public ResponseEntity<?> uploadProduct(@RequestParam("file") MultipartFile file){
		Map<Integer, String> errorMap = productService.uploadProducts(file);
		return ResponseEntity.ok(errorMap);
	}
}
