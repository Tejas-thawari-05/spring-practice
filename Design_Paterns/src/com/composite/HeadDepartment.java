package com.composite;

import java.util.List;

public class HeadDepartment implements Department{

	private int id;
	
	private String name;
	
	private List<Department> childDepartments;
	
	

	public HeadDepartment(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	
	@Override
	public void printDepartmentName() {
		childDepartments.forEach(Department :: printDepartmentName);
	}
	
	public void addDepartment(Department department) {
		childDepartments.add(department);
	} 
	
	public void removeDepartment(Department department) {
		childDepartments.remove(department);
	}
	
}
