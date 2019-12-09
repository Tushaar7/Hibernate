package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SalesBillingModel {
	@Id
	@GeneratedValue
	private int salebillinngid;
	private double totalAmount;
	private double discount;
	private String saledate;
	private String paymentmode;
	private double remainingAmount;
	private double paidAmount;
		
	@ManyToOne
	@JoinColumn(name="customerid")
	CustomerModel customerModel;
	
	@OneToMany(mappedBy="salebillingmodel")
	List<SalesModel> salesmodel=new ArrayList<>();
	public int getSalebillinngid() {
		return salebillinngid;
	}
	public List<SalesModel> getSalesmodel() {
		return salesmodel;
	}
	public void setSalesmodel(List<SalesModel> salesmodel) {
		this.salesmodel = salesmodel;
	}
	public void setSalebillinngid(int salebillinngid) {
		this.salebillinngid = salebillinngid;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getSaledate() {
		return saledate;
	}
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public CustomerModel getCustomerModel() {
		return customerModel;
	}
	public void setCustomerModel(CustomerModel customerModel) {
		this.customerModel = customerModel;
	}
	public double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	

}
