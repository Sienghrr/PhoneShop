package com.sieng.java.phoneshop_sieng.implementation;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sieng.java.phoneshop_sieng.Exception.ApiException;
import com.sieng.java.phoneshop_sieng.Exception.ResourceNotFoundException;
import com.sieng.java.phoneshop_sieng.dto.ProductImportDto;
import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.entity.ProductImportHistory;
import com.sieng.java.phoneshop_sieng.mapper.ProductMapper;
import com.sieng.java.phoneshop_sieng.repository.ProductImportHistoryRepository;
import com.sieng.java.phoneshop_sieng.repository.ProductRepository;
import com.sieng.java.phoneshop_sieng.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository productImportHistoryRepository;
	private final ProductMapper productMapper;

	@Override
	public Product create(Product product) {	
		
		String product_name = "%s %s".formatted(product.getModel().getName(),product.getColor().getName());
		product.setName(product_name);
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", id));
	}

	@Override
	public void importProduct(ProductImportDto productImportDto) {
		
		
		
		//update available product unit
		Product product = this.getById(productImportDto.getProductId());
		Integer availableUnit = 0;
		if(product.getAvailableUnit() != null) {
			availableUnit = product.getAvailableUnit();
		}
		product.setAvailableUnit(availableUnit+productImportDto.getImportUnit());
		
		// save product import history
		ProductImportHistory productImportHistory = productMapper.toProductImportHistory(productImportDto, product);
		productImportHistoryRepository.save(productImportHistory);
		
	}

	@Override
	public void setSalePrice(Long productId, BigDecimal price) {
		Product product = getById(productId);
		product.setSalePrice(price);
		productRepository.save(product);
	}

	@Override
	public void validateStock(Long productId, Integer numberOfUnit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, String> uploadProducts(MultipartFile file) {
		Map<Integer, String> map = new HashMap<>();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheet("products");
			Iterator<Row> rowIterator = sheet.iterator();
			
			rowIterator.next();//@todo checking errors
			while(rowIterator.hasNext()) {				
				Integer rowNumber =0;
				try {
					
					Row row = rowIterator.next();
					
					int cellIndex = 0;
					
					Cell cellNo = row.getCell(cellIndex++);
				    rowNumber = (int) cellNo.getNumericCellValue();
					
					Cell cellModelId = row.getCell(cellIndex++);
					Long modelId = (long) cellModelId.getNumericCellValue();
					
					Cell cellColorId = row.getCell(cellIndex++);
					Long colorId = (long) cellColorId.getNumericCellValue();
					
					Cell cellimportPrice = row.getCell(cellIndex++);
					double importPrice = cellimportPrice.getNumericCellValue();
					
					Cell cellimportUnit = row.getCell(cellIndex++);
					Integer importUnit = (int) cellimportUnit.getNumericCellValue();
					if(importUnit <1 ) {
						throw new ApiException(HttpStatus.BAD_REQUEST, "unit must be greater than 0");
					}
					
					Cell cellimportDate = row.getCell(cellIndex++);
					LocalDateTime importDate =  cellimportDate.getLocalDateTimeCellValue();
					
					Product product = getByModelIdAndColorId(modelId, colorId);
					
					//System.out.println(modelId);
					Integer availableUnit = 0;
					if(product.getAvailableUnit() != null) {
						availableUnit = product.getAvailableUnit();
					}
					product.setAvailableUnit(availableUnit+importUnit);
					
					// save product import history
					ProductImportHistory productImportHistory = new ProductImportHistory();
					productImportHistory.setDateImport(importDate);
					productImportHistory.setImportUnit(importUnit);
					productImportHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
					productImportHistory.setProduct(product);
					productImportHistoryRepository.save(productImportHistory);
					
				}catch (Exception e) {
				map.put(rowNumber, e.getMessage());
			}
			
				
		}} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	
		}
	

	@Override
	public Product getByModelIdAndColorId(Long modelId, Long colorId) {
		String text = "product with modelId with %s and colorId with %d not found";
		return productRepository.findByModelIdAndColorId(modelId, colorId)
				.orElseThrow(()-> new ApiException(HttpStatus.BAD_REQUEST,text.formatted(modelId,colorId)));
	}

	

}
