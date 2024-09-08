package com.sieng.java.phoneshop_sieng.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {
 int Defualt_Page_Number=1;
 int Defualt_Page_Limit =10;
 String Page_limit ="_limit";
 String Page_Number = "_page";
 
 static Pageable getPageable (int pageNumber, int pageSize) {
	 if(pageNumber<Defualt_Page_Number) {
		 pageNumber= Defualt_Page_Limit;
	 }
	 if(pageSize <1) {
		 pageSize =Defualt_Page_Limit;
		 
	 }
	 Pageable pageable = PageRequest.of(pageNumber  , pageSize);
	 
	 return pageable;
 }
}
