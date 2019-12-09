package com.serviceC;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daoI.ProductDaoI;
import com.model.BillingModel;
import com.model.NetProfit;
import com.model.ProductModel;
import com.model.PurchaseLedgerModel;
import com.model.Sub_ProductModel;
import com.model.SuplierModel;
import com.serviceI.ProductServiceI;
@Service(value="ProductServiceI")
public class ProductServiceC implements ProductServiceI {
	@Autowired
	private ProductDaoI productDaoI;
     @Transactional
	@Override
	public void saveproduct(ProductModel productmodel) {
		// TODO Auto-generated method stub
		productDaoI.saveproduct(productmodel);
	}
     @Transactional
	@Override
	public void updateproduct(ProductModel productmodel) {
		// TODO Auto-generated method stub
		productDaoI.updateproduct(productmodel);
	}
     @Transactional
	@Override
	public void deleteproduct(ProductModel productmodel) {
		// TODO Auto-generated method stub
		productDaoI.deleteproduct(productmodel);
	}
     @Transactional
	@Override
	public List<String> getproductlist() {
		// TODO Auto-generated method stub
		return productDaoI.getproductlist();
	}
     @Transactional
	@Override
	public List<String> getmaxproduct() {
		// TODO Auto-generated method stub
		return productDaoI.getmaxproduct();
	}
     @Transactional
	@Override
	public HashMap getsubproductlist() {
		// TODO Auto-generated method stub
		return productDaoI.getsubproductlist();
	}
     @Transactional
	@Override
	public List<String> getmaxid() {
		// TODO Auto-generated method stub
		return productDaoI.getmaxid();
	}
    @Transactional 
	@Override
	public void savesubproduct(Sub_ProductModel sbProductModel) {
		// TODO Auto-generated method stub
    	productDaoI.savesubproduct(sbProductModel);
	}
    @Transactional
	@Override
	public void updatesubproduct(Sub_ProductModel sbProductModel) {
		// TODO Auto-generated method stub
    	productDaoI.updatesubproduct(sbProductModel);
	}
    @Transactional
	@Override
	public void deletesubproduct(Sub_ProductModel sbProductModel) {
		// TODO Auto-generated method stub
    	productDaoI.deletesubproduct(sbProductModel);
	}
    @Transactional
	@Override
	public List<String> getsubproduct() {
		// TODO Auto-generated method stub
		return productDaoI.getsubproduct();
	}
    @Transactional
	@Override
	public List<String> getproductid(String productname) {
		// TODO Auto-generated method stub
		return productDaoI.getproductid(productname);
	}
    @Transactional
	@Override
	public List<String> getidlist() {
		// TODO Auto-generated method stub
		return productDaoI.getidlist();
	}
    @Transactional
	@Override
	public void addsupplier(SuplierModel suplierModel) {
		// TODO Auto-generated method stub
    	productDaoI.addsupplier(suplierModel);
	}
    @Transactional
	@Override
	public void deletesupplier(SuplierModel suplierModel) {
		// TODO Auto-generated method stub
    	productDaoI.deletesupplier(suplierModel);
	}
    @Transactional
	@Override
	public void updatesupplier(SuplierModel suplierModel) {
		// TODO Auto-generated method stub
		productDaoI.updatesupplier(suplierModel);
	}
    @Transactional
	@Override
	public List<String> getsupplierlist() {
		// TODO Auto-generated method stub
		return productDaoI.getsupplierlist();
	}
    @Transactional
	@Override
	public void addpurchaseLedger(HttpServletRequest req,
			PurchaseLedgerModel purchaseLedgerModel, BillingModel billingModel) {
		// TODO Auto-generated method stub
    	productDaoI.addpurchaseLedger(req,purchaseLedgerModel,billingModel);
	}
    @Transactional
	@Override
	public void saveNetProfit(NetProfit netprofit) {
		// TODO Auto-generated method stub
		productDaoI.saveNetProfit(netprofit);
	}
    @Transactional
	@Override
	public List<String> getpurchasebalance(String todate, String fromdate) {
		// TODO Auto-generated method stub
		return productDaoI.getpurchasebalance(todate,fromdate);
	}
    
    @Transactional
	@Override
	public List<String> getsalesBalance(String todate, String fromdate) {
		// TODO Auto-generated method stub
		return productDaoI.getsalesBalance(todate,fromdate);
	}
    @Transactional
	@Override
	public List<String> getClosing(String todate, String fromdate) {
	
		return productDaoI.getClosing(todate,fromdate);
	}
    @Transactional
	@Override
	public List<NetProfit> gettotalAmount(String toDate, String fromdate) {
		
		return productDaoI.gettotalAmount(toDate,fromdate);
	}
    @Transactional
	@Override
	public String gettotalAmounts(String toDate, String fromdate) {
		// TODO Auto-generated method stub
		return productDaoI.gettotalAmounts(toDate,fromdate);
	}
   
}
