package com.sieng.java.phoneshop_sieng.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.projection.ProductSold;
import com.sieng.java.phoneshop_sieng.repository.SaleRepository;
import com.sieng.java.phoneshop_sieng.service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	private final SaleRepository saleRepository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return saleRepository.findProductSold(startDate, endDate);
	}

}
