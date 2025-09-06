package com.Adapter;

public class AdapterCharger implements AppleCharger{

	private AndroidCharger charger;
	
	public AdapterCharger(AndroidCharger charger) {
		super();
		this.charger = charger;
	}


	@Override
	public void chargePhone() {
		
		charger.chargerAndroidPhone();
		System.out.println("Your phone is charging with Adapter");
	}

}
