package com.sieng.java.phoneshop_sieng.service;

import java.math.BigDecimal;

import com.sieng.java.phoneshop_sieng.dto.ProductImportDto;
import com.sieng.java.phoneshop_sieng.entity.Product;

public interface ProductService {
	Product create(Product product);
	Product getById(Long id);
	
	void importProduct(ProductImportDto productImportDto );
	void setSalePrice(Long productId , BigDecimal price);
	void validateStock(Long productId , Integer numberOfUnit);
}
