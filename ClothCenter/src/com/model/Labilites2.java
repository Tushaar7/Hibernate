package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Labilites2 {
	@Id
	@GeneratedValue
	private int labId;
	private String lessItem;
	private double lessAmmount;
	private String labDate;
	public int getLabId() {
		return labId;
	}
	public void setLabId(int labId) {
		this.labId = labId;
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
	public String getLabDate() {
		return labDate;
	}
	public void setLabDate(String labDate) {
		this.labDate = labDate;
	}
	

}
