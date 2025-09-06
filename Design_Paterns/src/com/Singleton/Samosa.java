package com.Singleton;

import java.io.Serializable;

public class Samosa implements Serializable,Cloneable{
	
	
//	public enum Samosa{
//		INSTANCE;
//		
//		public void test() {
//			System.out.println("Test method");
//		}
//	}
	
	
	
	
	

	private static Samosa samosa;
	
	
	//Constructor
		private Samosa() {
			if(samosa != null) {
				throw new RuntimeException("You are trying to break singleton pattern...");
			}
		}
	
	// Synchronized block is best method for singleton design patter
	
	public static Samosa synchronizedSamosa() {
		
		if(samosa == null) {
			
			// Synchronized block to get access for only one thread at a time
			synchronized (Samosa.class) {
				if(samosa == null) {
					samosa = new Samosa();
				}
			}
		}
		return samosa;
	}
	
	
	public Object readResolve() {
		return samosa;
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
	//	return super.clone();
		
		return samosa;
	}
	
	

	
	
	
	
	// Lazy way of creating Single object
	// lazy is good when single thread works 
	// when there are multiple thread each thread will get null at a time and they each will store object
	// it can be used when thread safety is not their
	public static Samosa getSamosa() {
		if(samosa == null) {
			samosa = new Samosa();
		}
		return samosa;
	}
	
	
	// Eager way of creating singleton object 
	//it is not a good way in terms of project 
	//because it creates object jast after class load
	// it can down the performance of the project
	private static Samosa sam12 = new Samosa();
	
	public static Samosa getsam12() {
		return sam12;
	}
}

/*
		1. Constructor Should be private 
		2. object create with the help of method
		3. Create field to store object in private 
*/