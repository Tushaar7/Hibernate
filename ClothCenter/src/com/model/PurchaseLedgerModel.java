package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseLedgerModel {
	@Id
	@GeneratedValue
	private int purid;
	private String ledgerDate;
	private double paidAmount;
	private double totalAmount;
	private double remainingAmount;
	@ManyToOne
	@JoinColumn(name="suplierid")
	SuplierModel supplierModel;
	
	public int getPurid() {
		return purid;
	}
	public void setPurid(int purid) {
		this.purid = purid;
	}
	public String getLedgerDate() {
		return ledgerDate;
	}
	public void setLedgerDate(String ledgerDate) {
		this.ledgerDate = ledgerDate;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public SuplierModel getSupplierModel() {
		return supplierModel;
	}
	public void setSupplierModel(SuplierModel supplierModel) {
		this.supplierModel = supplierModel;
	}
	public double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	
	
}
