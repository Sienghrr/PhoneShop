package com.sieng.java.phoneshop_sieng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sieng.java.phoneshop_sieng.entity.ProductImportHistory;

public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long>,JpaSpecificationExecutor<ProductImportHistory>{

}
