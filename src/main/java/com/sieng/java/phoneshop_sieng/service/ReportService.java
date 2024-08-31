package com.sieng.java.phoneshop_sieng.service;

import java.time.LocalDate;
import java.util.List;

import com.sieng.java.phoneshop_sieng.projection.ProductSold;

public interface ReportService {
List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
}
