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
public class Sub_ProductModel {

	@Id
	@GeneratedValue
	private int subProductid;
	private String SubProductName;
	private String codeSub;

	@OneToMany(mappedBy="sub_ProductModel")
	List<SalesModel> salemodel=new ArrayList<SalesModel>();
	
	@OneToMany(mappedBy="sub_ProductModel")
	List<StockModel> stockmodel=new ArrayList<StockModel>();
	/*	maping one to many purchase*/
	@OneToMany(mappedBy="subProductmodel")
	List<PurchaseModel> purchasemodel1=new ArrayList<>();
	public List<PurchaseModel> getPurchasemodel1() {
		return purchasemodel1;
	}

	public void setPurchasemodel1(List<PurchaseModel> purchasemodel1) {
		this.purchasemodel1 = purchasemodel1;
	}

	@ManyToOne
	@JoinColumn(name="productid")
   private ProductModel productmodel;

	public int getSubProductid() {
		return subProductid;
	}

	public void setSubProductid(int subProductid) {
		this.subProductid = subProductid;
	}

	public String getSubProductName() {
		return SubProductName;
	}

	public void setSubProductName(String subProductName) {
		SubProductName = subProductName;
	}

	public ProductModel getProductmodel() {
		return productmodel;
	}

	public void setProductmodel(ProductModel productmodel) {
		this.productmodel = productmodel;
	}

	public List<SalesModel> getSalemodel() {
		return salemodel;
	}

	public void setSalemodel(List<SalesModel> salemodel) {
		this.salemodel = salemodel;
	}

	public List<StockModel> getStockmodel() {
		return stockmodel;
	}

	public void setStockmodel(List<StockModel> stockmodel) {
		this.stockmodel = stockmodel;
	}

	public String getCodeSub() {
		return codeSub;
	}

	public void setCodeSub(String codeSub) {
		this.codeSub = codeSub;
	}


	
}

