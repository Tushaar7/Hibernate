package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AssetModel1 {
	@Id
	@GeneratedValue
	private int assetId;
	private String lessItem;
	private String assetDate;
	private double lessAmount;
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public String getLessItem() {
		return lessItem;
	}
	public void setLessItem(String lessItem) {
		this.lessItem = lessItem;
	}
	public String getAssetDate() {
		return assetDate;
	}
	public void setAssetDate(String assetDate) {
		this.assetDate = assetDate;
	}
	public double getLessAmount() {
		return lessAmount;
	}
	public void setLessAmount(double lessAmount) {
		this.lessAmount = lessAmount;
	}
	

}
