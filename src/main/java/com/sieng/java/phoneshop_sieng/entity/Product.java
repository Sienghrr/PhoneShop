package com.sieng.java.phoneshop_sieng.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name="products" , uniqueConstraints= {@UniqueConstraint(columnNames = {"model_id","color_id"})})
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id" )
	private Long id;
	
	@Column(name = "product_name", unique = true)
	private String name;
	
	@Column(name = "available_unit")
	private Integer availableUnit;
	
	@DecimalMin(value = "000.1", message = "prie must be greater than 0")
	@Column(name = "sale_price")
	private BigDecimal salePrice;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	

	
	

}
