package com.serviceC;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daoI.SaleDaoI;
import com.model.CustomerModel;
import com.model.SalesBillingModel;
import com.serviceI.SaleServiceI;

@Service(value="SaleServiceI")
public class SaleServiceC implements SaleServiceI {

	@Autowired
	private SaleDaoI saleDaoI;
    @Transactional
	@Override
	public List<String> getmaxsaleid() {
	
		return saleDaoI.getmaxsaleid();
	}
    @Transactional
	@Override
	public void addsale(String totalAmount,String paidAmount,CustomerModel customerModel, List productTypelist,
			List productNamelist, List quntitylist, List pricelist,
			List amountlist, SalesBillingModel salesBillingModel) {
		// TODO Auto-generated method stub
    	saleDaoI.addsale(totalAmount,paidAmount,customerModel,productTypelist,productNamelist,quntitylist,pricelist,amountlist,salesBillingModel);
	}
    @Transactional
	@Override
	public List<String> getstocklist() {
	
		return saleDaoI.getstocklist();
	}
    @Transactional
	@Override
	public List<String> getsalesDetails() {
		// TODO Auto-generated method stub
		return saleDaoI.getsalesDetails();
	}
    @Transactional
	@Override
	public List<String> getLadgerList(String customerName) {
		// TODO Auto-generated method stub
		return saleDaoI.getLadgerList(customerName);
	}
   @Transactional
	@Override
	public void updatePayment(String totalAmount, String paidAmount,
			String remainingAmount, String customerName,HttpSession session) {
	saleDaoI.updatePayment(totalAmount,paidAmount,remainingAmount,customerName,session);
		
	}
   @Transactional
@Override
public List<String> getsalesid() {
	// TODO Auto-generated method stub
	return saleDaoI.getsalesid();
}
   @Transactional
@Override
public void sales(String productType, String productname, String quntity,
		String price, String amount, String totalAmount,String saleId,String productCode,String customerName) {
	// TODO Auto-generated method stub
	saleDaoI.sales(productType,productname,quntity,price,amount,totalAmount,saleId,productCode,customerName);
}
 @Transactional
@Override
public List<String> getproductCodelist() {
	// TODO Auto-generated method stub
	return saleDaoI.getproductCodelist();
}
@Transactional
@Override
public List<String> getproductcode(String productCode) {
	// TODO Auto-generated method stub
	return saleDaoI.getproductcode(productCode);
}
@Transactional
@Override
public List<String> getAllSalesList(String saleId) {
	// TODO Auto-generated method stub
	return saleDaoI.getAllSalesList(saleId);
}
@Transactional
@Override
public void salesSave(String discount, String paymentMode, String paidAmount,
		String totalAmount, String customerName) {
	saleDaoI.salesSave(discount,paymentMode,paidAmount,totalAmount,customerName);
	
}
@Transactional
@Override
public String getsaleid() {
	// TODO Auto-generated method stub
	return saleDaoI.getsaleid();
}
@Transactional
@Override
public List<String> getpurchasecode(String productCode) {
	// TODO Auto-generated method stub
	return saleDaoI.getpurchasecode(productCode);
}
@Transactional
@Override
public String getsalesAmount() {
	// TODO Auto-generated method stub
	return saleDaoI.getsalesAmount();
}
@Transactional
@Override
public String gettotalStockAmount() {
	// TODO Auto-generated method stub
	return saleDaoI.gettotalStockAmount();
}
@Transactional
@Override
public void updateSale(String salesid, String totAmount, String customerName,
		String productCode, String productType, String productItem,
		String quantity, String price, String amount,String billid) {
	// TODO Auto-generated method stub
	saleDaoI.updateSale(salesid,totAmount,customerName,productCode,productType,productItem,quantity,price,amount,billid);
}
}
