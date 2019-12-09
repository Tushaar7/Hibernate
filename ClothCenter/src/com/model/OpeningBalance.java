package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OpeningBalance {

	@Id
	@GeneratedValue
	private int openId;
	private String openingDate;
	private double totalAmount;
	public int getOpenId() {
		return openId;
	}
	public void setOpenId(int openId) {
		this.openId = openId;
	}
	public String getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
