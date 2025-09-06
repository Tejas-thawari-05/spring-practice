package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dto.EmployeeDto;
import com.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto emp){
		return ResponseEntity.ok(employeeService.createEmployee(emp));
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAll(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int empId){
		return ResponseEntity.ok(employeeService.getEmployeeById(empId));
	}
	
	@PutMapping("/{empId}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int empId, @RequestBody EmployeeDto emp){
		return ResponseEntity.ok(employeeService.updateEmployee(empId, emp));
	}
	
	@DeleteMapping("/{empId}")
	public void deleteEmployee(@PathVariable int empId){
		employeeService.deleteEmployee(empId);
	}
}
