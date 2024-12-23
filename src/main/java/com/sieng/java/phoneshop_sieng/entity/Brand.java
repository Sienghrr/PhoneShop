package com.sieng.java.phoneshop_sieng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="brands")
public class Brand extends AuditEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase id via id in database
	@Column(name = "brand_id" )
	private Long id;
	
	@Column(name = "brand_name")
	private String name;

}