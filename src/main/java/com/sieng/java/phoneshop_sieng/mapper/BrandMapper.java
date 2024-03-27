package com.sieng.java.phoneshop_sieng.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sieng.java.phoneshop_sieng.dto.BrandDTO;
import com.sieng.java.phoneshop_sieng.entity.Brand;

@Mapper
public interface BrandMapper {
	BrandMapper Instance = Mappers.getMapper(BrandMapper.class);
	
	Brand toBrand(BrandDTO dto);
	BrandDTO toBrandDTO(Brand brand);

}
