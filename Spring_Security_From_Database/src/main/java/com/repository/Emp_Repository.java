package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Employee;

public interface Emp_Repository extends JpaRepository<Employee, Integer>{

	public Employee findByEmail(String email);
}
