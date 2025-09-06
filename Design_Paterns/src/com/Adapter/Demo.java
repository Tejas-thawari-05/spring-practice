package com.Adapter;

public class Demo {
 
	// Adapter provide the interface for the classes
	//to become compatible with the functionality of other classes
	
	
	public static void main(String[] args) {
		System.out.println("Program Started");
		
		AppleCharger charger = new AdapterCharger(new TTCharger());
		
		Iphone13 iphone13 = new Iphone13(charger);
		
		iphone13.chargeIphone();
	}
}
