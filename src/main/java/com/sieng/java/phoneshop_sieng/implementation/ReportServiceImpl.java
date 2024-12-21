package com.sieng.java.phoneshop_sieng.implementation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.dto.report.ExpenseReportDTO;
import com.sieng.java.phoneshop_sieng.dto.report.ProductReportDTO;
import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.entity.ProductImportHistory;
import com.sieng.java.phoneshop_sieng.entity.SaleDetail;
import com.sieng.java.phoneshop_sieng.projection.ProductSold;
import com.sieng.java.phoneshop_sieng.repository.ProductImportHistoryRepository;
import com.sieng.java.phoneshop_sieng.repository.ProductRepository;
import com.sieng.java.phoneshop_sieng.repository.SaleDetailsRepository;
import com.sieng.java.phoneshop_sieng.repository.SaleRepository;
import com.sieng.java.phoneshop_sieng.service.ReportService;
import com.sieng.java.phoneshop_sieng.spec.ProductImportHistoryFilter;
import com.sieng.java.phoneshop_sieng.spec.ProductImportHistorySpec;
import com.sieng.java.phoneshop_sieng.spec.SaleDetailFilter;
import com.sieng.java.phoneshop_sieng.spec.SaleDetailSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	private final SaleRepository saleRepository;
	private final SaleDetailsRepository saleDetailsRepository;
	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository productImportHistoryRepository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return saleRepository.findProductSold(startDate, endDate);
	}

	@Override
	public List<ExpenseReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate) {
		
		ProductImportHistoryFilter importHistoryFilter = new ProductImportHistoryFilter();
		importHistoryFilter.setStartDate(startDate);
		importHistoryFilter.setEndDate(endDate);
		ProductImportHistorySpec spec = new ProductImportHistorySpec(importHistoryFilter);		
		List<ProductImportHistory> importHistories = productImportHistoryRepository.findAll(spec);
		
		Set<Long> productIds = importHistories.stream()
		.map(his -> his.getProduct().getId())
		.collect(Collectors.toSet());
		List<Product> products = productRepository.findAllById(productIds);
		
		Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(p -> p.getId(), p->p));
		
		Map<Product, List<ProductImportHistory>> importMap = importHistories.stream()
				.collect(Collectors.groupingBy(pi -> pi.getProduct()));			
				
		var expenseReportDTOs = new ArrayList<ExpenseReportDTO>();
		for(var entry : importMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			
			List<ProductImportHistory> hisList = entry.getValue();
			int totalUnit = hisList.stream().mapToInt(pih -> pih.getImportUnit()).sum();
			double totalAmount = hisList.stream()
			.mapToDouble(pih -> pih.getImportUnit() * pih.getPricePerUnit().doubleValue()).sum();
			var expenseReportDTO = new ExpenseReportDTO();
			expenseReportDTO.setProductId(product.getId());
			expenseReportDTO.setProductName(product.getName());
			expenseReportDTO.setTotalUnit(totalUnit);
			expenseReportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			
			expenseReportDTOs.add(expenseReportDTO);
		}
		return expenseReportDTOs;
	}

	@Override
	public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
		List<ProductReportDTO> list = new ArrayList<>();
		
		SaleDetailFilter detailFilter = new SaleDetailFilter();
		detailFilter.setStartDate(startDate);
		detailFilter.setEndDate(endDate);
		Specification<SaleDetail> spec = new SaleDetailSpec(detailFilter);
		List<SaleDetail> saleDetails = saleDetailsRepository.findAll(spec);
		
		List<Long> productIds = saleDetails.stream()
		.map(sd -> sd.getProduct().getId())
		.toList();
		
		Map<Long, Product> productMap = productRepository.findAllById(productIds)
		.stream()
		.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream()
				.collect(Collectors.groupingBy(SaleDetail::getProduct));
		
		for(var entry : saleDetailMap.entrySet()) {
			
			List<SaleDetail> sdList = entry.getValue();
			
			// total unit
			Integer totalUnit = sdList.stream()
			.map(SaleDetail::getUnit)
			.reduce(0, (a,b)->a+b);
			
			//total amount
			
			/*sdList.stream()
			.map(sd -> sd.getUnit() * sd.getAmount().doubleValue())
			.reduce(0.0,(a,b)->a+b);*/
			
			double totalAmount = sdList.stream().mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue())
			.sum();
			
			
			Product product = productMap.get(entry.getKey().getId());
			ProductReportDTO reportDTO = new ProductReportDTO();
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setUnit(totalUnit);
			reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			
			list.add(reportDTO);
			
			Collections.sort(list,(a,b) -> (int)( a.getProductId() - b.getProductId()));
		}
				
		return list;
	}

}
