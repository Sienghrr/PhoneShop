package com.sieng.java.phoneshop_sieng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sieng.java.phoneshop_sieng.entity.SaleDetail;

@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetail, Long>{

}
