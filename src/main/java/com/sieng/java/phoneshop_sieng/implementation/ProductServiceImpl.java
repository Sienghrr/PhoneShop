package com.sieng.java.phoneshop_sieng.implementation;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.Exception.ApiException;
import com.sieng.java.phoneshop_sieng.Exception.ResourceNotFoundException;
import com.sieng.java.phoneshop_sieng.dto.ProductImportDto;
import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.entity.ProductImportHistory;
import com.sieng.java.phoneshop_sieng.mapper.ProductMapper;
import com.sieng.java.phoneshop_sieng.repository.ProductImportHistoryRepository;
import com.sieng.java.phoneshop_sieng.repository.ProductRepository;
import com.sieng.java.phoneshop_sieng.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository productImportHistoryRepository;
	private final ProductMapper productMapper;

	@Override
	public Product create(Product product) {	
		
		String product_name = "%s %s".formatted(product.getModel().getName(),product.getColor().getName());
		product.setName(product_name);
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", id));
	}

	@Override
	public void importProduct(ProductImportDto productImportDto) {
		
		
		
		//update available product unit
		Product product = this.getById(productImportDto.getProductId());
		Integer availableUnit = 0;
		if(product.getAvailableUnit() != null) {
			availableUnit = product.getAvailableUnit();
		}
		product.setAvailableUnit(availableUnit+productImportDto.getImportUnit());
		
		// save product import history
		ProductImportHistory productImportHistory = productMapper.toProductImportHistory(productImportDto, product);
		productImportHistoryRepository.save(productImportHistory);
		
	}

	@Override
	public void setSalePrice(Long productId, BigDecimal price) {
		Product product = getById(productId);
		product.setSalePrice(price);
		productRepository.save(product);
	}

	@Override
	public void validateStock(Long productId, Integer numberOfUnit) {
		// TODO Auto-generated method stub
		
	}

}
