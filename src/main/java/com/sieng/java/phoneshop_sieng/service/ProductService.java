package com.sieng.java.phoneshop_sieng.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sieng.java.phoneshop_sieng.dto.ProductImportDto;
import com.sieng.java.phoneshop_sieng.entity.Product;

public interface ProductService {
	Product create(Product product);
	Product getById(Long id);
	Product getByModelIdAndColorId(Long modelId , Long colorId);
	void importProduct(ProductImportDto productImportDto );
	void setSalePrice(Long productId , BigDecimal price);
	void validateStock(Long productId , Integer numberOfUnit);
	Map<Integer, String> uploadProducts(MultipartFile file);
}
