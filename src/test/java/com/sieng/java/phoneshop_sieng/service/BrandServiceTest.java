package com.sieng.java.phoneshop_sieng.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sieng.java.phoneshop_sieng.Exception.ResourceNotFoundException;
import com.sieng.java.phoneshop_sieng.entity.Brand;
import com.sieng.java.phoneshop_sieng.implementation.BrandServiceImpl;
import com.sieng.java.phoneshop_sieng.repository.BrandRepository;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
	
	
	@Mock
	private BrandRepository brandRepository;
	
	
	private BrandService brandService;
	
	@BeforeEach
	public void setUp() {
		
		brandService = new BrandServiceImpl(brandRepository);
	}
	
	/*
	@Test
	public void createTest() {
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1);
		
		
		//when 
		when(brandRepository.save(any(Brand.class))).thenReturn(brand);
		Brand brandReturn = brandService.create(new Brand());		
		
		
		//then
		 assertEquals(1, brandReturn.getId());
		 assertEquals("Apple", brandReturn.getName());
	} */
	
	
	//or use this instead
	
	@Test
	public void createTest() {
		
		//given
		Brand brand = new Brand();
		brand.setName("apple");
		
		//when
		brandService.create(brand);
				
		//then
		verify(brandRepository , times(1)).save(brand);
	}
	
	
	@Test
	public void getByidTestSuccess() {
		//given 
		Brand brand = new Brand();
		brand.setId(1);
		brand.setName("sieng");
		
		//when
		when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getbyId(1);
		
		//then
		assertEquals(1, brandReturn.getId());
		assertEquals("sieng", brandReturn.getName());
	}
	
	@Test
	public void getByidthrow() {
		
		//given
		
		
		//when
		when(brandRepository.findById(2)).thenReturn(Optional.empty());
		assertThatThrownBy(()->brandService.getbyId(2))
		.isInstanceOf(ResourceNotFoundException.class)
		.hasMessage("brand with id  = 2 not found");
		//.hasMessage(String.format("%s with id  = %d not found",brand,id);
		//.hasMessageEndingWith("not found");
		
		//then
	}

}
