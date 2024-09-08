package com.sieng.java.phoneshop_sieng.spec;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO {
	private int pageSize;
	private int pageNumber;
	private int totalPages;
	private long totalElememts;
	private long numberOfElements;
	private boolean first;
	private boolean last;
	private boolean empty; 

}
