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
public class BillingModel {
	@Id
	@GeneratedValue
	private int bilid;
	private String Date;
	private double TotalAmount;
	private double Discount;
	private String PaymentMode;
	private double paidAmount;
	private double remainingAmount;
	private String ledgerName;
	private String productCode;
	
	public String getPaymentMode() {
		return PaymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		PaymentMode = paymentMode;
	}
    
	@OneToMany(mappedBy="billingModel")
	private List<PurchaseModel> purchasemodel=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="suplierid")
    private SuplierModel suplierModel;
	
	public SuplierModel getSuplierModel() {
		return suplierModel;
	}

	public void setSuplierModel(SuplierModel suplierModel) {
		this.suplierModel = suplierModel;
	}

	public int getBilid() {
		return bilid;
	}

	

	public void setBilid(int bilid) {
		this.bilid = bilid;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public double getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}

	public double getDiscount() {
		return Discount;
	}

	public void setDiscount(double discount) {
		Discount = discount;
	}

	public List<PurchaseModel> getPurchasemodel() {
		return purchasemodel;
	}

	public void setPurchasemodel(List<PurchaseModel> purchasemodel) {
		this.purchasemodel = purchasemodel;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	
	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public String getLedgerName() {
		return ledgerName;
	}

	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	
	

}
