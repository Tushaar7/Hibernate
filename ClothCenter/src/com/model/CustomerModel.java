package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CustomerModel {
	@Id
	@GeneratedValue
	private int customerid;
	private String customername;
	private double remainingAmount;
	private double totalAmount;
	private double paidAmount;
	public int getCustomerid() {
		return customerid;
	}
	@OneToMany(mappedBy="customerModel")
	List<SalesBillingModel> salesBillingmodel=new ArrayList<SalesBillingModel>();
	
	@OneToMany(mappedBy="customerModel")
	List<SalesLedgerModel> salelLedgerModel=new ArrayList<>();
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
		
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public List<SalesBillingModel> getSalesBillingmodel() {
		return salesBillingmodel;
	}
	public void setSalesBillingmodel(List<SalesBillingModel> salesBillingmodel) {
		this.salesBillingmodel = salesBillingmodel;
	}
	public double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	


}
