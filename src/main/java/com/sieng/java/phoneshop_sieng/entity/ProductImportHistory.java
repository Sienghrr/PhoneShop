package com.sieng.java.phoneshop_sieng.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ProductImportHistories")
public class ProductImportHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "import_id" )
	private Long id;
	
	@Column(name = "import_unit")
	private Integer importUnit;
	
	@Column(name = "price_per_unit")
	private BigDecimal PricePerUnit;	
	
	@Column(name = "date_import")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateImport;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

}
