package com.prototype;

import java.util.ArrayList;
import java.util.List;

public class NetworkConnection implements Cloneable{

	private String ip;
	
	private String impData;
	
	private List<String> domain = new ArrayList<>();

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getImpData() {
		return impData;
	}

	public void setImpData(String impData) {
		this.impData = impData;
	}
	
	
	public List<String> getDomain() {
		return domain;
	}

	public void setDomain(List<String> domain) {
		this.domain = domain;
	}

	public void loadVeryImpData() throws InterruptedException {
		this.impData = "Very Very imp data";
		domain.add("www.xyz.com");
		domain.add("www.xyz.com");
		domain.add("www.xyz.com");
		Thread.sleep(5000);
	}

	

	@Override
	public String toString() {
		return "NetworkConnection [ip=" + ip + ", impData=" + impData + ", domain=" + domain + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		//  logic for cloning   /// DEEP CLONING
		
		NetworkConnection networkConnection = new NetworkConnection();
		networkConnection.setIp(getIp());
		networkConnection.setImpData(getImpData());
		
		for(String d : getDomain()) {
			networkConnection.getDomain().add(d);
		}
		return networkConnection;
	}
	
	 
	
}
