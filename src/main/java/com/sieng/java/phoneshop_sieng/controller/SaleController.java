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

import com.sieng.java.phoneshop_sieng.dto.BrandDTO;
import com.sieng.java.phoneshop_sieng.dto.ModelDTO;
import com.sieng.java.phoneshop_sieng.dto.PageDTO;
import com.sieng.java.phoneshop_sieng.dto.PriceDto;
import com.sieng.java.phoneshop_sieng.dto.ProductDTO;
import com.sieng.java.phoneshop_sieng.dto.ProductImportDto;
import com.sieng.java.phoneshop_sieng.dto.SaleDto;
import com.sieng.java.phoneshop_sieng.entity.Brand;
import com.sieng.java.phoneshop_sieng.entity.Model;
import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.mapper.BrandMapper;
import com.sieng.java.phoneshop_sieng.mapper.ModelEntityMapper;
import com.sieng.java.phoneshop_sieng.mapper.ProductMapper;
import com.sieng.java.phoneshop_sieng.service.BrandService;
import com.sieng.java.phoneshop_sieng.service.ModelService;
import com.sieng.java.phoneshop_sieng.service.ProductService;
import com.sieng.java.phoneshop_sieng.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("sales")
@RequiredArgsConstructor
public class SaleController {
	
	private final SaleService saleService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody SaleDto saleDto){
		saleService.sell(saleDto);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{saleId}/cancel")
	public ResponseEntity<?> cancelSale(@PathVariable Long saleId){
		saleService.cancelSale(saleId);
		return ResponseEntity.ok().build();
		}
	
}
