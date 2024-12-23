package com.sieng.java.phoneshop_sieng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sieng.java.phoneshop_sieng.entity.SaleDetail;

@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetail, Long>,JpaSpecificationExecutor<SaleDetail>{
	List<SaleDetail> findBySaleId(Long saleId);
	
}
