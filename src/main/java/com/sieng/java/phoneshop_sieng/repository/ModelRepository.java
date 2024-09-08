package com.sieng.java.phoneshop_sieng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sieng.java.phoneshop_sieng.entity.Brand;
import com.sieng.java.phoneshop_sieng.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{
	List<Model> findByBrandId(Long brandId);

}
