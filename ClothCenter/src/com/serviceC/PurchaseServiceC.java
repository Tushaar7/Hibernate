package com.serviceC;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import com.daoI.PurchaseDaoI;
import com.model.AssetModel;
import com.model.AssetModel1;
import com.model.BillingModel;
import com.model.Labilites;
import com.model.Labilites2;
import com.model.PurchaseLedgerModel;
import com.model.PurchaseModel;
import com.model.SuplierModel;
import com.serviceI.PurchaseServiceI;
@Service(value="PurchaseServiceI")
public class PurchaseServiceC implements PurchaseServiceI {
	@Autowired
	private PurchaseDaoI purchaseDaoI;
	
	@Transactional
	@Override
	public List<String> getmaxproductid() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getmaxproductid();
	}
	@Transactional
	@Override
	public HashMap getsubproductid() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getsubproductid();
	}
	@Transactional
	@Override
	public void savepurchase(PurchaseModel purchaseModel,
			SuplierModel supliermodel, List productTypelist,
			List productNamelist, List quntitylist, List pricelist,
			List amountlist,BillingModel billingmodel,PurchaseLedgerModel purchaseLedgerModel) {
		
		purchaseDaoI.savepurchase(purchaseModel,supliermodel,productTypelist,productNamelist,quntitylist,pricelist,amountlist,billingmodel,purchaseLedgerModel);
	}
	@Transactional
	@Override
	public List<String> getpurchaselist() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getpurchaselist();
	}
	@Transactional
	@Override
	public List<String> getpurchaseLedger(String ledgerType,String ledgername,String customerName) {
		// TODO Auto-generated method stub
		return purchaseDaoI.getpurchaseLedger(ledgerType,ledgername,customerName);
	}
	@Transactional
	@Override
	public List<String> getpurchaseLedgerlist(String billid) {
		// TODO Auto-generated method stub
		return purchaseDaoI.getpurchaseLedgerlist(billid);
	}
	@Transactional
	@Override
	public HashMap getladgername() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getladgername();
	}
	@Transactional
	@Override
	public HashMap getCustomerNamelist() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getCustomerNamelist();
	}
	@Transactional
	@Override
	public String gettotalAmount(String supplierName) {
		// TODO Auto-generated method stub
		return purchaseDaoI.gettotalAmount(supplierName);
	}
	@Transactional
	@Override
	public String getRemainingAmounts(String supplierName) {
		// TODO Auto-generated method stub
		return purchaseDaoI.getRemainingAmounts(supplierName);
	}
	@Transactional
	@Override
	public void paymentMode(String supplierName, String totalAmount,
			String remainingAmount, String paidAmount,HttpSession session) {
		purchaseDaoI.paymentMode(supplierName,totalAmount,remainingAmount,paidAmount,session);
		
	}
	@Transactional
	@Override
	public String getSalesTotalAmount(String customerN) {
		// TODO Auto-generated method stub
		return purchaseDaoI.getSalesTotalAmount(customerN);
	}
	@Transactional
	@Override
	public String getSalesPaidAmount(String customerN) {
		// TODO Auto-generated method stub
		return purchaseDaoI.getSalesPaidAmount(customerN);
	}
	@Transactional
	@Override
	public void saveItem(String productType, String productItem,
			String quntity, String price, String amount,String billingId) {
			purchaseDaoI.saveItem(productType,productItem,quntity,price,amount,billingId);
		
	}
	@Transactional
	@Override
	public List<String> getalllist() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getalllist();
	}
	@Transactional
	@Override
	public List<String> getBillinglist() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getBillinglist();
	}
	@Transactional
	@Override
	public List<String> getMaxBillid() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getMaxBillid();
	}
	@Transactional
	@Override
	public void savpurchase(String supplierName, String discount,
			String paymentMode, String paidAmount, String totAmount) {
		// TODO Auto-generated method stub
		purchaseDaoI.savpurchase(supplierName,discount,paymentMode,paidAmount,totAmount);
	}
	@Transactional
	@Override
	public List<String> productList(String productname) {
		// TODO Auto-generated method stub
		return purchaseDaoI.productList(productname);
	}
	@Transactional
	@Override
	public List<String> getpurchasepriceList() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getpurchasepriceList();
	}
	@Transactional
	@Override
	public String salesPriceList() {
		// TODO Auto-generated method stub
		return purchaseDaoI.salesPriceList();
	}
	@Transactional
	@Override
	public String closingStockList() {
		// TODO Auto-generated method stub
		return purchaseDaoI.closingStockList();
	}
	@Transactional
	@Override
	public HashMap getlistname() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getlistname();
	}
	@Transactional
	@Override
	public String getsupplierName(String supplierName) {
		// TODO Auto-generated method stub
		return purchaseDaoI.getsupplierName(supplierName);
	}
	@Transactional
	@Override
	public HashMap getCustomerName() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getCustomerName();
	}
	@Transactional
	@Override
	public void addItem(AssetModel assetModel,double totalAmout,String openingBalance) {
		// TODO Auto-generated method stub
		purchaseDaoI.addItem(assetModel,totalAmout,openingBalance);
	}
	@Transactional
	@Override
	public void lessItem(AssetModel1 assetModel,Double totalAmount) {
		// TODO Auto-generated method stub
		purchaseDaoI.lessItem(assetModel,totalAmount);
	}
	@Transactional
	@Override
	public String getnetprofit() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getnetprofit();
	}
	@Transactional
	@Override
	public String getclosingstock() {
		// TODO Auto-generated method stub
		return purchaseDaoI.getclosingstock();
	}
	@Transactional
	@Override
	public void addLablites(Labilites labilites, Double totAmount) {
		// TODO Auto-generated method stub
		purchaseDaoI.addLablites(labilites,totAmount);
	}
	@Transactional
	@Override
	public void lesslabItem(Labilites2 labilites) {
		purchaseDaoI.lesslabItem(labilites);
	}
	@Transactional
	@Override
	public List<String> getitemlist(String productitem) {
		// TODO Auto-generated method stub
		return purchaseDaoI.getitemlist(productitem);
	}
	@Transactional
	@Override
	public void update(String id, String productType, String productItem,
			String quantity, String price, String amount, double totalAmount,
			String billid) {
		// TODO Auto-generated method stub
		purchaseDaoI.update(id,productType,productItem,quantity,price,amount,totalAmount,billid);
	}
	@Transactional
	@Override
	public List<String> updateStock(String productType, String productItem,
			String productCode, String price, String quantity, String totAmount) {
		// TODO Auto-generated method stub
		return purchaseDaoI.updateStock(productType,productItem,productCode,price,quantity,totAmount);
	}
	@Transactional
	@Override
	public String getpurchasetotAmount() {
		
		return purchaseDaoI.getpurchasetotAmount();
	}
	@Transactional
	@Override
	public List<String> opeingBalance(String lablitesDate12) {
		// TODO Auto-generated method stub
		return purchaseDaoI.openingBalance(lablitesDate12);
	}
	@Transactional
	@Override
	public List<String> netprofit(String lablitesDate12) {
		// TODO Auto-generated method stub
		return purchaseDaoI.netprofit(lablitesDate12);
	}
	@Transactional
	@Override
	public List<String> addlablites(String lablitesDate12) {
		// TODO Auto-generated method stub
		return purchaseDaoI.addlablites(lablitesDate12);
	}
	@Transactional
	@Override
	public List<String> lessLablitesl(String lablitesDate12) {
		// TODO Auto-generated method stub
		return purchaseDaoI.lessLablites(lablitesDate12);
	}
	@Transactional
	@Override
	public String gettotalA(String todate11, String fromDate1) {
		// TODO Auto-generated method stub
		return purchaseDaoI.gettotalA(todate11,fromDate1);
	}
	@Transactional
	@Override
	public List<String> getTotalA(String toDates, String toFroms) {
		// TODO Auto-generated method stub
		return purchaseDaoI.getTotalA(toDates,toFroms);
	}


}	