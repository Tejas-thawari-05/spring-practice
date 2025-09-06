package com.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.Dto.EmployeeDto;
import com.Exception.ResourceNotFoundException;
import com.entity.Department;
import com.entity.Employee;
import com.repository.DepartmentRepository;
import com.repository.EmployeeRepository;
import com.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository employeeRepository;
	
	private final DepartmentRepository departmentRepository;
	
	private final ModelMapper modelMapper;

	@Override
	public EmployeeDto createEmployee(EmployeeDto emp) {
		 Department department = departmentRepository.findById(emp.getDepartment().getDeptId())
	                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

	        Employee employee = modelMapper.map(emp, Employee.class);
	        employee.setDepartment(department);

	        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
	}

	@Override
	public EmployeeDto getEmployeeById(int empId) {
		
		return modelMapper.map(getEntity(empId), EmployeeDto.class);
		
	}
				private Employee getEntity(int id) {
			        return employeeRepository.findById(id)
			                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
			    }

	@Override
	public List<EmployeeDto> getAllEmployees() {
		
		return employeeRepository.findAll().stream()
				.map(emp -> modelMapper.map(emp, EmployeeDto.class))
				.toList();
	}

	@Override
	public EmployeeDto updateEmployee(int empId, EmployeeDto emp) {
		Employee entity = getEntity(empId);
		
		Department department = departmentRepository.findById(emp.getDepartment().getDeptId()).orElseThrow(() -> new ResourceNotFoundException("Department not Founf"));
		
		entity.setName(emp.getName());
		entity.setEmail(emp.getEmail());
		entity.setDepartment(department);
		entity.setSalary(emp.getSalary());
		
		return modelMapper.map(employeeRepository.save(entity), EmployeeDto.class);
	}

	@Override
	public String deleteEmployee(int empId) {
		
		employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id = "+empId));
		
		employeeRepository.deleteById(empId);
		return "Employee Deleted Successfully";
	}

}
