package com.composite;

public class FinancialDepartment2 implements Department{

   private int id;
	
	private String name;
	

	public FinancialDepartment2(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	@Override
	public void printDepartmentName() {
	
		System.out.println(getClass().getSimpleName());
	}
}
