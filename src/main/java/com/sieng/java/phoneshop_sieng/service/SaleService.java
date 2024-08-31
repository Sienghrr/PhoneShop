package com.sieng.java.phoneshop_sieng.service;

import com.sieng.java.phoneshop_sieng.dto.SaleDto;
import com.sieng.java.phoneshop_sieng.entity.Sale;

public interface SaleService {
 void sell(SaleDto saleDto);
 void cancelSale(Long saleId);
 Sale getById(Long saleId);
}
