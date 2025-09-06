package com.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	@NotBlank
	@Size(min = 4, message = "minimum size of category title is 4")
	private String categoryTitle;
	@NotBlank
	@Size(min = 10, max = 100, message = "minimum size of category title is 10")
	private String categoryDescription;
}
