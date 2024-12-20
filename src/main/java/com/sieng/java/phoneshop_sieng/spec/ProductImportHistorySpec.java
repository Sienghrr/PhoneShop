package com.sieng.java.phoneshop_sieng.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sieng.java.phoneshop_sieng.entity.ProductImportHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory>{
	
	private ProductImportHistoryFilter importHistoryFilter ;
	
	@Override
	public Predicate toPredicate(Root<ProductImportHistory> productImportHistory, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if(Objects.nonNull(importHistoryFilter.getStartDate())) {
			cb.greaterThanOrEqualTo(productImportHistory.get("dateImport"), importHistoryFilter.getStartDate());
		}
		
		if(Objects.nonNull(importHistoryFilter.getEndDate())) {
			cb.lessThanOrEqualTo(productImportHistory.get("dateImport"), importHistoryFilter.getEndDate());
		}		
		
		
		return cb.and(predicates.toArray(Predicate[]::new));
	}

}
