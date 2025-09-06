package com.AbstractFactory;

public class Client {

	public static void main(String[] args) {
		Employee employee1 = EmployeeFactory.getEmployee(new AndroidDevFactory());
		System.out.println(employee1.name());
		
		Employee employee2 = EmployeeFactory.getEmployee(new WenDevFactory());
		System.out.println(employee2.name());
	}

}
