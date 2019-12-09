package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NetProfit {
	@Id
	@GeneratedValue
	private int netprofitid;
	private double netProfit,closingbalance;
	private String todate;
	
	public int getNetprofitid() {
		return netprofitid;
	}
	public void setNetprofitid(int netprofitid) {
		this.netprofitid = netprofitid;
	}
	public double getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(double netProfit) {
		this.netProfit = netProfit;
	}
	
	public double getClosingbalance() {
		return closingbalance;
	}
	public void setClosingbalance(double closingbalance) {
		this.closingbalance = closingbalance;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	
}
