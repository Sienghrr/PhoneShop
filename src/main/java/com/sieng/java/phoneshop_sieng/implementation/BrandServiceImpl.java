package com.sieng.java.phoneshop_sieng.implementation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sieng.java.phoneshop_sieng.Exception.ResourceNotFoundException;
import com.sieng.java.phoneshop_sieng.entity.Brand;
import com.sieng.java.phoneshop_sieng.repository.BrandRepository;
import com.sieng.java.phoneshop_sieng.service.BrandService;
import com.sieng.java.phoneshop_sieng.spec.BrandSpec;
import com.sieng.java.phoneshop_sieng.spec.Brandfilter;
import com.sieng.java.phoneshop_sieng.util.PageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	private final BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
		
		return brandRepository.save(brand);
	}

	@Override
	public Brand getbyId(Integer id) {
		
		return brandRepository.findById(id)
				//.orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format( "Brand with id = %d not found", id)));
				.orElseThrow(()->new ResourceNotFoundException("brand", id));
		
		
	}

	@Override
	public Brand update(Integer id, Brand brandUpdate) {
		Brand brand = getbyId(id);
		brand.setName(brandUpdate.getName());
		return brandRepository.save(brand);
	}

	
	@Override
	public List<Brand> getBrands(String name) {
		return brandRepository.findByNameContaining(name);
	}

	/*@Override
	public List<Brand> getBrands(Map<String, String> params) {
		Brandfilter brandfilter = new Brandfilter();
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandfilter.setName(name);
			
		}
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandfilter.setId(Integer.parseInt(id));
			
		}
		BrandSpec brandSpec = new BrandSpec(brandfilter);
		
		//Pageable pageable = PageUtil.getPageable(0, 0);
		
		return brandRepository.findAll(brandSpec);
	}*/
	
	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		Brandfilter brandfilter = new Brandfilter();
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandfilter.setName(name);
			
		}
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandfilter.setId(Integer.parseInt(id));
			
		}
		//to-do function pageable
		int pageLimit = PageUtil.Defualt_Page_Limit;
		if(params.containsKey(PageUtil.Page_limit)) {
			pageLimit= Integer.parseInt(params.get(PageUtil.Page_limit));
			
		}
		int pageNumer = PageUtil.Defualt_Page_Number;
		if(params.containsKey(PageUtil.Page_Number)) {
			pageNumer= Integer.parseInt(params.get(PageUtil.Page_Number));
			
		}
		BrandSpec brandSpec = new BrandSpec(brandfilter);
		
		Pageable pageable = PageUtil.getPageable(pageNumer, pageLimit);
		
		return brandRepository.findAll(brandSpec,pageable);
	}

}
