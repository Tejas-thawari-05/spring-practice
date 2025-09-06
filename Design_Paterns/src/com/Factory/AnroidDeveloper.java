package com.Factory;

public class AnroidDeveloper implements Employee{

	@Override
	public int salary() {
		System.out.println("Getting Anroid Developer Salary");
		return 50000;
	}

}
