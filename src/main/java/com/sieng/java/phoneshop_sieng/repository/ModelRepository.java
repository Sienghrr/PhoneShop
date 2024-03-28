package com.sieng.java.phoneshop_sieng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sieng.java.phoneshop_sieng.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>{
	

}
