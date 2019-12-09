package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Labilites {

	@Id
	@GeneratedValue
	private int labid;
	private String addItem;
	private  double addAmount;
	private String lessItem;
	private double lessAmmount;
	private String labDate;
	public int getLabid() {
		return labid;
	}
	public void setLabid(int labid) {
		this.labid = labid;
	}
	public String getAddItem() {
		return addItem;
	}
	public void setAddItem(String addItem) {
		this.addItem = addItem;
	}
	
	public String getLabDate() {
		return labDate;
	}
	public void setLabDate(String labDate) {
		this.labDate = labDate;
	}
	public double getAddAmount() {
		return addAmount;
	}
	public void setAddAmount(double addAmount) {
		this.addAmount = addAmount;
	}
	public String getLessItem() {
		return lessItem;
	}
	public void setLessItem(String lessItem) {
		this.lessItem = lessItem;
	}
	public double getLessAmmount() {
		return lessAmmount;
	}
	public void setLessAmmount(double lessAmmount) {
		this.lessAmmount = lessAmmount;
	}
	
}
