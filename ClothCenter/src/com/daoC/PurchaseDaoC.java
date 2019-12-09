package com.daoC;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.validator.constraints.Length;
import org.mvel2.ast.DoUntilNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daoI.PurchaseDaoI;
import com.model.AssetModel;
import com.model.AssetModel1;
import com.model.BillingModel;
import com.model.Labilites;
import com.model.Labilites2;
import com.model.PurchaseLedgerModel;
import com.model.PurchaseModel;
import com.model.StockModel;
import com.model.SuplierModel;
import com.serviceI.PurchaseServiceI;

@Repository(value="PurchaseDaoI")
public class PurchaseDaoC implements PurchaseDaoI {
	@Autowired
	SessionFactory sessionfactory;
	private HashMap subproductlist=new HashMap<>();
	private List<String> PurchaseDetailslist=new ArrayList<String>();
	private List<String> purchaseLedgerlist=new ArrayList<String>();
 	private List<String> getPurchaseLedgerlist=new ArrayList<String>();
 	private HashMap getladgerNamelist=new HashMap<>();
 	private HashMap getladgerCustomerNamelist=new HashMap<>();
	private List<String>getTotalAmount=new ArrayList<String>();
  	private List<String> getReminingAmount=new ArrayList<String>();
	private List<String>getSalesTotalA=new ArrayList<>();
  	private List<String>getSalesPaidA=new ArrayList<>();
	private List<String>getAllProductList=new ArrayList<String>();
  	private List<String>billingidList=new ArrayList<String>();
 	private List<String> productnamelist=new ArrayList<>();
	private List<String> purchasesPrice=new ArrayList<>();
  	private List<String>salesPrice=new ArrayList<>();
  	private List<String>closingStocklist=new ArrayList<>();

	@Override
	public void savepurchase(PurchaseModel purchaseModel, SuplierModel supliermodel,
			List productTypelist, List productNamelist, List quntitylist,
			List pricelist, List amountlist, BillingModel billingmodel,PurchaseLedgerModel purchaseLedgerModel
			) {
		  List<StockModel> list=new ArrayList<>();
	
		  String name=supliermodel.getSupliername();
			String paymentMode=billingmodel.getPaymentMode();
		/*SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT suplierid FROM supliermodel where Supliername='"+name+"'");*/
		
		  int  suplierid=Integer.parseInt(name);
		  java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
			String currentTime = sdf.format(dt);
			billingmodel.setDate(currentTime);
			   supliermodel.setSuplierid(suplierid);
				Double paidamount=billingmodel.getPaidAmount();
			   Double toAmount=billingmodel.getTotalAmount();
				billingmodel.setSuplierModel(supliermodel);
				billingmodel.setRemainingAmount((toAmount)-(paidamount));
			
				this.sessionfactory.getCurrentSession().save(billingmodel);
			  SQLQuery query3=this.sessionfactory.getCurrentSession().createSQLQuery("select max(bilid) from billingmodel ");
			  int billId=(int) query3.uniqueResult();
			 
			Double paidAmount= purchaseLedgerModel.getPaidAmount();
			Double totAmount=purchaseLedgerModel.getTotalAmount();
			SQLQuery query8=this.sessionfactory.getCurrentSession().createSQLQuery("insert into purchaseledgermodel (ledgerDate, paidAmount, totalAmount, bilid) values('"+currentTime+"','"+paidAmount+"',('"+totAmount+"')-('"+paidAmount+"'),'"+billId+"')");
			query8.executeUpdate();
			  for(int i=0;i<productTypelist.size();i++)
			  {
				  
				  SQLQuery query2=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT id ,quntity FROM stockmodel where productid='"+productTypelist.get(i)+"' and subProductid='"+productNamelist.get(i)+"'");
				  query2.setResultTransformer(Transformers.aliasToBean(StockModel.class));
				  list=query2.list();
				 
				 if(list.size()==0)
				 {
					 System.out.println("elseBlock");
					 SQLQuery query5=this.sessionfactory.getCurrentSession().createSQLQuery("insert into  stockmodel (quntity, productid, subProductid,ToTalAmount,price) values('"+quntitylist.get(i)+"','"+productTypelist.get(i)+"','"+productNamelist.get(i)+"',('"+quntitylist.get(i)+"'*'"+pricelist.get(i)+"'),'"+pricelist.get(i)+"')");
					 query5.executeUpdate();
					 SQLQuery query7=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT max(id) FROM stockmodel s");
					 int stock=(int) query7.uniqueResult();
					 SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("insert into purchasemodel(Price, Quntity, amount, productid, subProductid,bilid,id) values('"+pricelist.get(i)+"','"+quntitylist.get(i)+"','"+amountlist.get(i)+"','"+productTypelist.get(i)+"','"+productNamelist.get(i)+"','"+billId+"','"+stock+"')  ");
					  query1.executeUpdate();
				 }
				 else	 
				 {
					 System.out.println("ifblock");	
					 String quntity=list.get(0).getQuntity();
					   int stockid=list.get(0).getId();
					   SQLQuery query7=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT max(id) FROM stockmodel s");
						 int stock=(int) query7.uniqueResult();
						 SQLQuery query4=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel  set quntity=('"+quntity+"'+'"+quntitylist.get(i)+"'),ToTalAmount=('"+quntitylist.get(i)+"'*'"+pricelist.get(i)+"'),price='"+pricelist.get(i)+"' where id='"+stockid+"' ");
						 query4.executeUpdate();
						 SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("insert into purchasemodel(Price, Quntity, amount, productid, subProductid,bilid,id) values('"+pricelist.get(i)+"','"+quntitylist.get(i)+"','"+amountlist.get(i)+"','"+productTypelist.get(i)+"','"+productNamelist.get(i)+"','"+billId+"','"+stock+"')  ");
						 query1.executeUpdate();
				 }
				Double totamount=billingmodel.getTotalAmount();
				Double pAmounts=billingmodel.getPaidAmount();
				 if(paymentMode.equals("Credit"))
				 {
						SQLQuery query10=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(totalAmount) FROM supliermodel  where suplierid='"+suplierid+"'");
						Double totalAmount=(Double) query10.uniqueResult();
						SQLQuery query13=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(paidAmount) FROM supliermodel  where suplierid='"+suplierid+"'");
						Double Amunt=(Double) query13.uniqueResult();
						
						SQLQuery query16=this.sessionfactory.getCurrentSession().createSQLQuery("update  supliermodel set totalAmount=('"+totamount+"')+('"+totalAmount+"'),paidAmount=('"+pAmounts+"')+('"+Amunt+"')  where suplierid='"+suplierid+"'");
					    query16.executeUpdate();
						
					    SQLQuery query17=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(paidAmount) FROM supliermodel  where suplierid='"+suplierid+"'");
						Double updatepaidAmount=(Double) query17.uniqueResult();
						
						
						SQLQuery query14=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(totalAmount) FROM supliermodel  where suplierid='"+suplierid+"'");
						Double updatedtotal=(Double) query14.uniqueResult();
						
						
						SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("update  supliermodel   set remainingAmount=('"+updatedtotal+"')-('"+updatepaidAmount+"') where suplierid='"+suplierid+"'");
						query.executeUpdate();
				 }
				 
			  }
  
		
	}

