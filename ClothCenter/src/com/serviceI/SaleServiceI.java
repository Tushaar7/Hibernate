package com.serviceI;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.model.CustomerModel;
import com.model.SalesBillingModel;

public interface SaleServiceI {

	List<String> getmaxsaleid();

	void addsale(String totalAmount, String paidAmount, CustomerModel customerModel, List productTypelist,
			List productNamelist, List quntitylist, List pricelist,
			List amountlist, SalesBillingModel salesBillingModel);

	List<String> getstocklist();

	List<String> getsalesDetails();

	List<String> getLadgerList(String customerName);

	void updatePayment(String totalAmount, String paidAmount,
			String remainingAmount, String customerName, HttpSession session);

	List<String> getsalesid();

	void sales(String productType, String productname, String quntity,
			String price, String amount, String totalAmount, String saleId, String productCode, String customerName);

	List<String> getproductCodelist();

	List<String> getproductcode(String productCode);

	List<String> getAllSalesList(String saleId);

	void salesSave(String discount, String paymentMode, String paidAmount,
			String totalAmount, String customerName);

	String getsaleid();

	List<String> getpurchasecode(String productCode);

	String getsalesAmount();

	String gettotalStockAmount();

	void updateSale(String salesid, String totAmount, String customerName,
			String productCode, String productType, String productItem,
			String quantity, String price, String amount,String billid);




}
