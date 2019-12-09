package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AddTotalAsset {
	@Id
	@GeneratedValue
	private int assetid;
	private double assetAmount ;
	private String assetDate;
	public int getAssetid() {
		return assetid;
	}
	public void setAssetid(int assetid) {
		this.assetid = assetid;
	}
	public double getAssetAmount() {
		return assetAmount;
	}
	public void setAssetAmount(double assetAmount) {
		this.assetAmount = assetAmount;
	}
	public String getAssetDate() {
		return assetDate;
	}
	public void setAssetDate(String assetDate) {
		this.assetDate = assetDate;
	}
	

}
