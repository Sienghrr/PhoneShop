package com.sieng.java.phoneshop_sieng.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sieng.java.phoneshop_sieng.entity.Brand;

import lombok.Data;

@Data
public class BrandSpec implements Specification<Brand> {
	
	private final Brandfilter brandfilter;
	List<Predicate> predicates = new ArrayList<>();

	@Override
	public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(brandfilter.getName() != null) {
			//Predicate name = brand.get("name").in(brandfilter.getName());
			Predicate name = cb.like(cb.upper(brand.get("name")),"%"+ brandfilter.getName().toUpperCase()+"%");
			predicates.add(name);			
			
		}
		if(brandfilter.getId() != null) {
			
			Predicate id = brand.get("id").in(brandfilter.getId());
			predicates.add(id);			
			
		}		
		
		//return cb.and(predicates.toArray(new Predicate[0])); or use this
		return cb.and(predicates.toArray(Predicate[]::new));
	}

}
