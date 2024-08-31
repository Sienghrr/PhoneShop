package com.sieng.java.phoneshop_sieng.implementation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.Exception.ApiException;
import com.sieng.java.phoneshop_sieng.Exception.ResourceNotFoundException;
import com.sieng.java.phoneshop_sieng.dto.ProductSaleDto;
import com.sieng.java.phoneshop_sieng.dto.SaleDto;
import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.entity.Sale;
import com.sieng.java.phoneshop_sieng.entity.SaleDetail;
import com.sieng.java.phoneshop_sieng.repository.ProductRepository;
import com.sieng.java.phoneshop_sieng.repository.SaleDetailsRepository;
import com.sieng.java.phoneshop_sieng.repository.SaleRepository;
import com.sieng.java.phoneshop_sieng.service.ProductService;
import com.sieng.java.phoneshop_sieng.service.SaleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {
	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final SaleDetailsRepository saleDetailsRepository;

	@Override
	public void sell(SaleDto saleDto) {
		// validation
		//validate1(saleDto);
		List<Long> productIds = saleDto.getProducts().stream()
				 .map(ProductSaleDto::getProductId)
				 .toList();
				//validate product 
				productIds		 
				 //.forEach(productId -> productService.getById(productId));
				 .forEach(productService::getById);
				List<Product> products = productRepository.findAllById(productIds);
				Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
				
				
				//validate stock
				
				saleDto.getProducts().stream()
				.forEach(ps ->{
					Product product = productMap.get(ps.getProductId());
					if(product.getAvailableUnit()<ps.getNumberOfUnit()) {
						throw new ApiException(HttpStatus.BAD_REQUEST, "product[%s] is not enough in stock"
								.formatted(product.getName()));
					}
				});
				
	//sale
	Sale sale = new Sale();
	sale.setSoldDate(saleDto.getSaleDate());
	saleRepository.save(sale);

	// saledetails
	saleDto.getProducts().forEach(ps->{
		Product product = productMap.get(ps.getProductId());
		SaleDetail saleDetail = new SaleDetail();
		saleDetail.setAmount(product.getSalePrice());
		saleDetail.setSale(sale);
		saleDetail.setProduct(product);
		saleDetail.setUnit(ps.getNumberOfUnit());
		saleDetailsRepository.save(saleDetail);
		
		
		//cut stock
		Integer availableUnit = product.getAvailableUnit() - ps.getNumberOfUnit();
		product.setAvailableUnit(availableUnit);
		productRepository.save(product);
		
	});
	
		 
		
		
	}
	
	private void validate1(SaleDto saleDto) {
		saleDto.getProducts().forEach(ps -> {
			 Product product = productService.getById(ps.getProductId()) ;
			 if(product.getAvailableUnit()<ps.getNumberOfUnit()) {
					throw new ApiException(HttpStatus.BAD_REQUEST, "product[%s] is not enough in stock"
							.formatted(product.getName()));
				}
			});
			
		
		
	}

	@Override
	public void cancelSale(Long saleId) {
		// update sale status
			Sale sale = getById(saleId);
			sale.setActive(false);
			saleRepository.save(sale);
		// update stock
			List<SaleDetail> saleDetails = saleDetailsRepository.findBySaleId(saleId);
			
			List<Long> productIds = saleDetails.stream()
			.map(sd -> sd.getId())
		    .toList();
			
			List<Product> products = productRepository.findAllById(productIds);
			Map<Long, Product> productMap = products.stream()
					.collect(Collectors.toMap(Product::getId, Function.identity()));
			
			saleDetails.forEach(
					sd ->{
						Product product = productMap.get(sd.getProduct().getId());
						product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
						productRepository.save(product);
					}
					);
			
			
			}

	@Override
	public Sale getById(Long saleId) {
		return saleRepository.findById(saleId)
				.orElseThrow(()-> new ResourceNotFoundException("Sale", saleId));
	}

/*	private void validate(SaleDto saleDto) {
		
		
		List<Long> productIds = saleDto.getProducts().stream()
		 .map(ProductSaleDto::getProductId)
		 .toList();
		//validate product 
		productIds		 
		 //.forEach(productId -> productService.getById(productId));
		 .forEach(productService::getById);
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
		
		
		//validate stock
		
		saleDto.getProducts().stream()
		.forEach(ps ->{
			Product product = productMap.get(ps.getProductId());
			if(product.getAvailableUnit()<ps.getNumberOfUnit()) {
				throw new ApiException(HttpStatus.BAD_REQUEST, "product[%s] is not enough in stock"
						.formatted(product.getName()));
			}
		});
		
	}*/
}
