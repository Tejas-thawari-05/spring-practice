package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	
	@NotBlank(message = "Name should not be null")
	private String name;
	
	@Email(message = "Enter a valid email")
	@Column(unique = true)
	private String email;
	
//	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id" , nullable = false)
	private Department department;
	
	private double salary;
}
