package com.sieng.java.phoneshop_sieng.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sieng.java.phoneshop_sieng.entity.Brand;

@DataJpaTest
public class BrandRepositoryTest {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Test
	public void testFindbyNameLike() {
		
		
		//given
		Brand brand = new Brand();
		brand.setName("Techno");
		
		Brand brand2 = new Brand();
		brand2.setName("Salt");// if we use capital T it will error because we expect 1 but result 2
		
		brandRepository.save(brand);
		brandRepository.save(brand2);
		
		//when
		
		List<Brand> brands = brandRepository.findByNameLike("%T%");
		
		//then 
		
		assertEquals(1, brands.size());
		assertEquals("Techno", brands.get(0).getName());
		assertEquals(1, brands.get(0).getId());
		
	}

}
