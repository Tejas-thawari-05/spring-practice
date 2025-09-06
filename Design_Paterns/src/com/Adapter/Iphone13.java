package com.Adapter;

public class Iphone13 {

	private AppleCharger appleCharger;
	
	public Iphone13(AppleCharger appleCharger) {
		super();
		this.appleCharger = appleCharger;
	}


	public void chargeIphone() {
		appleCharger.chargePhone();
	}
}
