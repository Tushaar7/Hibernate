package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProductModel {

	
	@Id
	@GeneratedValue
	private int productid;
	private String productName;
	/*Mapping one to many*/
      @OneToMany(mappedBy="productmodel")
      private List<Sub_ProductModel>subproductModel=new ArrayList<>();
    
   /*   Mapping onetomanysalebilling*/
      @OneToMany(mappedBy="productmodels")
      List<SalesModel> saleModel=new ArrayList<>();
      
  /*    Mappling OneTomany Stock*/
      @OneToMany(mappedBy="productmodel")
      List<StockModel> stockmodel=new ArrayList<StockModel>();
      /* Mapping purchase model*/
      @OneToMany(mappedBy="productmodel1")
      private List<PurchaseModel> purchasemodel=new ArrayList<PurchaseModel>();
	public List<Sub_ProductModel> getSubproductModel() {
		return subproductModel;
	}
	public void setSubproductModel(List<Sub_ProductModel> subproductModel) {
		this.subproductModel = subproductModel;
	}
	public int getProductid() {
		return productid;
	}
	public List<PurchaseModel> getPurchasemodel() {
		return purchasemodel;
	}
	public void setPurchasemodel(List<PurchaseModel> purchasemodel) {
		this.purchasemodel = purchasemodel;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<SalesModel> getSaleModel() {
		return saleModel;
	}
	public void setSaleModel(List<SalesModel> saleModel) {
		this.saleModel = saleModel;
	}
	public List<StockModel> getStockmodel() {
		return stockmodel;
	}
	public void setStockmodel(List<StockModel> stockmodel) {
		this.stockmodel = stockmodel;
	}
	
}
