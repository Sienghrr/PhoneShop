package com.sieng.java.phoneshop_sieng.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.entity.ProductImportHistory;

public class ReportServiceHelper {
	
	private static Product product1 = Product.builder()
			.id(1l)
			.name("iphone")
			.build();
	private static Product product2 = Product.builder()
			.id(2l)
			.name("iphone10")
			.build();
	private static Product product3 = Product.builder()
			.id(3l)
			.name("iphone11")
			.build();
	public static List<Product> getProducts(){return List.of(product1,product2);}
	
	
	
	public static List<ProductImportHistory> getProductImportHistories (){
		
		ProductImportHistory his1 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(10)
				.PricePerUnit(BigDecimal.valueOf(12000))
				.dateImport(LocalDateTime.of(2022, 05, 20, 6, 30))				
				.build();
		ProductImportHistory his2 = ProductImportHistory.builder()
				.product(product2)
				.importUnit(20)
				.PricePerUnit(BigDecimal.valueOf(13000))
				.dateImport(LocalDateTime.of(2022, 05, 20, 6, 30))				
				.build();
		ProductImportHistory his3 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(15)
				.PricePerUnit(BigDecimal.valueOf(12000))
				.dateImport(LocalDateTime.of(2022, 05, 20, 6, 30))				
				.build();
		
		
		return List.of(his1,his2,his3);
	}

}
