package com.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class SalesModel {
	@Id
	@GeneratedValue
	private int saleid;
	private String quantitys,saledate;
	private double price;
	private double amounts;
	
	@ManyToOne
	@JoinColumn(name="productid")
	ProductModel productmodels;
	@ManyToOne
	@JoinColumn(name="salebillinngid")
	SalesBillingModel salebillingmodel;
	@ManyToOne
	@JoinColumn(name="id")
	StockModel stockModel;
	@Transient
	private List productType;
	@Transient
	private List productname ;
	@Transient
	private List quantity ;
	@Transient
	private List productprice;
	@Transient
	private List Amount;
	
	
	@ManyToOne
	@JoinColumn(name="subProductid")
	Sub_ProductModel sub_ProductModel;
	
	
	public ProductModel getProductmodels() {
		return productmodels;
	}
	public void setProductmodels(ProductModel productmodels) {
		this.productmodels = productmodels;
	}
	public SalesBillingModel getSalebillingmodel() {
		return salebillingmodel;
	}
	public void setSalebillingmodel(SalesBillingModel salebillingmodel) {
		this.salebillingmodel = salebillingmodel;
	}
	public List getProductType() {
		return productType;
	}
	public void setProductType(List productType) {
		this.productType = productType;
	}
	public List getProductname() {
		return productname;
	}
	public void setProductname(List productname) {
		this.productname = productname;
	}
	public List getQuantity() {
		return quantity;
	}
	public void setQuantity(List quantity) {
		this.quantity = quantity;
	}
	public List getProductprice() {
		return productprice;
	}
	public void setProductprice(List productprice) {
		this.productprice = productprice;
	}
	public void setAmount(List amount) {
		Amount = amount;
	}
	public int getSaleid() {
		return saleid;
	}
	public void setSaleid(int saleid) {
		this.saleid = saleid;
	}
	public String getQuantitys() {
		return quantitys;
	}
	public void setQuantitys(String quantitys) {
		this.quantitys = quantitys;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmounts() {
		return amounts;
	}
	public void setAmounts(double amounts) {
		this.amounts = amounts;
	}
	public List getAmount() {
		return Amount;
	}
	public Sub_ProductModel getSub_ProductModel() {
		return sub_ProductModel;
	}
	public void setSub_ProductModel(Sub_ProductModel sub_ProductModel) {
		this.sub_ProductModel = sub_ProductModel;
	}
	public StockModel getStockModel() {
		return stockModel;
	}
	public void setStockModel(StockModel stockModel) {
		this.stockModel = stockModel;
	}
	public String getSaledate() {
		return saledate;
	}
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	
	

	

}
