package com.sieng.java.phoneshop_sieng.utils;

import java.util.List;

public class GeneralUtils {
	// convert list<string> to List<Integer>
	
	public static List<Integer> toInteger(List<String> list){
	  return list.stream()
		.map( s-> s.length())
		.toList();
	}
	
	// getevenNumbers
	
	public static List<Integer> evenNumber(List<Integer> numbers){
	return	numbers.stream()
		.filter(n->n%2==0)
		.toList();
		
	}

}
