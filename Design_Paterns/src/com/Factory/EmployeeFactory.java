package com.Factory;

public class EmployeeFactory {

	// get the Employee
	public static Employee getEmployee(String empType) {
		if(empType.trim().equalsIgnoreCase("ANDROID DEVELOPER")) {
			return new AnroidDeveloper();
		}else if(empType.trim().equalsIgnoreCase("Web DEVELOPER")) {
			return new WebDeveloper();
		}else {
			return null;
		}
	}
}
