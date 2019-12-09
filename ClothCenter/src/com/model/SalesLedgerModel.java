package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SalesLedgerModel {
	@Id
	@GeneratedValue
	private int saleLid;
	private String salesDate;
	private double paidAmount;
	private double totAmount;
	private double remainingA;;
	
	@ManyToOne
	@JoinColumn(name="customerid")
	CustomerModel customerModel;
	public int getSaleLid() {
		return saleLid;
	}
	public void setSaleLid(int saleLid) {
		this.saleLid = saleLid;
	}
	public String getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getTotAmount() {
		return totAmount;
	}
	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}
	public CustomerModel getCustomerModel() {
		return customerModel;
	}
	public void setCustomerModel(CustomerModel customerModel) {
		this.customerModel = customerModel;
	}
	public double getRemainingA() {
		return remainingA;
	}
	public void setRemainingA(double remainingA) {
		this.remainingA = remainingA;
	}
	
	
	


}
