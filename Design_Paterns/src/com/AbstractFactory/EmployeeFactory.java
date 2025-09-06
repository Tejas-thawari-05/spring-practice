package com.AbstractFactory;

public class EmployeeFactory {

	// get Employee
	
	public static Employee getEmployee(EmployeeAbstractFactory factory) {
		return factory.createEmployee();
	}
}
