package com.emp.demo;

public class Address {
	
	private String addressLine1,city,state,country;
	
	public Address(String addressLine1, String city, String state, String country){		
		this.addressLine1 = addressLine1;
		this.city = city;
		this.state = state;
		this.country = country;		
	}

	@Override
	public String toString() {
		return "Address [addressLine1=" + addressLine1 + ", city=" + city
				+ ", state=" + state + ", country=" + country + "]";
	}
}
