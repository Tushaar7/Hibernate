package com.model;

import javax.persistence.Entity;
import javax.persistence.Table;

public class OnBlurModel {

	private Double price;
	private Double quantity;
	private Double amount;
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}	
}
