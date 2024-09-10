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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
	
	@Captor
	private ArgumentCaptor<Brand> brandCaptor;
	
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
		brand.setId(1l);
		brand.setName("sieng");
		
		//when
		when(brandRepository.findById(1l)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getbyId(1l);
		
		//then
		assertEquals(1, brandReturn.getId());
		assertEquals("sieng", brandReturn.getName());
	}
	
	@Test
	public void getByidthrow() {
		
		//given
		
		
		//when
		when(brandRepository.findById(2l)).thenReturn(Optional.empty());
		assertThatThrownBy(()->brandService.getbyId(2l))
		.isInstanceOf(ResourceNotFoundException.class)
		.hasMessage("brand with id  = 2 not found");
		//.hasMessage(String.format("%s with id  = %d not found",brand,id);
		//.hasMessageEndingWith("not found");
		
		//then
	}
		@Test
		public void updateTest() {
			
			//given 
			Brand brandInDb = new Brand(1L,"apple");
			Brand brand = new Brand(1L,"apple 2");
			
			
			// when
			when(brandRepository.findById(1L)).thenReturn(Optional.ofNullable(brandInDb));
			when(brandRepository.save(any(Brand.class))).thenReturn(brand);
			Brand brandToUpdate = brandService.update(1L, brand);
			
			
			//then
			
			verify(brandRepository, times(1)).findById(1L);
			//assertEquals("apple 2", brandToUpdate.getName());
			verify(brandRepository).save(brandCaptor.capture());
			assertEquals("apple 2",  brandCaptor.getValue().getName());
			assertEquals(1L, brandCaptor.getValue().getId());
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
