package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address100")
public class Address {
	
	@Id
	@Column(name="address_id")
	@GeneratedValue
	private int aid;
	
	@Column(name="address_street")
	private String astreet;
	
	@Column(name="address_city")
	private String acity;
	
	@Column(name="address_state")
	private String astate;
	
	@Column(name="address_zipcode")
	private String azipcode;
	
	public Address(String astreet, String acity, String astate, String azipcode) {
		super();
		this.astreet = astreet;
		this.acity = acity;
		this.astate = astate;
		this.azipcode = azipcode;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAstreet() {
		return astreet;
	}

	public void setAstreet(String astreet) {
		this.astreet = astreet;
	}

	public String getAcity() {
		return acity;
	}

	public void setAcity(String acity) {
		this.acity = acity;
	}

	public String getAstate() {
		return astate;
	}

	public void setAstate(String astate) {
		this.astate = astate;
	}

	public String getAzipcode() {
		return azipcode;
	}

	public void setAzipcode(String azipcode) {
		this.azipcode = azipcode;
	}
}
