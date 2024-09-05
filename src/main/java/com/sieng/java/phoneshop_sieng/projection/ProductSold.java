package com.sieng.java.phoneshop_sieng.projection;

import java.math.BigDecimal;

public interface ProductSold {
Long getProductId();
String getProductName();
Integer getUnit();
BigDecimal getTotalAmount();
}
