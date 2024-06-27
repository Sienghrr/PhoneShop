package com.sieng.java.phoneshop_sieng.implementation;

import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.repository.ProductRepository;
import com.sieng.java.phoneshop_sieng.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;

	@Override
	public Product create(Product product) {	
		
		String product_name = "%s %s".formatted(product.getModel().getName(),product.getColor().getName());
		product.setName(product_name);
		return productRepository.save(product);
	}

}
