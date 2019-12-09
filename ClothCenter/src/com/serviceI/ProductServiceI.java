package com.serviceI;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.model.BillingModel;
import com.model.NetProfit;
import com.model.ProductModel;
import com.model.PurchaseLedgerModel;
import com.model.Sub_ProductModel;
import com.model.SuplierModel;

public interface ProductServiceI {

	void saveproduct(ProductModel productmodel);

	void updateproduct(ProductModel productmodel);

	void deleteproduct(ProductModel productmodel);

	List<String> getproductlist();



	List<String> getmaxproduct();

	HashMap getsubproductlist();

	List<String> getmaxid();

	void savesubproduct(Sub_ProductModel sbProductModel);

	void updatesubproduct(Sub_ProductModel sbProductModel);

	void deletesubproduct(Sub_ProductModel sbProductModel);

	List<String> getsubproduct();

	List<String> getproductid(String productname);

	List<String> getidlist();

	void addsupplier(SuplierModel suplierModel);

	void deletesupplier(SuplierModel suplierModel);

	void updatesupplier(SuplierModel suplierModel);

	List<String> getsupplierlist();

	void addpurchaseLedger(HttpServletRequest req,
			PurchaseLedgerModel purchaseLedgerModel, BillingModel billingModel);
	      void saveNetProfit(NetProfit netprofit);
	    List<String> getpurchasebalance(String todate, String fromdate);
	   List<String> getsalesBalance(String todate, String fromdate);

	List<String> getClosing(String todate, String fromdate);

	List<NetProfit> gettotalAmount(String toDate, String fromdate);

	String gettotalAmounts(String toDate, String fromdate);


	



	



}
