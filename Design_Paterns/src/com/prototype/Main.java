package com.prototype;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
		System.out.println("Creating object using prototype");

		NetworkConnection networkConnection = new NetworkConnection();
		
		networkConnection.setIp("192.168.4.4");
		networkConnection.loadVeryImpData();
		
		
		System.out.println(networkConnection);
		
		// we want new object of network connection
		
		NetworkConnection networkConnection2 =  (NetworkConnection) networkConnection.clone();
		NetworkConnection networkConnection3 =  (NetworkConnection) networkConnection.clone();
		
		networkConnection2.getDomain().remove(0);
		System.out.println(networkConnection2);
		System.out.println(networkConnection3);
	}

}
