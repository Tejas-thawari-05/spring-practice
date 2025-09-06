package com.Factory;

public class WebDeveloper implements Employee{

	@Override
	public int salary() {
		System.out.println("Getting salary of web Developer");
		return 40000;
	}

}
