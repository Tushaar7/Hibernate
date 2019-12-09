package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AssetModel {
	@Id
	@GeneratedValue
	private int assetid;
	private String addItemName;
	private double addAmount;
	private String lessItem;
	private double lessAmount;
	private String  assetDate;
	public int getAssetid() {
		return assetid;
	}
	public void setAssetid(int assetid) {
		this.assetid = assetid;
	}
	public String getAddItemName() {
		return addItemName;
	}
	public void setAddItemName(String addItemName) {
		this.addItemName = addItemName;
	}
	public double getAddAmount() {
		return addAmount;
	}
	public void setAddAmount(double addAmount) {
		this.addAmount = addAmount;
	}
	
	public String getAssetDate() {
		return assetDate;
	}
	public void setAssetDate(String assetDate) {
		this.assetDate = assetDate;
	}
	public String getLessItem() {
		return lessItem;
	}
	public void setLessItem(String lessItem) {
		this.lessItem = lessItem;
	}
	public double getLessAmount() {
		return lessAmount;
	}
	public void setLessAmount(double lessAmount) {
		this.lessAmount = lessAmount;
	}
}
