package com.daoC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daoI.ProductDaoI;
import com.model.BillingModel;
import com.model.NetProfit;
import com.model.ProductModel;
import com.model.PurchaseLedgerModel;
import com.model.Sub_ProductModel;
import com.model.SuplierModel;

@Repository(value="ProductDaoI")
public class ProductDaoC implements ProductDaoI {
	@Autowired
	SessionFactory sessionFactory;
	List<String> productlist=new ArrayList<String>();
	private HashMap Productnamelistss=new HashMap<>();
	private List<String> getMaxId=new ArrayList<String>();
	private List<String> getProductMaxId=new ArrayList<String>();
	private List<String> getSubProductList=new ArrayList<String>();
	private List<String> getSupplierlist=new ArrayList<String>();
	@Override
	public void saveproduct(ProductModel productmodel) {
		// TODO Auto-generated method stub
		
		
		
		this.sessionFactory.getCurrentSession().save(productmodel);
	}
 
	@Override
	public void updateproduct(ProductModel productmodel) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(productmodel);
	}

	@Override
	public void deleteproduct(ProductModel productmodel) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(productmodel);
	}

	@Override
	public List<String> getproductlist() {
		// TODO Auto-generated method stub
		
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM productmodel");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		productlist=query.list();
		return productlist;
	}

	@Override
	public List<String> getmaxproduct() {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("select ifnull(max(productid),0)+1 as productid from productmodel");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		getMaxId=query.list();
		return getMaxId;
	}

	@Override
	public HashMap getsubproductlist() {
		List<HashMap>listproduct =new ArrayList<>();
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT productid,productName FROM productmodel ");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		listproduct=query.list();
		Productnamelistss.clear();
		for(HashMap l :listproduct)
		{
			Productnamelistss.put(l.get("productid"),l.get("productName"));
			
		}
		
		return Productnamelistss;
	}

	@Override
	public List<String> getmaxid() {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(subProductid),0)+1 as subProductid FROM sub_productmodel");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		getProductMaxId=query.list();
		return getProductMaxId;
	}

	@Override
	public void savesubproduct(Sub_ProductModel sbProductModel) {
		// TODO Auto-generated method stub
		String code=sbProductModel.getCodeSub();
		System.out.println("code"+code+"codes");
		String codes=code.replaceAll(" ","" );
		System.out.println("afterre"+codes+"afterre");
		sbProductModel.setCodeSub(codes);
		this.sessionFactory.getCurrentSession().save(sbProductModel);
	}

	@Override
	public void updatesubproduct(Sub_ProductModel sbProductModel) {
		String code=sbProductModel.getCodeSub();
		System.out.println("code"+code+"codes");
		String codes=code.replaceAll(" ","" );
		System.out.println("afterre"+codes+"afterre");
		sbProductModel.setCodeSub(codes);
		this.sessionFactory.getCurrentSession().update(sbProductModel);
	}

	@Override
	public void deletesubproduct(Sub_ProductModel sbProductModel) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(sbProductModel);
	}

	@Override
	public List<String> getsubproduct() {
	SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT e.codeSub,e.SubProductName,e.subProductid,p.productName FROM sub_productmodel e inner join productmodel p on e.productid=p.productid");
	query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	getSubProductList=query.list();
	System.out.println("sssjojweofdokowejfojf"+getSubProductList);
		return getSubProductList;
	}

	@Override
	public List<String> getproductid(String productname) {
		List<String> getproductid=new ArrayList<String>();
	SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT productid FROM productmodel where productName='"+productname+"'");
	query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	getproductid=query.list();
		return getproductid;
	}

	@Override
	public List<String> getidlist() {
		List<String> getMaxSupplerlist=new ArrayList<String>();
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(suplierid),0)+1 as suplierid FROM supliermodel ");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		getMaxSupplerlist =query.list();
		
		return getMaxSupplerlist;
	}

	@Override
	public void addsupplier(SuplierModel suplierModel) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(suplierModel);
	}

	@Override
	public void deletesupplier(SuplierModel suplierModel) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(suplierModel);
	}

	@Override
	public void updatesupplier(SuplierModel suplierModel) {
		// TODO Auto-generated method stub
		String suppliername=suplierModel.getSupliername();
		String supplierphone=suplierModel.getPhoneNumber();
		String supplierAddress=suplierModel.getSuplierAddress();
       int supplierid=suplierModel.getSuplierid();
       
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery(" update supliermodel set Supliername='"+suppliername+"',SuplierAddress='"+supplierAddress+"',PhoneNumber='"+supplierphone+"' where suplierid='"+supplierid+"'");
		query.executeUpdate();
	}

	@Override
	public List<String> getsupplierlist() {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM supliermodel");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		getSupplierlist=query.list();
		return getSupplierlist;
	}

	@Override
	public void addpurchaseLedger(HttpServletRequest req,
			PurchaseLedgerModel purchaseLedgerModel, BillingModel billingModel) {
		  java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
			String currentTime = sdf.format(dt);
			
		 String billid=req.getParameter("billid");
   	     String supplierName=req.getParameter("SuplierModel.SuplierName");
   	     String totalA=req.getParameter("totalAmount");
   	     String paidAmount=req.getParameter("paidAmount");
   	     String remainingAmount=req.getParameter("remainingAmount");
   	     Double pAmount=Double.parseDouble(paidAmount); 
   	     Double totAmount=Double.parseDouble(totalA);
   	     Integer billno=Integer.parseInt(billid);
   	     Double remain=Double.parseDouble(remainingAmount);
   	  SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("update billingmodel set paidAmount='"+pAmount+"',remainingAmount='"+remain+"' where bilid='"+billno+"'");
   	  query.executeUpdate();
   	  SQLQuery query1=this.sessionFactory.getCurrentSession().createSQLQuery("insert into purchaseledgermodel(ledgerDate, paidAmount, bilid,totalAmount) values('"+currentTime+"','"+pAmount+"','"+billno+"','"+totAmount+"')");
   	  query1.executeUpdate();
		
	}

	@Override
	public void saveNetProfit(NetProfit netprofit) {
		sessionFactory.getCurrentSession().save(netprofit);
		
	}

	@Override
	public List<String> getpurchasebalance(String todate, String fromdate) {
			
			SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT sum(amount) as price FROM purchasemodel  where purdate between '"+todate+"' and '"+fromdate+"'");
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List <String>getpurchasebalance=new ArrayList<>();
			getpurchasebalance=query.list();
				return getpurchasebalance;
			
		
	
	}

	@Override
	public List<String> getsalesBalance(String todate, String fromdate) {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(amounts),0) as amount FROM salesmodel  where saledate between '"+todate+"' and '"+fromdate+"' ;");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		return query.list();
	}

	@Override
	public List<String> getClosing(String todate, String fromdate) {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(ToTalAmount),0)  asamounta FROM stockmodel  where  puRDate between '"+todate+"' and '"+fromdate+"'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public List<NetProfit> gettotalAmount(String toDate, String fromdate) {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(netProfit),0) as netProfit ,ifnull(sum(closingbalance),0) as closingbalance  FROM netprofit  where  todate between '"+fromdate+"' and '"+toDate+"';");
		query.setResultTransformer(Transformers.aliasToBean(NetProfit.class));
		
		return query.list();
	}

	@Override
	public String gettotalAmounts(String toDate, String fromdate) {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(addAmount),0)   FROM assetmodel  where assetDate between '"+fromdate+"' and '"+toDate+"'");
		double ds=(double) query.uniqueResult();
		SQLQuery query1=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(lessAmount),0) FROM assetmodel1 where  assetDate between '"+fromdate+"' and '"+toDate+"'");
		double ds2=(double) query1.uniqueResult();
		double tot=ds-ds2;
		System.out.println("tot"+tot);
		String getTotolAmount=String.valueOf(tot);
		return getTotolAmount;
	}

}
