package com.sieng.java.phoneshop_sieng.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sieng.java.phoneshop_sieng.dto.report.ExpenseReportDTO;
import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.entity.ProductImportHistory;
import com.sieng.java.phoneshop_sieng.implementation.ReportServiceImpl;
import com.sieng.java.phoneshop_sieng.repository.ProductImportHistoryRepository;
import com.sieng.java.phoneshop_sieng.repository.ProductRepository;
import com.sieng.java.phoneshop_sieng.repository.SaleDetailsRepository;
import com.sieng.java.phoneshop_sieng.repository.SaleRepository;
import com.sieng.java.phoneshop_sieng.spec.ProductImportHistorySpec;
import com.sieng.java.phoneshop_sieng.utils.ReportServiceHelper;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	@Mock
	private  SaleRepository saleRepository;
	@Mock
	private  SaleDetailsRepository saleDetailsRepository;
	@Mock
	private  ProductRepository productRepository;
	@Mock
	private  ProductImportHistoryRepository productImportHistoryRepository;
	
	private ReportService reportService;
	
	@BeforeEach
	public void setUp() {
		reportService = new ReportServiceImpl(saleRepository, saleDetailsRepository, productRepository, productImportHistoryRepository);
	}
	
	
	@Test
	public void testGetExpenseReport() {
		
		//given
		
		List<ProductImportHistory> importHistories = ReportServiceHelper.getProductImportHistories();
		List<Product> products = ReportServiceHelper.getProducts();
		
		//when
		when(productImportHistoryRepository.findAll(Mockito.any(ProductImportHistorySpec.class)))
		.thenReturn(importHistories);
		
		when(productRepository.findAllById(anySet())).thenReturn(products);
		
		List<ExpenseReportDTO> reportExpense = reportService.getExpenseReport(LocalDate.now().minusMonths(1), LocalDate.now());
		
		//then
		
		assertEquals(2,reportExpense.size());
		ExpenseReportDTO expense1 = reportExpense.get(0);
		assertEquals(1, expense1.getProductId());
		assertEquals("iphone", expense1.getProductName());
		assertEquals(25, expense1.getTotalUnit());
		assertEquals(300000d, expense1.getTotalAmount().doubleValue());
		
		
	}

}
