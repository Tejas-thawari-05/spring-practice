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

import com.Dto.DepartmentDto;
import com.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DepartmentController {

	public final DepartmentService departmentService;
	
	@PostMapping
	public ResponseEntity<DepartmentDto> create(@RequestBody DepartmentDto dept){
		return ResponseEntity.ok(departmentService.createDept(dept));
	}
	
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAll(){
		return ResponseEntity.ok(departmentService.getAll());
	}
	
	@GetMapping("/{deptId}")
	public ResponseEntity<DepartmentDto> get(@PathVariable int deptId){
		return ResponseEntity.ok(departmentService.getById(deptId));
	}
	
	@PutMapping("/{deptId}")
	public ResponseEntity<DepartmentDto> update(@PathVariable int deptId, @RequestBody DepartmentDto dept){
		return ResponseEntity.ok(departmentService.update(deptId, dept));
	}
	
	@DeleteMapping("/{deptId}")
	public ResponseEntity<String> delete(@PathVariable int deptId){
		departmentService.delete(deptId);
		return ResponseEntity.ok("Department deleted successfully");
	}
}
