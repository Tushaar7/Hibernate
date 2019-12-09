package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class PurchaseModel {
	@Id
	@GeneratedValue
	private int Purchaseid;                   
	private String quntity;
	private double Price;
    private double amount;
    private String productCode,purdate;
  
	@ManyToOne
	@JoinColumn(name="id")
	StockModel stockModel;
	
	@ManyToOne
	@JoinColumn(name="productid")
	private ProductModel productmodel1;
	@ManyToOne
	@JoinColumn(name="subProductid")
	Sub_ProductModel subProductmodel;
	
	@ManyToOne
	@JoinColumn(name="bilid")
	BillingModel  billingModel;
	public BillingModel getBillingModel() {
		return billingModel;
	}
    public void setBillingModel(BillingModel billingModel) {
		this.billingModel = billingModel;
	}
	public Sub_ProductModel getSubProductmodel() {
		return subProductmodel;
	}
	public void setSubProductmodel(Sub_ProductModel subProductmodel) {
		this.subProductmodel = subProductmodel;
	}
	public int getPurchaseid() {
		return Purchaseid;
	}
	public void setPurchaseid(int purchaseid) {
		Purchaseid = purchaseid;
	}
	
	public String getQuntity() {
		return quntity;
	}
	public void setQuntity(String quntity) {
		this.quntity = quntity;
	}
	public double getAmount() {
		return amount;
	}
	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ProductModel getProductmodel1() {
		return productmodel1;
	}

	public void setProductmodel1(ProductModel productmodel1) {
		this.productmodel1 = productmodel1;
	}

	public StockModel getStockModel() {
		return stockModel;
	}

	public void setStockModel(StockModel stockModel) {
		this.stockModel = stockModel;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getPurdate() {
		return purdate;
	}
	public void setPurdate(String purdate) {
		this.purdate = purdate;
	}
	

	

}
