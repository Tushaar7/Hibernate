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
public class SuplierModel {
	@Id
	@GeneratedValue
	private int suplierid;
	private String Supliername;
	private String SuplierAddress;
	private String PhoneNumber;
	private double remainingAmount;
	private double totalAmount;
	private double paidAmount;
	@OneToMany(mappedBy="supplierModel")
	List<PurchaseLedgerModel> purchaseLedger=new ArrayList<>();
    @OneToMany(mappedBy="suplierModel")
    private List<BillingModel> billingmodel=new ArrayList<>();
	

	public List<BillingModel> getBillingmodel() {
		return billingmodel;
	}

	public void setBillingmodel(List<BillingModel> billingmodel) {
		this.billingmodel = billingmodel;
	}

	public int getSuplierid() {
		return suplierid;
	}

	public void setSuplierid(int suplierid) {
		this.suplierid = suplierid;
	}

	public String getSupliername() {
		return Supliername;
	}

	public void setSupliername(String supliername) {
		Supliername = supliername;
	}

	public String getSuplierAddress() {
		return SuplierAddress;
	}

	public void setSuplierAddress(String suplierAddress) {
		SuplierAddress = suplierAddress;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
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

	public List<PurchaseLedgerModel> getPurchaseLedger() {
		return purchaseLedger;
	}

	public void setPurchaseLedger(List<PurchaseLedgerModel> purchaseLedger) {
		this.purchaseLedger = purchaseLedger;
	}
	

}
