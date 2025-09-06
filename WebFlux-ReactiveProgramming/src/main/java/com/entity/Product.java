package com.entity;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

import lombok.Data;


@Data
@Table("product")
public class Product {

	@Id
	private int id;
	
	private String name;
	
	private Double price;
}
