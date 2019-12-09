package com.daoI;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.model.AssetModel;
import com.model.AssetModel1;
import com.model.BillingModel;
import com.model.Labilites;
import com.model.Labilites2;
import com.model.PurchaseLedgerModel;
import com.model.PurchaseModel;
import com.model.SuplierModel;

public interface PurchaseDaoI {



	List<String> getmaxproductid();

	HashMap getsubproductid();

	void savepurchase(PurchaseModel purchaseModel, SuplierModel supliermodel,
			List productTypelist, List productNamelist, List quntitylist,
			List pricelist, List amountlist, BillingModel billingmodel, PurchaseLedgerModel purchaseLedgerModel);

	List<String> getpurchaselist();



	List<String> getpurchaseLedgerlist(String billid);

	HashMap getladgername();

	List<String> getpurchaseLedger(String ledgerType, String ledgername, String customerName);

	HashMap getCustomerNamelist();

	String gettotalAmount(String supplierName);

	String getRemainingAmounts(String supplierName);

	void paymentMode(String supplierName, String totalAmount,
			String remainingAmount, String paidAmount, HttpSession session);

	String getSalesTotalAmount(String customerN);

	String getSalesPaidAmount(String customerN);

	void saveItem(String productType, String productItem, String quntity,
			String price, String amount, String billingId);

	List<String> getalllist();

	List<String> getBillinglist();

	List<String> getMaxBillid();

	void savpurchase(String supplierName, String discount, String paymentMode,
			String paidAmount, String totAmount);

	List<String> productList(String productname);

	List<String> getpurchasepriceList();

	String salesPriceList();

	String closingStockList();

	HashMap getlistname();

	String getsupplierName(String supplierName);

	HashMap getCustomerName();

	void addItem(AssetModel assetModel, double totalAmout, String openingBalance);

	void lessItem(AssetModel1 assetModel, Double totalAmount);

	String getnetprofit();

	String getclosingstock();

	void addLablites(Labilites labilites, Double totAmount);

	void lesslabItem(Labilites2 labilites);

	List<String> getitemlist(String productitem);

	void update(String id, String productType, String productItem,
			String quantity, String price, String amount, double totalAmount,
			String billid);

	List<String> updateStock(String productType, String productItem,
			String productCode, String price, String quantity, String totAmount);

	String getpurchasetotAmount();

	List<String> openingBalance(String lablitesDate12);

	List<String> netprofit(String lablitesDate12);

	List<String> addlablites(String lablitesDate12);

	List<String> lessLablites(String lablitesDate12);

	String gettotalA(String todate11, String fromDate1);

	List<String> getTotalA(String toDates, String toFroms);

	

	

}
