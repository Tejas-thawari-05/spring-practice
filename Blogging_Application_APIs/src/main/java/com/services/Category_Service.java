package com.services;

import java.util.List;

import com.payloads.CategoryDto;

public interface Category_Service {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategoryDto(CategoryDto categoryDto, int categoryId);
	
	void deleteCategory(int categoryId);
	
	CategoryDto getCategoryDtoById(int categoryId);
	
	List<CategoryDto> getAllCategory();
}
