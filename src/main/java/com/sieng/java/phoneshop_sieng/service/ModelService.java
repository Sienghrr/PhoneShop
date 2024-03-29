package com.sieng.java.phoneshop_sieng.service;

import java.util.List;

import com.sieng.java.phoneshop_sieng.entity.Model;

public interface ModelService {
	Model create(Model model);
	List<Model> getByBrand(Integer brandId);
}
