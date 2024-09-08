package com.sieng.java.phoneshop_sieng.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductImportDto {
	@NotNull(message = "id cannot be null")
	private Long productId;
	
	@Min(value = 1 ,message = "it must greater than 0")
	private Integer importUnit;
	
	@DecimalMin(value = "000.1" )
	private BigDecimal importPrice;
	
	@NotNull(message = "date cannot be null")
	private LocalDateTime importDate;

}
