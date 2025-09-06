package com.Factory;

public class DeveloperClient {

	public static void main(String[] args) {
		
		Employee emp = EmployeeFactory.getEmployee("Android Developer");
		System.out.println("salary : "+emp.salary());
		
		Employee emp2 = EmployeeFactory.getEmployee("web Developer");
		System.out.println("salary : "+emp2.salary());
	}

}
