package com.sieng.java.phoneshop_sieng.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
@Data
public class SaleDto {

@NotEmpty
 private List<ProductSaleDto> products;
 private LocalDate saleDate;
}
