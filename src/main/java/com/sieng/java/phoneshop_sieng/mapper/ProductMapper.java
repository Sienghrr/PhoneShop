package com.sieng.java.phoneshop_sieng.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sieng.java.phoneshop_sieng.dto.ProductDTO;
import com.sieng.java.phoneshop_sieng.dto.ProductImportDto;
import com.sieng.java.phoneshop_sieng.entity.Product;
import com.sieng.java.phoneshop_sieng.entity.ProductImportHistory;
import com.sieng.java.phoneshop_sieng.service.ColorService;
import com.sieng.java.phoneshop_sieng.service.ModelService;

@Mapper(componentModel = "spring" , uses = {ModelService.class,ColorService.class})
public interface ProductMapper {
	
	
	@Mapping(target = "model" , source  = "modelID")
	@Mapping(target = "color" , source  = "colorID")
	Product toProduct(ProductDTO productDto);
	
	@Mapping(target = "dateImport" , source = "importDto.importDate")
	@Mapping(target = "pricePerUnit" , source = "importDto.importPrice")
	@Mapping(target = "product" , source = "product")
	@Mapping(target = "id" , ignore = true)
	ProductImportHistory toProductImportHistory (ProductImportDto importDto  , Product product);
}
