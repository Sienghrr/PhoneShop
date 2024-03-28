package com.sieng.java.phoneshop_sieng.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sieng.java.phoneshop_sieng.dto.ModelDTO;
import com.sieng.java.phoneshop_sieng.entity.Brand;
import com.sieng.java.phoneshop_sieng.entity.Model;
import com.sieng.java.phoneshop_sieng.service.BrandService;

@Mapper(componentModel = "spring",uses = {BrandService.class})
public interface ModelMapper {
	
	ModelMapper Instance = Mappers.getMapper(ModelMapper.class);
	
	@Mapping(target = "brand" , source = "brandId")
	Model toModel(ModelDTO dto );
	
	/*default Brand toBrand(Integer brId) {
		Brand brand = new Brand();
		brand.setId(brId);		
		return brand;
	}*/
	@Mapping(target = "brandId", source = "brand.id")
	ModelDTO toModelDTO(Model model);
}
