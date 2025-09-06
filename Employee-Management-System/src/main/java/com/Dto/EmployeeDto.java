package com.Dto;

import lombok.Data;

@Data
public class EmployeeDto {

	private int empId;
	
	private String name;
	
	private String email;
	
	private double salary;
	
	private DepartmentDto department;
}
