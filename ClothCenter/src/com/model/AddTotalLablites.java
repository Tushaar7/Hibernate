package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AddTotalLablites {
       @Id
       @GeneratedValue
       private int labid;
       private String lablitesDate;
       private double labAmount;
	public int getLabid() {
		return labid;
	}
	public void setLabid(int labid) {
		this.labid = labid;
	}
	public String getLablitesDate() {
		return lablitesDate;
	}
	public void setLablitesDate(String lablitesDate) {
		this.lablitesDate = lablitesDate;
	}
	public double getLabAmount() {
		return labAmount;
	}
	public void setLabAmount(double labAmount) {
		this.labAmount = labAmount;
	}
       
}
