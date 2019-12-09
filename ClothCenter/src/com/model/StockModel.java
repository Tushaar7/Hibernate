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
public class StockModel
	{

			@Id
			@GeneratedValue
			private int id;
			private String quntity;
			private double price;
			private double ToTalAmount;
			private String purchaseCode,puRDate;
			@OneToMany(mappedBy="stockModel")
			List<PurchaseModel> purchasemodel=new ArrayList<>();
			@ManyToOne
			@JoinColumn(name="productid")
			ProductModel productmodel;
			
			@ManyToOne
			@JoinColumn(name="subProductid")
			Sub_ProductModel sub_ProductModel;
			@OneToMany(mappedBy="stockModel")
			List<SalesModel> salesModel=new ArrayList<>();
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getQuntity() {
				return quntity;
			}
			public void setQuntity(String quntity) {
				this.quntity = quntity;
			}
			public ProductModel getProductmodel() {
				return productmodel;
			}
			public void setProductmodel(ProductModel productmodel) {
				this.productmodel = productmodel;
			}
			public Sub_ProductModel getSub_ProductModel() {
				return sub_ProductModel;
			}
			public void setSub_ProductModel(Sub_ProductModel sub_ProductModel) {
				this.sub_ProductModel = sub_ProductModel;
			}
			public List<PurchaseModel> getPurchasemodel() {
				return purchasemodel;
			}
			public void setPurchasemodel(List<PurchaseModel> purchasemodel) {
				this.purchasemodel = purchasemodel;
			}
			public double getPrice() {
				return price;
			}
			public void setPrice(double price) {
				this.price = price;
			}
			public double getToTalAmount() {
				return ToTalAmount;
			}
			public void setToTalAmount(double toTalAmount) {
				ToTalAmount = toTalAmount;
			}
			public String getPurchaseCode() {
				return purchaseCode;
			}
			public void setPurchaseCode(String purchaseCode) {
				this.purchaseCode = purchaseCode;
			}
			public List<SalesModel> getSalesModel() {
				return salesModel;
			}
			public void setSalesModel(List<SalesModel> salesModel) {
				this.salesModel = salesModel;
			}
			public String getPuRDate() {
				return puRDate;
			}
			public void setPuRDate(String puRDate) {
				this.puRDate = puRDate;
			}
			
			

	}
