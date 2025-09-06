package com.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.Dto.DepartmentDto;
import com.Exception.ResourceNotFoundException;
import com.entity.Department;
import com.repository.DepartmentRepository;
import com.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

	private final DepartmentRepository departmentRepository;
	
	private final ModelMapper modelMapper;
	
	@Override
	public DepartmentDto createDept(DepartmentDto dept) {
		Department department = modelMapper.map(dept, Department.class);
		return modelMapper.map(departmentRepository.save(department), DepartmentDto.class);
	}

	@Override
	public DepartmentDto getById(int deptId) {
		Department department = findById(deptId);
		
		return modelMapper.map(department, DepartmentDto.class);
	}
	
					 private Department findById(int id) {
					        return departmentRepository.findById(id)
					                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
					    }  
	 
	 

	@Override
	public List<DepartmentDto> getAll() {
		return departmentRepository.findAll()
				.stream()
				.map(dept -> modelMapper.map(dept, DepartmentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDto update(int deptId, DepartmentDto dept) {
		Department department = findById(deptId);
		department.setDeptName(dept.getDeptName());
		return modelMapper.map(departmentRepository.save(department), DepartmentDto.class);
	}

	@Override
	public void delete(int deptId) {
		departmentRepository.deleteById(deptId);
	}

}
