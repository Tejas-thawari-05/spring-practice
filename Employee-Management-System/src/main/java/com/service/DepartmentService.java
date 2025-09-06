package com.service;

import java.util.List;

import com.Dto.DepartmentDto;

public interface DepartmentService {

	DepartmentDto createDept(DepartmentDto dept);
	
	DepartmentDto getById(int deptId);
	
	List<DepartmentDto> getAll();
	
	DepartmentDto update(int deptId, DepartmentDto dept);
	
	void delete(int deptId);
}
