package com.service;

import java.util.List;

import com.Dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto createEmployee(EmployeeDto emp);
	
	EmployeeDto getEmployeeById(int empId);
	
	List<EmployeeDto> getAllEmployees();
	
	EmployeeDto updateEmployee(int empId, EmployeeDto emp);
	
	String deleteEmployee(int empId);
}
