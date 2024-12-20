package com.sieng.java.phoneshop_sieng.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sieng.java.phoneshop_sieng.dto.BrandDTO;
import com.sieng.java.phoneshop_sieng.dto.ModelDTO;
import com.sieng.java.phoneshop_sieng.dto.PageDTO;
import com.sieng.java.phoneshop_sieng.dto.PriceDto;
import com.sieng.java.phoneshop_sieng.dto.ProductDTO;
import com.sieng.java.phoneshop_sieng.dto.ProductImportDto;
import com.sieng.java.phoneshop_sieng.dto.SaleDto;
import com.sieng.java.phoneshop_sieng.dto.report.ExpenseReportDTO;
import com.sieng.java.phoneshop_sieng.dto.report.ProductReportDTO;
import com.sieng.java.phoneshop_sieng.entity.Brand;
import com.sieng.java.phoneshop_sieng.entity.Model;
import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.mapper.BrandMapper;
import com.sieng.java.phoneshop_sieng.mapper.ModelEntityMapper;
import com.sieng.java.phoneshop_sieng.mapper.ProductMapper;
import com.sieng.java.phoneshop_sieng.projection.ProductSold;
import com.sieng.java.phoneshop_sieng.service.BrandService;
import com.sieng.java.phoneshop_sieng.service.ModelService;
import com.sieng.java.phoneshop_sieng.service.ProductService;
import com.sieng.java.phoneshop_sieng.service.ReportService;
import com.sieng.java.phoneshop_sieng.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("sales")
@RequiredArgsConstructor
public class ReportController {
	
	private final ReportService reportService;
	
	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?> productSold(@DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable("startDate")LocalDate startDate ,
			@DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable("endDate") LocalDate endDate){
		List<ProductSold> productSolds = reportService.getProductSold(startDate, endDate);
		return ResponseEntity.ok(productSolds);
	}
	
	@GetMapping("v2/{startDate}/{endDate}")
	public ResponseEntity<?> productSoldV2(@DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable("startDate")LocalDate startDate ,
			@DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable("endDate") LocalDate endDate){
		List<ProductReportDTO> productSolds = reportService.getProductReport(startDate, endDate);
		return ResponseEntity.ok(productSolds);
	}
	
	@GetMapping("expense/{startDate}/{endDate}")
	public ResponseEntity<?> expenseProduct(@DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable("startDate")LocalDate startDate ,
			@DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable("endDate") LocalDate endDate){
		List<ExpenseReportDTO> expenseReport = reportService.getExpenseReport(startDate, endDate);
		return ResponseEntity.ok(expenseReport);
	}
	
	
}
