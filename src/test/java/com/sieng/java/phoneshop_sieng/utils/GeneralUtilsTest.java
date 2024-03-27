package com.sieng.java.phoneshop_sieng.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;


public class GeneralUtilsTest {
	
	@Test
	public void testTointeger() {
	//given
	List<String> name = List.of("sieng","Ly pu","kaka");
	
	
	//when
	List<Integer> list = GeneralUtils.toInteger(name);
	
	
	//then
	//[5,5,4]
	
	assertEquals(3, list.size());
	assertEquals(5, list.get(0));
	assertEquals(5, list.get(1));
	assertEquals(4, list.get(2));
	
	}
	
	@Test
	public void testEvenNumber() {
		
		//given
		List<Integer> numbers = List.of(1,2,3,5,6,22);
		
		//when
		List<Integer> list = GeneralUtils.evenNumber(numbers);
		
		//then
		 assertEquals(3, list.size());
		 assertEquals(2, list.get(0));
		
		
	}
	

}
