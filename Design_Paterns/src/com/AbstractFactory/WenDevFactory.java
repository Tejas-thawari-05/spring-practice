package com.AbstractFactory;

public class WenDevFactory extends EmployeeAbstractFactory{

	@Override
	public Employee createEmployee() {
		
		return new WebDeveloper();
	}

}