	@Override
	public List<String> getmaxproductid() {
		 List<String> getMaxpurchaseid=new ArrayList<String>();
		 SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(Purchaseid),0)+1 as Purchaseid  FROM purchasemodel");
		 query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 getMaxpurchaseid=query.list();
		return getMaxpurchaseid;
	}

	@Override
	public HashMap getsubproductid() {
	List <HashMap>listproductid =new ArrayList<>();
	SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT subProductid, SubProductName FROM sub_productmodel ");
	query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	listproductid=query.list();
	subproductlist.clear();
	for(HashMap l:listproductid)
	{
		subproductlist.put(l.get("subProductid"),l.get("SubProductName"));
	}
		return subproductlist;
	}

	@Override
	public List<String> getpurchaselist() {
	SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT b.bilid,sub.SubProductName, b.Date,b.PaymentMode,pr.productName,b.TotalAmount,p.Price, p.Quntity, p.amount,su.Supliername FROM purchasemodel p inner join billingmodel b on p.bilid=b.bilid inner join supliermodel su on su.suplierid=b.suplierid inner join productmodel pr on pr.productid=p.productid inner join sub_productmodel sub on sub.subProductid=p.subProductid group by b.bilid ");
	query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	PurchaseDetailslist=query.list();
		return PurchaseDetailslist;
	}

	@Override
	public List<String> getpurchaseLedger(String ledgerType,String ledgername,String customerName) {
		
			SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT Supliername FROM supliermodel s where suplierid='"+ledgername+"'");
		    String name=(String) query1.uniqueResult();
		  
			SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT st.purchaseCode, b.Date, b.remainingAmount,b.bilid,sub.SubProductName,b.PaymentMode,pr.productName,b.TotalAmount,p.Price, p.Quntity, p.amount,su.Supliername FROM purchasemodel p inner join billingmodel b on p.bilid=b.bilid inner join supliermodel su on su.suplierid=b.suplierid inner join productmodel pr on pr.productid=p.productid inner join sub_productmodel sub on sub.subProductid=p.subProductid inner join stockmodel st on st.id=p.id where b.PaymentMode='Credit'and su.Supliername ='"+name+"' group by b.bilid ");
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			purchaseLedgerlist=query.list();
				return purchaseLedgerlist;
		}


		/*	SQLQuery query2=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT customername FROM customermodel  where customerid='"+customerName+"'");
			String customername=(String)query2.uniqueResult();
	     SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT s.salebillinngid,c.customername,s.totalAmount,s.paidAmount,s.saledate FROM salesbillingmodel s inner join salesmodel sa on s.salebillinngid=sa.salebillinngid inner join customermodel c on c.customerid=s.customerid where c.customername='"+customername+"' group by s.salebillinngid");
	     query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	     purchaseLedgerlist=query.list();
		 return purchaseLedgerlist;
		 //SELECT * FROM salesbillingmodel s inner join salesmodel sa on s.salebillinngid=sa.salebillinngid inner join customermodel c on c.customerid=s.customerid ;
		}*/
	

	@Override
	public List<String> getpurchaseLedgerlist(String billid) {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT b.remainingAmount,b.bilid, pl.paidAmount,b.TotalAmount,su.Supliername FROM purchasemodel p inner join billingmodel b on p.bilid=b.bilid inner join supliermodel su on su.suplierid=b.suplierid inner join productmodel pr on pr.productid=p.productid inner join sub_productmodel sub on sub.subProductid=p.subProductid inner join purchaseledgermodel pl on b.bilid=pl.bilid where b.PaymentMode='Credit'and b.bilid='"+billid+"' group by b.bilid ");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		getPurchaseLedgerlist=query.list();
		return getPurchaseLedgerlist;
	}

	@Override
	public HashMap getladgername() {
           List<HashMap> getledger=new ArrayList<>();
         
         
        	   SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT suplierid,Supliername FROM supliermodel ");
        		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        		
        		getledger=query.list();
        		getladgerNamelist.clear();
        		for(HashMap l: getledger)
        		{
        			getladgerNamelist.put(l.get("suplierid"),l.get("Supliername"));
        		} 
        
	       System.out.println("getladgerNamelist"+getladgerNamelist);
		return getladgerNamelist;
	}

	@Override
	public HashMap getCustomerNamelist() {
	List<HashMap> getcustomerlist=new ArrayList<>();
	SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT * FROM customermodel where  remainingAmount !=0 ");
	query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	getcustomerlist=query.list();
	getladgerCustomerNamelist.clear();
	for(HashMap l:getcustomerlist)
	{
		getladgerCustomerNamelist.put(l.get("customerid"), l.get("customername"));
	}
		return getladgerCustomerNamelist;
	}

	@Override
	public String gettotalAmount(String supplierName) {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(totalAmount) as total FROM supliermodel where suplierid='"+supplierName+"'");
	double	getTotalAmount1=(double) query.uniqueResult();
	String getTotalAmount=String.valueOf(getTotalAmount1);
	String gettotal=getTotalAmount.substring(getTotalAmount.indexOf("."));
	if(gettotal.length()>=3)
	{
		getTotalAmount=getTotalAmount.substring(0,getTotalAmount.indexOf('.')+3);
	}
		return getTotalAmount;
	}

	@Override
	public String getRemainingAmounts(String supplierName) {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(remainingAmount) as remaining FROM supliermodel where suplierid='"+supplierName+"'");
		
		double getReminingAmount1=(double) query.uniqueResult();
		String getReminingAmount=String.valueOf(getReminingAmount1);
		String gettoal=getReminingAmount.substring(getReminingAmount.indexOf("."));
		if(gettoal.length()>=3)
		{
			getReminingAmount=getReminingAmount.substring(0,getReminingAmount.indexOf('.')+3);
			
		}
		
		return getReminingAmount;
	}

	@Override
	public void paymentMode(String supplierName, String totalAmount,
			String remainingAmount, String paidAmount,HttpSession session) {
		Double paidA =Double.parseDouble(paidAmount);
		Double remainingA=Double.parseDouble(remainingAmount);
		Double totA=Double.parseDouble(totalAmount);
		java.util.Date d=new java.util.Date();
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
		String currentDate=sdf.format(d);
		
		SQLQuery query2=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT paidAmount FROM supliermodel  where suplierid='"+supplierName+"'");
		  Double remainPaid =(Double)query2.uniqueResult();
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("update supliermodel set paidAmount=('"+paidA+"')+('"+remainPaid+"'),remainingAmount='"+remainingA+"' where suplierid='"+supplierName+"'");
		query.executeUpdate();
		SQLQuery query12=this.sessionfactory.getCurrentSession().createSQLQuery("insert into purchaseledgermodel (ledgerDate, paidAmount, totalAmount, suplierid,remainingAmount) values('"+currentDate+"','"+paidA+"','"+totA+"','"+supplierName+"','"+remainingA+"') ");
		query12.executeUpdate();
		SQLQuery query3=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT Supliername FROM supliermodel  where suplierid='"+supplierName+"'");
		  String name =(String)query3.uniqueResult();
		  session.setAttribute("suppplerName", name);
		
	}

	@Override
	public String getSalesTotalAmount(String customerN) {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT totalAmount  FROM customermodel where customerid='"+customerN+"'");
	double	getSalesTotalA1=(double) query.uniqueResult();
	String getSalesTotalA=String.valueOf(getSalesTotalA1);
	String salestot=getSalesTotalA.substring(getSalesTotalA.indexOf("."));
	if(salestot.length()>=3)
	{
		getSalesTotalA=getSalesTotalA.substring(0,getSalesTotalA.indexOf('.')+3);
	}
		return getSalesTotalA;
	}

	@Override
	public String getSalesPaidAmount(String customerN) {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT remainingAmount  FROM customermodel where customerid='"+customerN+"'");
		double getSalesPaidA2=(double) query.uniqueResult();
		String getSalesPaidA=String.valueOf(getSalesPaidA2);
		String salespaid1=getSalesPaidA.substring(getSalesPaidA.indexOf("."));
		if(salespaid1.length()>=3)
		{
			getSalesPaidA=getSalesPaidA.substring(0,getSalesPaidA.indexOf('.')+3);
		}
		return getSalesPaidA;
	}

	@Override
	public void saveItem(String productType, String productItem,
		String quntity, String price, String amount,String billingId) {
		List<String> list=new ArrayList<String>();
		
	   
	    System.out.println("ssssssssssssssssssssssssss");
	    Integer productT=Integer.parseInt(productType);
	    SQLQuery query19=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT subProductid FROM sub_productmodel where productid='"+productT+"' and SubProductName='"+productItem+"'");
	    int productI=(int) query19.uniqueResult();
	    SQLQuery query25=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT codeSub FROM sub_productmodel where productid='"+productT+"' and SubProductName='"+productItem+"'");
	    String productCode12=(String) query25.uniqueResult();
	    String productCode=productType+productCode12+price+quntity;
	    System.out.println("producttype"+productType+"product");
	    System.out.println("itemcode"+productCode12+"item");
	    System.out.println("price"+price+"price");
	    System.out.println("quntity"+quntity+"quantiy");
	    System.out.println("product code which generated "+productCode);
	    productCode=productCode.replaceAll(" ", "");
	    System.out.println("After replace product code"+productCode);
	    Double quntityI=Double.parseDouble(quntity);
	    Double priceI=Double.parseDouble(price);
	    Double amountI=Double.parseDouble(amount);
	    java.util.Date d=new java.util.Date();
	    java.text.SimpleDateFormat sd=new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
	    String cureentdate=sd.format(d);
	    SimpleDateFormat sds=new SimpleDateFormat("yyyy/MM/dd HH:mm");
	    String purdate=sds.format(d);
	    
	    SQLQuery query18=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(bilid),0) as bilid FROM billingmodel");
	    BigInteger maxbil=(BigInteger) query18.uniqueResult();
	    
	    String maxbillid=String.valueOf(maxbil);
	    System.out.println("maxxxxxxxxxxxid"+maxbillid);
	    System.out.println("incrementid"+billingId);
	    if(maxbillid.equals(billingId))
	    {
	    	 

		    SQLQuery query3=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT id FROM stockmodel where  productid='"+productType+"' and subProductid='"+productI+"' and price='"+priceI+"'");
		    list=query3.list();
		  if(list.size()==0)
		  {
			  
			  SQLQuery query44=this.sessionfactory.getCurrentSession().createSQLQuery("insert into stockmodel  (ToTalAmount, price, quntity, productid, subProductid, purchaseCode,puRDate) values(('"+priceI+"')*('"+quntity+"'),'"+priceI+"','"+quntity+"','"+productT+"','"+productI+"','"+productCode+"','"+purdate+"')");
			  query44.executeUpdate();
			  /*SQLQuery query5=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT id FROM stockmodel where  productid='"+productType+"' and subProductid='"+productItem+"' and purchaseCode='"+productCode+"'");
			   int stockid =(int) query5.uniqueResult();*/
			   SQLQuery query5=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(id),0) as id FROM stockmodel");
			   BigInteger stockid =(BigInteger) query5.uniqueResult();
			  SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("select max(bilid) as billid  from billingmodel");
			    int billid = (int) query.uniqueResult();
			  SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("insert into purchasemodel(Price, Quntity, amount, bilid, productid, id, subProductid, productCode,purdate) values('"+priceI+"','"+quntityI+"','"+amountI+"','"+billid+"','"+productT+"','"+stockid+"','"+productI+"','"+productCode+"','"+purdate+"')");
			    query1.executeUpdate();
			    
		  }
		  else
		  {
			    System.out.println("sssssssssssssssssaaaaaaaaaaaaaaaaaa"+productI);
			    SQLQuery query7=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT id FROM stockmodel where  productid='"+productType+"' and subProductid='"+productI+"' and price='"+priceI+"'");
			    int stockid=(int) query7.uniqueResult();
			    SQLQuery query12=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(price) FROM stockmodel where id='"+stockid+"'");
			    Double priceS=(Double) query12.uniqueResult();
			    
			    SQLQuery query13=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(quntity) FROM stockmodel where id='"+stockid+"'");
			    Double quntityS=(Double) query13.uniqueResult();
			    
			    SQLQuery query14=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(ToTalAmount) FROM stockmodel where id='"+stockid+"'");
				Double amountS=(Double) query14.uniqueResult();
				
			    SQLQuery query8=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel set quntity=('"+quntityS+"')+('"+quntityI+"'),ToTalAmount=('"+amountS+"')+('"+amountI+"'),puRDate='"+purdate+"' where id='"+stockid+"' ");
			    query8.executeUpdate();
			    SQLQuery query21=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(quntity) FROM stockmodel where id='"+stockid+"'");
			    Double quntity12=(Double) query21.uniqueResult();
			    NumberFormat nf=NumberFormat.getIntegerInstance();
				 String pris=  nf.format(quntity12);
				 int price1 = Integer.parseInt(pris.replaceAll(",", ""));
			    String productCode1=productType+productCode12+price+price1;
			    SQLQuery query32=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel set purchaseCode='"+productCode1+"' where id='"+stockid+"' ");
			    query32.executeUpdate();
				  SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT  ifnull(max(bilid),0) as bilid FROM billingmodel");
				    BigInteger billid = (BigInteger) query.uniqueResult();
			  SQLQuery query9=this.sessionfactory.getCurrentSession().createSQLQuery("insert into purchasemodel(Price, Quntity, amount, bilid, productid, id, subProductid, productCode,purdate) values('"+priceI+"','"+quntityI+"','"+amountI+"','"+billid+"','"+productT+"','"+stockid+"','"+productI+"','"+productCode+"','"+purdate+"')");
			    query9.executeUpdate();
			  
		  }
		   
	    }
	  
	    else
	    {
	    	System.out.println("qwe");
	    	 SQLQuery query6=this.sessionfactory.getCurrentSession().createSQLQuery("insert into billingmodel (Date, Discount, PaymentMode, TotalAmount, ledgerName, paidAmount, remainingAmount, suplierid, productCode) values('"+cureentdate+"','"+0+"','"+null+"','"+0+"','"+null+"','"+0+"','"+0+"','"+1+"','"+productCode+"') ");
			  query6.executeUpdate();
		    SQLQuery query3=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT id FROM stockmodel where  productid='"+productType+"' and subProductid='"+productI+"' and price='"+priceI+"'");
		    list=query3.list();
		  if(list.size()==0)
		  {
			  SQLQuery query42=this.sessionfactory.getCurrentSession().createSQLQuery("insert into stockmodel  (ToTalAmount, price, quntity, productid, subProductid, purchaseCode,puRDate) values('"+amountI+"','"+priceI+"','"+quntity+"','"+productT+"','"+productI+"','"+productCode+"','"+purdate+"')");
			  query42.executeUpdate();
			  SQLQuery query5=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(id),0) as id FROM stockmodel");
			   BigInteger stockid =(BigInteger) query5.uniqueResult();
			 
			  SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("select max(bilid) as billid  from billingmodel");
			    int billid = (int) query.uniqueResult();
			  SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("insert into purchasemodel(Price, Quntity, amount, bilid, productid, id, subProductid, productCode,purdate) values('"+priceI+"','"+quntityI+"','"+amountI+"','"+billid+"','"+productT+"','"+stockid+"','"+productI+"','"+productCode+"','"+purdate+"')");
			    query1.executeUpdate();
			    
		  }
		  else
		  {
			  System.out.println("sssssssssssssssssaaaaaaaaaaaaaaaaaa");
			 SQLQuery query7=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT id FROM stockmodel where  productid='"+productType+"' and subProductid='"+productI+"' and price='"+priceI+"' ");
			    int stockid=(int) query7.uniqueResult();
			    SQLQuery query12=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(price) FROM stockmodel where id='"+stockid+"'");
			    Double priceS=(Double) query12.uniqueResult();
			    
			    SQLQuery query13=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(quntity) FROM stockmodel where id='"+stockid+"'");
			    Double quntityS=(Double) query13.uniqueResult();
			    
			    SQLQuery query14=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(ToTalAmount) FROM stockmodel where id='"+stockid+"'");
				Double amountS=(Double) query14.uniqueResult();
			   
			    SQLQuery query8=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel set quntity=('"+quntityS+"')+('"+quntityI+"'),ToTalAmount=('"+amountS+"')+('"+amountI+"'),puRDate='"+purdate+"' where id='"+stockid+"' ");
			    query8.executeUpdate();
			    SQLQuery query21=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(quntity) FROM stockmodel where id='"+stockid+"'");
			    Double quntity12=(Double) query21.uniqueResult();
			    NumberFormat nf=NumberFormat.getIntegerInstance();
				 String pris=  nf.format(quntity12);
				 int price1 = Integer.parseInt(pris.replaceAll(",", ""));
			    String productCode1=productType+productCode12+price+price1;
			    SQLQuery query32=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel set purchaseCode='"+productCode1+"' where id='"+stockid+"' ");
			    query32.executeUpdate();
			   
				  SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("select max(bilid) as billid  from billingmodel");
				    int billid = (int) query.uniqueResult();
			  SQLQuery query9=this.sessionfactory.getCurrentSession().createSQLQuery("insert into purchasemodel(Price, Quntity, amount, bilid, productid, id, subProductid, productCode,purdate) values('"+priceI+"','"+quntityI+"','"+amountI+"','"+billid+"','"+productT+"','"+stockid+"','"+productI+"','"+productCode+"','"+purdate+"')");
			    query9.executeUpdate();
			  
		  }
		   
	    }
	  
	   
		
	}

	@Override
	public List<String> getalllist() {
		SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT max(bilid) as bilid FROM billingmodel b");
		int billid=(int)query1.uniqueResult();
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT pur.Purchaseid,pur.Price, pur.Quntity, pur.amount,st.purchaseCode,p.productName,s.SubProductName FROM purchasemodel pur inner join productmodel p  on pur.productid=p.productid inner join sub_productmodel s on s.subProductid=pur.subProductid inner join stockmodel st on pur.id=st.id  where bilid='"+billid+"'");
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		getAllProductList=query.list();
		return getAllProductList;
	}

	@Override
	public List<String> getBillinglist() {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(bilid),0)+1 as bilid FROM billingmodel b");
		billingidList=query.list();
		return billingidList;
	}

	@Override
	public List<String> getMaxBillid() {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT max(bilid) as bilid FROM billingmodel b");
		billingidList=query.list();
		return billingidList;
	}

	@Override
	public void savpurchase(String suplierid, String discount,
			String paymentMode, String paidAmount, String totAmount) {
		// TODO Auto-generated method stub
		Double discountA=Double.parseDouble(discount);
		Double paidA=Double.parseDouble(paidAmount);
		Double totalAmount=Double.parseDouble(totAmount);
		
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT max(bilid) as bill FROM billingmodel ");
		int billid =(int)query.uniqueResult();
		java.util.Date d=new java.util.Date();
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
		String curentDate=sdf.format(d);
		
		if(paymentMode.equals("Credit"))
		{
			
			if(paidA>0)
			{
				SQLQuery query2=this.sessionfactory.getCurrentSession().createSQLQuery("insert into  purchaseledgermodel (ledgerDate, paidAmount, totalAmount,suplierid,remainingAmount) values('"+curentDate+"','"+paidA+"','"+totalAmount+"','"+suplierid+"',('"+totalAmount+"')-('"+paidA+"'))");
				query2.executeUpdate();
			}
			SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("update  billingmodel set  Discount='"+discountA+"', PaymentMode='"+paymentMode+"', TotalAmount='"+totalAmount+"', paidAmount='"+paidA+"', suplierid='"+suplierid+"' where bilid='"+billid+"' ");
			query1.executeUpdate();
			
			 SQLQuery query10=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(totalAmount) FROM supliermodel  where suplierid='"+suplierid+"'");
			Double totalAmount1=(Double) query10.uniqueResult();
			SQLQuery query13=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(paidAmount) FROM supliermodel  where suplierid='"+suplierid+"'");
			Double Amunt=(Double) query13.uniqueResult();
			
			SQLQuery query16=this.sessionfactory.getCurrentSession().createSQLQuery("update  supliermodel set totalAmount=('"+totalAmount+"')+('"+totalAmount1+"'),paidAmount=('"+paidA+"')+('"+Amunt+"')  where suplierid='"+suplierid+"'");
		    query16.executeUpdate();
			
		    SQLQuery query17=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(paidAmount) FROM supliermodel  where suplierid='"+suplierid+"'");
			Double updatepaidAmount=(Double) query17.uniqueResult();
			
			
			SQLQuery query14=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(totalAmount) FROM supliermodel  where suplierid='"+suplierid+"'");
			Double updatedtotal=(Double) query14.uniqueResult();
			
			
			SQLQuery query22=this.sessionfactory.getCurrentSession().createSQLQuery("update  supliermodel   set remainingAmount=('"+updatedtotal+"')-('"+updatepaidAmount+"') where suplierid='"+suplierid+"'");
			query22.executeUpdate();
		}
		else
		{
			SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("update  billingmodel set  Discount='"+discountA+"', PaymentMode='"+paymentMode+"', TotalAmount='"+totalAmount+"', paidAmount='"+paidA+"', suplierid='"+suplierid+"' where bilid='"+billid+"' ");
			query1.executeUpdate();
		}
		
		
	}

	@Override
	public List<String> productList(String productname) {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT SubProductName FROM sub_productmodel s where productid='"+productname+"'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		productnamelist=query.list();
		return productnamelist;
	}

	@Override
	public List<String> getpurchasepriceList() {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT sum(amount)  FROM purchasemodel ");
	
		purchasesPrice=query.list();
		return purchasesPrice;
	}

	@Override
	public String salesPriceList() {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(amounts),0) FROM salesmodel");
		
		Double salesPrice1=(Double) query.uniqueResult();
		String salesPrice=String.valueOf(salesPrice1);
		String salesprice2=salesPrice.substring(salesPrice.indexOf("."));
		if(salesprice2.length()>=3)
		{
			 salesPrice=salesPrice.substring(0, salesPrice.indexOf('.')+3);
		}
		
		
		return salesPrice;
	}

	@Override
	public String closingStockList() {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(ToTalAmount),0) FROM stockmodel");
		double closingStocklist1=(double) query.uniqueResult();
		String closingStocklist=String.valueOf(closingStocklist1);
		String stocktot=closingStocklist.substring(closingStocklist.indexOf("."));
		if(stocktot.length()>=3)
		{
			closingStocklist=closingStocklist.substring(0, closingStocklist.indexOf('.')+3);
		}
		
		return closingStocklist;
	}

	@Override
	public HashMap getlistname() {
		 List<HashMap> getledger=new ArrayList<>();
         
         
  	   SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT suplierid,Supliername FROM supliermodel where remainingAmount !=0");
  		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
  		
  		getledger=query.list();
  		getladgerNamelist.clear();
  		for(HashMap l: getledger)
  		{
  			getladgerNamelist.put(l.get("suplierid"),l.get("Supliername"));
  		} 
  
     System.out.println("getladgerNamelist"+getladgerNamelist);
	return getladgerNamelist;
	}

	@Override
	public String getsupplierName(String supplierName) {
	SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT Supliername FROM supliermodel s where suplierid='"+supplierName+"'");
     String supliername=(String) query.uniqueResult();
		return supliername;
	}

	@Override
	public HashMap getCustomerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addItem(AssetModel assetModel,double totalAmout,String openingBalance) {
		// TODO Auto-generated method stub
		String date =assetModel.getAssetDate();
	
           this.sessionfactory.getCurrentSession().save(assetModel);
           
           
           SQLQuery query12=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT * FROM openingbalance where openingDate='"+date+"'");
          List<String> list1=new ArrayList<>();
          list1=query12.list();
         if(list1.size()==0)
         {
        	 SQLQuery query123=this.sessionfactory.getCurrentSession().createSQLQuery("insert into openingbalance (openingDate, totalAmount) values('"+date+"','"+openingBalance+"')");
        	 query123.executeUpdate();
         }
           SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT * FROM addtotalasset where assetDate='"+date+"'");
           List<String>list=new ArrayList<>();
           list=query.list();
           if(list.size()==0)
           {
        	   SQLQuery query2=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT netProfit FROM netprofit where netprofitid in (SELECT max(netprofitid) FROM netprofit)");
        	   double netporfit=  (double) query2.uniqueResult();
        	   
        	   SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("insert into addtotalasset(assetAmount, assetDate) values(('"+totalAmout+"')+('"+netporfit+"'),'"+date+"')");
        	   query1.executeUpdate();
           }
           else
           {
        	   SQLQuery query2=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT netProfit FROM netprofit where netprofitid in (SELECT max(netprofitid) FROM netprofit)");
        	   double netporfit=  (double) query2.uniqueResult();
        	   SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("update addtotalasset set assetAmount=('"+totalAmout+"')+('"+netporfit+"') where assetDate='"+date+"' ");
        	   query1.executeUpdate();

           }
           
	}

	@Override
	public void lessItem(AssetModel1 assetModel1,Double totalAmount) {
		String date=assetModel1.getAssetDate();
		
	this.sessionfactory.getCurrentSession().save(assetModel1);
	 SQLQuery query2=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT netProfit FROM netprofit where netprofitid in (SELECT max(netprofitid) FROM netprofit)");
	   double netporfit=  (double) query2.uniqueResult();
	SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("update addtotalasset set assetAmount=('"+totalAmount+"')+('"+netporfit+"') where assetDate='"+date+"'");
	query.executeUpdate();
		
	}

	@Override
	public String getnetprofit() {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(netProfit),0) FROM netprofit where netprofitid in (SELECT max(netprofitid) FROM netprofit) ");
		double netProfit1=(double) query.uniqueResult();
		String netProfit=String.valueOf(netProfit1);
		return netProfit;
	}

	@Override
	public String getclosingstock() {
	SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(ToTalAmount),0) FROM stockmodel ");
	double closeingStock1=(double)query.uniqueResult();
	String closeingStock=String.valueOf(closeingStock1);
		return closeingStock;
	}

	@Override
	public void addLablites(Labilites labilites, Double totAmount) {
		
		System.out.println("ssssssssssssssssssssssssss");
		this.sessionfactory.getCurrentSession().save(labilites);
		String date=labilites.getLabDate();
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery(" SELECT * FROM addtotallablites where lablitesDate='"+date+"'");
		List<String> list=new ArrayList<>();
		list=query.list();
		
		
		if(list.size()==0)
		{
			SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("insert into addtotallablites (labAmount, lablitesDate) values('"+totAmount+"','"+date+"')");
			query1.executeUpdate();
		}
		else
		{
			SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("update addtotallablites set labAmount='"+totAmount+"' where lablitesDate='"+date+"' ");
			query1.executeUpdate();
			
		}
		
	}

	@Override
	public void lesslabItem(Labilites2 labilites) {
		this.sessionfactory.getCurrentSession().save(labilites);
		
	}

	@Override
	public List<String> getitemlist(String productitem) {
		List<String> getitemList=new ArrayList<>();
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery(" SELECT SubProductName FROM sub_productmodel where SubProductName='"+productitem+"'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		getitemList=query.list();
		return getitemList;
	}

	@Override
	public void update(String id, String productType, String productItem,
			String quantity, String price, String amount, double totalAmount,
			String billid) {
		
		SQLQuery query21=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT  Price FROM purchasemodel where Purchaseid='"+id+"'");
		Double oldPrice=(Double)query21.uniqueResult();
		SQLQuery query22=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT  quntity FROM purchasemodel where Purchaseid='"+id+"'");
		String oldquntity=(String)query22.uniqueResult();
		SQLQuery query23=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT  amount FROM purchasemodel where Purchaseid='"+id+"'");
		Double oldTotAmout=(Double)query23.uniqueResult();
		SQLQuery query24=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT  productid FROM purchasemodel where Purchaseid='"+id+"'");
		int idproduct=(int)query24.uniqueResult();
		SQLQuery query25=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT  subProductid FROM purchasemodel where Purchaseid='"+id+"'");
		int idproductsub=(int)query25.uniqueResult();
		SQLQuery query26=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT purchaseCode FROM stockmodel where price='"+oldPrice+"' and productid='"+idproduct+"' and subProductid='"+idproductsub+"' ");
		String productCode=(String)query26.uniqueResult();
		
		SQLQuery query27=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ToTalAmount  FROM stockmodel where purchaseCode='"+productCode+"'");
		Double totaAmount=(Double)query27.uniqueResult();
		
		SQLQuery query3=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT quntity  FROM stockmodel where purchaseCode='"+productCode+"'");
		String  totQuantity=(String)query3.uniqueResult();
		  SQLQuery query28=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel set ToTalAmount=('"+totaAmount+"')-('"+oldTotAmout+"'),quntity=('"+oldquntity+"')-('"+totQuantity+"') where  purchaseCode='"+productCode+"' ");
	       query28.executeUpdate();
	       SQLQuery query6=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT price  FROM stockmodel where purchaseCode='"+productCode+"'");
			Double totprice1=(Double)query6.uniqueResult();
			SQLQuery query7=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT quntity  FROM stockmodel where purchaseCode='"+productCode+"'");
			String  totQuantity1=(String)query7.uniqueResult();
			double totQuantity2=Double.parseDouble(totQuantity1);
			SQLQuery query10=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT codeSub FROM sub_productmodel where subProductid='"+idproductsub+"'");
			String subproductid=(String)query10.uniqueResult();
			 NumberFormat nf1=NumberFormat.getIntegerInstance();
			 String quntit=  nf1.format(totQuantity2);
			 int QTY = Integer.parseInt(quntit.replaceAll(",", ""));
			
			
			 NumberFormat nf=NumberFormat.getIntegerInstance();
			 String pris=  nf.format(totprice1);
			 int price1 = Integer.parseInt(pris.replaceAll(",", ""));
			
		    String productCode12=idproduct+subproductid+price1+""+QTY;
		    SQLQuery query12=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel set purchaseCode='"+productCode12+"' where  purchaseCode='"+productCode+"' ");
	        query12.executeUpdate();
			
	        
	        
	        
	      
		
	      SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT codeSub FROM sub_productmodel  where SubProductName='"+productItem+"' and productid='"+productType+"' ");
	       String code=(String)query.uniqueResult();
	       SQLQuery query1=sessionfactory.getCurrentSession().createSQLQuery("SELECT subProductid FROM sub_productmodel  where SubProductName='"+productItem+"' and productid='"+productType+"'");
	       int subProductId=(int)query1.uniqueResult();
	       
	       SQLQuery query2=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT * FROM stockmodel  where price='"+price+"' and productid='"+productType+"'and subProductid='"+subProductId+"'");
	       List<String> list=new ArrayList<>();
	       list=query2.list();
	       
	       if(list.size()==0)
	       {
	    	   System.out.println("ssss");
	    	   double quanitity=Double.parseDouble(quantity);
	    	   NumberFormat ss=NumberFormat.getIntegerInstance();
	    	   String quantity1=ss.format(quanitity);
	    	   int quantity123 = Integer.parseInt(quantity1.replaceAll(",", ""));
	    	 String  purchaseCodes=productType+code+price+quantity123;
	    	   SQLQuery query31=this.sessionfactory.getCurrentSession().createSQLQuery("insert into stockmodel (ToTalAmount, price, purchaseCode, quntity, productid, subProductid) values('"+amount+"','"+price+"','"+purchaseCodes+"','"+quantity+"','"+productType+"','"+subProductId+"') ");
	    	   query31.executeUpdate();
	    	   
	    	   SQLQuery query33=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT id FROM stockmodel  where price='"+price+"' and productid='"+productType+"'and subProductid='"+subProductId+"'");
	    	 int stockid=(int) query33.uniqueResult();
	    	   SQLQuery query32=this.sessionfactory.getCurrentSession().createSQLQuery("update purchasemodel set Price='"+price+"',amount='"+amount+"',quntity='"+quantity+"',productid='"+productType+"',subProductid='"+subProductId+"',id='"+stockid+"' where Purchaseid='"+id+"'");
	           query32.executeUpdate();
	       }
	       else
	       {
	    	   System.out.println("z");
	    	   double quantityold=Double.parseDouble(quantity);
	    	   double oldAmout=Double.parseDouble(amount);
	    	   
	    	   SQLQuery query33=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT id FROM stockmodel  where price='"+price+"' and productid='"+productType+"'and subProductid='"+subProductId+"'");
	    	 int stockid=(int) query33.uniqueResult();
	    	  
	    	 
	    	   SQLQuery query35=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ToTalAmount FROM stockmodel  where price='"+price+"' and productid='"+productType+"'and subProductid='"+subProductId+"'");
	    	 double stocktot=(double) query35.uniqueResult();
	    	  
	    	   SQLQuery query36=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT quntity FROM stockmodel  where price='"+price+"' and productid='"+productType+"'and subProductid='"+subProductId+"'");
	    	 String stockquantity=(String) query36.uniqueResult();
	    	 SQLQuery query37=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel set quntity=('"+stockquantity+"')+('"+quantityold+"'),ToTalAmount=('"+stocktot+"')+('"+oldAmout+"') where id='"+stockid+"'");
	    	 query37.executeUpdate();
	    	  
	    	  
		         SQLQuery query39=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT price FROM stockmodel  where price='"+price+"' and productid='"+productType+"'and subProductid='"+subProductId+"'");
		    	 double stockprice=(double) query39.uniqueResult();
		        
		    	 SQLQuery query41=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT quntity FROM stockmodel  where price='"+price+"' and productid='"+productType+"'and subProductid='"+subProductId+"'");
		    	 String stockquan=(String) query41.uniqueResult();
		    	 double stockQ=Double.parseDouble(stockquan);
		    	 NumberFormat nf3=NumberFormat.getIntegerInstance();
				 String pri=  nf3.format(stockprice);
				 int price12 = Integer.parseInt(pri.replaceAll(",", ""));
				 NumberFormat nf4=NumberFormat.getIntegerInstance();
				 String quanitity=  nf4.format(stockQ);
				 int qyt = Integer.parseInt(quanitity.replaceAll(",", ""));
				
				 String product=productType+code+price12+qyt;
				 SQLQuery query42=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel set purchaseCode='"+product+"' where id='"+stockid+"' ");
		    	 query42.executeUpdate();
		    	 SQLQuery query32=this.sessionfactory.getCurrentSession().createSQLQuery("update purchasemodel set Price='"+price+"',amount='"+amount+"',quntity='"+quantity+"',productid='"+productType+"',subProductid='"+subProductId+"',id='"+stockid+"' where Purchaseid='"+id+"'");
		           query32.executeUpdate();
	    	   
	       }
	       
		
	}

	@Override
	public List<String> updateStock(String productType, String productItem,
			String productCode, String price, String quantity, String totAmount) {
		double oldPrice=Double.parseDouble(price);
		double oldquntity=Double.parseDouble(quantity);
		double oldTotAmout=Double.parseDouble(totAmount);
		SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ToTalAmount  FROM stockmodel where purchaseCode='"+productCode+"'");
		Double totalAmount=(Double)query1.uniqueResult();
		
		SQLQuery query3=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT quntity  FROM stockmodel where purchaseCode='"+productCode+"'");
		Double  totQuantity=(Double)query3.uniqueResult();
	       SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel set ToTalAmount=('"+totalAmount+"')-('"+oldTotAmout+"'),quntity=('"+oldquntity+"')-('"+totQuantity+"') where  purchaseCode='"+productCode+"' ");
	       query.executeUpdate();
	     
			SQLQuery query6=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT price  FROM stockmodel where purchaseCode='"+productCode+"'");
			Double totprice1=(Double)query6.uniqueResult();
			SQLQuery query7=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT quntity  FROM stockmodel where purchaseCode='"+productCode+"'");
			Double  totQuantity1=(Double)query7.uniqueResult();
			SQLQuery query8=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT productid FROM productmodel  where productName='"+productType+"'");
			int productid=(int)query8.uniqueResult();
			SQLQuery query9=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT codeSub FROM sub_productmodel where SubProductName='"+productItem+"' and productid='"+productType+"'");
			String subproductid=(String)query9.uniqueResult();
			  NumberFormat nf=NumberFormat.getIntegerInstance();
				 String pris=  nf.format(totprice1);
				 int price1 = Integer.parseInt(pris.replaceAll(",", ""));
				 NumberFormat nf2=NumberFormat.getIntegerInstance();
				 String quanity=nf2.format(totQuantity1);
				 int qTy=Integer.parseInt(quanity.replaceAll(",", ""));
			  String productCode12=productid+subproductid+price1+""+qTy;
			  SQLQuery query12=this.sessionfactory.getCurrentSession().createSQLQuery("update stockmodel set purchaseCode='"+productCode12+"' where  purchaseCode='"+productCode+"' ");
		       query12.executeUpdate();
		   return null;
	}

	@Override
	public String getpurchasetotAmount() {
	SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT  ifnull(sum(amount),0) as total FROM purchasemodel ");
	//List<String>totalPurchaseAmount=new ArrayList<>();
	Double totalPurchaseAmount1=(Double) query.uniqueResult();
	String totalPurchaseAmount=String.valueOf(totalPurchaseAmount1);
	String totol=totalPurchaseAmount.substring(totalPurchaseAmount.indexOf("."));
	if(totol.length()>=3)
	{
	 totalPurchaseAmount=totalPurchaseAmount.substring(0,totalPurchaseAmount.indexOf('.')+3);
	}

		return totalPurchaseAmount;
	}

	@Override
	public List<String> openingBalance(String lablitesDate12) {
	SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT totalAmount FROM openingbalance where openingDate='"+lablitesDate12+"'");
	query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	
		return query.list();
	}

	@Override
	public List<String> netprofit(String lablitesDate12) {
		SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(netprofitid),0)  FROM netprofit ");
	BigInteger profit=	(BigInteger) query1.uniqueResult();
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT * FROM netprofit where netprofitid='"+profit+"' ");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return query.list();
	}

	@Override
	public List<String> addlablites(String lablitesDate12) {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT * FROM labilites where labDate='"+lablitesDate12+"'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public List<String> lessLablites(String lablitesDate12) {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT * FROM labilites2 where labDate='"+lablitesDate12+"'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return query.list();
	}

	@Override
	public String gettotalA(String todate11, String fromDate1) {
		SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(addAmount),0) FROM labilites where labDate between '"+todate11+"' and '"+fromDate1+"'");
		double addAmount=(double) query.uniqueResult();
		SQLQuery query1=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(lessAmmount),0) FROM labilites2  where labDate between '"+todate11+"' and '"+fromDate1+"' ");
		double lessAmount=(double) query1.uniqueResult();
		double totalA=addAmount-lessAmount;
		String totalLablitesA=String.valueOf(totalA);
		return totalLablitesA;
	}

	@Override
	public List<String> getTotalA(String toDates, String toFroms) {
	SQLQuery query=this.sessionfactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(l.addAmount),0) as amountss,ifnull(sum(l2.lessAmmount),0) as amount FROM labilites l inner join labilites2 l2 on l.labDate=l2.labDate where l.labDate between '"+toDates+"' and '"+toFroms+"'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
	return query.list();
	}

	

	

}
