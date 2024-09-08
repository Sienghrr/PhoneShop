package com.sieng.java.phoneshop_sieng.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sieng.java.phoneshop_sieng.spec.PaginationDTO;

import lombok.Data;

@Data
public class PageDTO {
	private List<?> lists;
	private PaginationDTO paginationDTO;
	
	public PageDTO(Page<?> page) {
		this.lists = page.getContent();
		this.paginationDTO = PaginationDTO.builder()
				.empty(page.isEmpty())
				.first(page.isFirst())
				.last(page.isLast())
				.pageSize(page.getPageable().getPageSize())
				.pageNumber(page.getPageable().getPageNumber())
				.totalElememts(page.getTotalElements())
				.totalPages(page.getTotalPages())
				.numberOfElements(page.getNumberOfElements())
				.build();
		
	}

}
