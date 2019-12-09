package com.daoC;

import java.math.BigInteger;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daoI.SaleDaoI;
import com.model.CustomerModel;
import com.model.SalesBillingModel;
import com.model.StockModel;
@Repository(value="SaleDaoI")
public class SaleDaoC implements SaleDaoI {
	@Autowired
	SessionFactory sessionFactory;
	List<String> getmaxsaleid=new ArrayList<String>();
	List<String> getStockSummary=new ArrayList<String>();
	List<String> getSalesDetailslist=new ArrayList<String>();
	private List<String> getSalesLadgerlist=new ArrayList<>();
	private List<String>getSalesid=new ArrayList<>();
	private List<String> productCodelist=new ArrayList<>();
 	private List<String> listProductCode=new ArrayList<>();
	private List<String>getSalesList=new ArrayList<>();
    @Transactional
	@Override
	public List<String> getmaxsaleid() {
	  SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(salebillinngid),0)+1 as salebillingid FROM salesbillingmodel");
	  query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	  getmaxsaleid=query.list();
		return getmaxsaleid;
    }
	@Override
	public void addsale(String totalAmounts,String paidAmounts,CustomerModel customerModel, List productTypelist,
			List productNamelist, List quntitylist, List pricelist,
			List amountlist, SalesBillingModel salesBillingModel) {
		  List<StockModel> list=new ArrayList<>();
		 
		  String customerName=customerModel.getCustomername();
		  java.util.Date dt = new java.util.Date();
		 Double totalAmount=Double.parseDouble(totalAmounts);
		 System.out.println("totalAmount"+totalAmount);
		 Double paidAmount=Double.parseDouble(paidAmounts);
		 System.out.println("paidAmount"+paidAmount);
		 String paymentmode=salesBillingModel.getPaymentmode();
		 List<String>customerList=new ArrayList<>();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
			String currentTime = sdf.format(dt);	  
	   SQLQuery query12=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT customerid FROM customermodel  where customername='"+customerName+"'");
	    customerList=query12.list();
	   if(customerList.size()==0)
	   {
		   System.out.println("iffffffffffffffffffffffffffffff");
			 SQLQuery query222=this.sessionFactory.getCurrentSession().createSQLQuery("insert into customermodel ( customername, paidAmount, remainingAmount, totalAmount) values('"+customerName+"','"+0+"','"+0+"','"+0+"')");
			 query222.executeUpdate();
			     SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT max(customerid) FROM customermodel");
				 int customerid=(int) query.uniqueResult();
				 if(paymentmode.equals("Credit"))
				 {
				  SQLQuery query123=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT totalAmount FROM customermodel  where customername='"+customerName+"'");
				   Double amountTotal=(Double) query123.uniqueResult(); 
				   System.out.println("printpaidAmount"+amountTotal);
				   SQLQuery query124=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT paidAmount  FROM customermodel  where customername='"+customerName+"'");
				   Double amountPaid=(Double) query124.uniqueResult(); 
				   System.out.println("printpaidAmount"+amountPaid);
				   SQLQuery query32=this.sessionFactory.getCurrentSession().createSQLQuery("update customermodel set totalAmount=('"+totalAmount+"')+('"+amountTotal+"'),paidAmount=('"+amountPaid+"')+('"+paidAmount+"'),remainingAmount=('"+totalAmount+"')+('"+amountTotal+"') where customername='"+customerName+"' ");
				   query32.executeUpdate();
				   
				   SQLQuery query128=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT sum(paidAmount) as total FROM customermodel  where customername='"+customerName+"'");
				   Double newamountPaid=(Double) query128.uniqueResult(); 
				   SQLQuery query125=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT sum(remainingAmount) as total FROM customermodel  where customername='"+customerName+"'");
				   Double amountRemaining=(Double) query125.uniqueResult(); 
				   SQLQuery query129=this.sessionFactory.getCurrentSession().createSQLQuery("update customermodel set remainingAmount=('"+amountRemaining+"')-('"+newamountPaid+"') where customername='"+customerName+"'");
				   query129.executeUpdate();
				
				 }
				
				 salesBillingModel.setSaledate(currentTime);
				    customerModel.setCustomerid(customerid);
				    salesBillingModel.setCustomerModel(customerModel);
				    sessionFactory.getCurrentSession().save(salesBillingModel);
				    SQLQuery query2=sessionFactory.getCurrentSession().createSQLQuery("SELECT max(salebillinngid) FROM salesbillingmodel");
				    int billingid=(int) query2.uniqueResult();
				    for(int i=0;i<productNamelist.size();i++)
				    {
				    	
				    	SQLQuery query1=sessionFactory.getCurrentSession().createSQLQuery("insert into salesmodel(amounts, price, quantitys, productid, salebillinngid, subProductid) values('"+amountlist.get(i)+"','"+pricelist.get(i)+"','"+quntitylist.get(i)+"','"+productTypelist.get(i)+"','"+billingid+"','"+productNamelist.get(i)+"')");
				    	query1.executeUpdate();
				    	
				    	  SQLQuery query4=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT id ,quntity,ToTalAmount FROM stockmodel where productid='"+productTypelist.get(i)+"' and subProductid='"+productNamelist.get(i)+"'");
						  query4.setResultTransformer(Transformers.aliasToBean(StockModel.class));
						  list=query4.list();
						  if(list.size()==0)
							 {
								 System.out.println("elseBlock");
								 SQLQuery query5=this.sessionFactory.getCurrentSession().createSQLQuery("insert into  stockmodel (quntity, productid, subProductid) values('"+quntitylist.get(i)+"','"+productTypelist.get(i)+"','"+productNamelist.get(i)+"')");
								 query5.executeUpdate();
							 }
							 else
								 
							 {
								
								 System.out.println("ifblock");
									
								    String quntity=list.get(0).getQuntity();
								    int stockid=list.get(0).getId();
								    double totalamount=list.get(0).getToTalAmount();
									 SQLQuery query6=this.sessionFactory.getCurrentSession().createSQLQuery("update stockmodel  set quntity=('"+quntity+"'-'"+quntitylist.get(i)+"'),ToTalAmount=('"+totalamount+"'-('"+quntitylist.get(i)+"'*'"+pricelist.get(i)+"')) where id='"+stockid+"' ");
										query6.executeUpdate();
							 }
				    }
				
				
			   
		   }
		   
			
			
			
	  
	   else
	   {
		   System.out.println("elseeeeeeeeeeeeeeeeeeeeeeeeeeee"); 
		   SQLQuery query122=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT customerid FROM customermodel  where customername='"+customerName+"'");
		   int customerid=(int) query122.uniqueResult(); 
		  if(paymentmode.equals("Credit"))
		  {
		   SQLQuery query123=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT totalAmount FROM customermodel  where customername='"+customerName+"'");
		   Double amountTotal=(Double) query123.uniqueResult();
		   
		   System.out.println("printtotalAmount"+amountTotal);
		   SQLQuery query124=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT paidAmount as tota FROM customermodel  where customername='"+customerName+"'");
		   Double amountPaid=(Double) query124.uniqueResult(); 
		   System.out.println("printpaidAmount"+amountPaid);
		   SQLQuery query32=this.sessionFactory.getCurrentSession().createSQLQuery("update customermodel set totalAmount=('"+totalAmount+"')+('"+amountTotal+"'),paidAmount=('"+amountPaid+"')+('"+paidAmount+"'),remainingAmount=('"+totalAmount+"')+('"+amountTotal+"') where customername='"+customerName+"' ");
		   query32.executeUpdate();
		   
		   SQLQuery query128=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT sum(paidAmount) as total FROM customermodel  where customername='"+customerName+"'");
		   Double newamountPaid=(Double) query128.uniqueResult(); 
		   SQLQuery query125=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT sum(remainingAmount) as total FROM customermodel  where customername='"+customerName+"'");
		   Double amountRemaining=(Double) query125.uniqueResult(); 
		   SQLQuery query129=this.sessionFactory.getCurrentSession().createSQLQuery("update customermodel set remainingAmount=('"+amountRemaining+"')-('"+newamountPaid+"') where customername='"+customerName+"'");
		   query129.executeUpdate();
		  }
		   salesBillingModel.setSaledate(currentTime);
		    customerModel.setCustomerid(customerid);
		    salesBillingModel.setCustomerModel(customerModel);
		    sessionFactory.getCurrentSession().save(salesBillingModel);
		    SQLQuery query2=sessionFactory.getCurrentSession().createSQLQuery("SELECT max(salebillinngid) FROM salesbillingmodel");
		    int billingid=(int) query2.uniqueResult();
		    for(int i=0;i<productNamelist.size();i++)
		    {
		    	
		    	SQLQuery query1=sessionFactory.getCurrentSession().createSQLQuery("insert into salesmodel(amounts, price, quantitys, productid, salebillinngid, subProductid) values('"+amountlist.get(i)+"','"+pricelist.get(i)+"','"+quntitylist.get(i)+"','"+productTypelist.get(i)+"','"+billingid+"','"+productNamelist.get(i)+"')");
		    	query1.executeUpdate();
		    	
		    	  SQLQuery query4=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT id ,quntity,ToTalAmount FROM stockmodel where productid='"+productTypelist.get(i)+"' and subProductid='"+productNamelist.get(i)+"'");
				  query4.setResultTransformer(Transformers.aliasToBean(StockModel.class));
				  list=query4.list();
				  if(list.size()==0)
					 {
						 System.out.println("elseBlock");
						 SQLQuery query5=this.sessionFactory.getCurrentSession().createSQLQuery("insert into  stockmodel (quntity, productid, subProductid) values('"+quntitylist.get(i)+"','"+productTypelist.get(i)+"','"+productNamelist.get(i)+"')");
						 query5.executeUpdate();
					 }
					 else
						 
					 {
						
						 System.out.println("ifblock");
							
						    String quntity=list.get(0).getQuntity();
						    int stockid=list.get(0).getId();
						    double totalamount=list.get(0).getToTalAmount();
							 SQLQuery query6=this.sessionFactory.getCurrentSession().createSQLQuery("update stockmodel  set quntity=('"+quntity+"'-'"+quntitylist.get(i)+"'),ToTalAmount=('"+totalamount+"'-('"+quntitylist.get(i)+"'*'"+pricelist.get(i)+"')) where id='"+stockid+"' ");
								query6.executeUpdate();
					 }
		    }
	   }
	}
	@Override
	public List<String> getstocklist() {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT s.id,s.quntity,p.productName,sp.SubProductName, s.ToTalAmount,s.price FROM stockmodel s  inner join productmodel p on s.productid=p.productid inner join sub_productmodel sp on s.subProductid=sp.subProductid inner join purchasemodel pr on s.id=pr.id where s.quntity !=0 group by id");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		getStockSummary=query.list();
		return getStockSummary;
	}
	@Override
	public List<String> getsalesDetails() {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT cust.customername, p.productName,sub.SubProductName,s.amounts, s.price, s.quantitys,bill.salebillinngid,bill.paymentmode, bill.saledate, bill.totalAmount FROM salesmodel s inner join salesbillingmodel sale inner join salesbillingmodel bill on bill.salebillinngid=s.salebillinngid inner join productmodel p on p.productid=s.productid inner join sub_productmodel sub on sub.subProductid=s.subProductid inner join customermodel cust on cust.customerid=bill.customerid group by bill.salebillinngid");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		getSalesDetailslist=query.list();
		return getSalesDetailslist;
	}
	@Override
	public List<String> getLadgerList(String customerName) {
		
     SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT s.salebillinngid,c.customername,s.totalAmount,s.paidAmount,s.saledate FROM salesbillingmodel s inner join salesmodel sa on s.salebillinngid=sa.salebillinngid inner join customermodel c on c.customerid=s.customerid where c.customerid='"+customerName+"' group by s.salebillinngid ");
     query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
     getSalesLadgerlist=query.list();
	 return getSalesLadgerlist;
	}
	@Override
	public void updatePayment(String totalAmount, String paidAmount,
			String remainingAmount, String customerName,HttpSession session) {
		
	
		Double paiAmount=Double.parseDouble(paidAmount);
		Double remaininAmount=Double.parseDouble(remainingAmount);
		Double totA=Double.parseDouble(totalAmount);
		java.util.Date date=new java.util.Date();
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
		String currentDate=sdf.format(date);
	SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("update customermodel set  paidAmount='"+paiAmount+"', remainingAmount='"+remaininAmount+"' where customerid='"+customerName+"'");
	query.executeUpdate();
	SQLQuery query1=this.sessionFactory.getCurrentSession().createSQLQuery("insert into salesledgermodel (paidAmount, salesDate, totAmount, customerid, remainingA) values('"+paiAmount+"','"+currentDate+"','"+totA+"','"+customerName+"','"+remaininAmount+"')");
	query1.executeUpdate();
	SQLQuery query3=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT customername FROM customermodel where customerid='"+customerName+"'");
	String customeName=(String)query3.uniqueResult();
	session.setAttribute("customerName",customeName);
	
	}
	
	
	@Override
	public List<String> getsalesid() {
	SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(salebillinngid),0)+1 as saleid  FROM salesbillingmodel ");
	getSalesid=query.list();
		return getSalesid;
	}
	@Override
	public void sales(String productType, String productname, String quntity,
			String price, String amount, String totalAmount,String saleId,String productCode,String customerName) {
		List<String> list=new ArrayList<>();
		java.util.Date d1=new java.util.Date();
		java.text.SimpleDateFormat sdf1=new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
		String saledate1=sdf1.format(d1);
		
		SQLQuery query44=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM customermodel c where customername='"+customerName+"' ");
		list=query44.list();
		if(list.size()==0)
		{
			CustomerModel customer=new CustomerModel();
			customer.setCustomername(customerName);
			this.sessionFactory.getCurrentSession().save(customer);
			
		}
		SQLQuery query55=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT customerid  FROM customermodel c where customername='"+customerName+"'");
	int customerid=(int) query55.uniqueResult();
		
		
		SQLQuery query123=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT productid FROM productmodel  where  productName ='"+productType+"'");
		int productT=(int) query123.uniqueResult();
		SQLQuery query34=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT subProductid FROM sub_productmodel where SubProductName='"+productname+"' and productid ='"+productT+"'");
		int productN=(int)query34.uniqueResult();
		SQLQuery query35=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT codeSub FROM sub_productmodel where SubProductName='"+productname+"' and productid ='"+productT+"'");
		String productC=(String)query35.uniqueResult();
	Double qunitityd=Double.parseDouble(quntity);
	System.out.println("quntity"+qunitityd);
	Double priced=Double.parseDouble(price);
	System.out.println("price"+priced);
	Double amountd=Double.parseDouble(amount);
	System.out.println("amountd"+amountd);
	Double totAmount=Double.parseDouble(totalAmount);
	
	int saleid=Integer.parseInt(saleId);
	java.util.Date d=new java.util.Date();
	java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
	String currentDate=sdf.format(d);
	SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(max(salebillinngid),0) as id FROM salesbillingmodel ");
	BigInteger billId=(BigInteger) query.uniqueResult();
	String bill=String.valueOf(billId);
	SQLQuery query66=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT id FROM stockmodel where purchaseCode='"+productCode+"'");
	int idStock=(int)query66.uniqueResult();
	if(saleId.equals(bill))
	{
		System.out.println("ifff");
		SQLQuery query1=this.sessionFactory.getCurrentSession().createSQLQuery("insert into salesmodel( amounts, price, quantitys, productid, salebillinngid, subProductid, id,saledate) values('"+amountd+"','"+priced+"','"+qunitityd+"','"+productT+"','"+saleid+"','"+productN+"','"+idStock+"','"+saledate1+"')");
		query1.executeUpdate();
		SQLQuery query5=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ToTalAmount FROM stockmodel  where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"'");
		Double totAmoun=(Double)query5.uniqueResult();
		System.out.println("totAData"+totAmoun);
		SQLQuery query7=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT quntity FROM stockmodel  where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"'");
		String quntitys=(String)query7.uniqueResult();
		System.out.println("quntitysdata"+quntitys);
		Double quntitya=Double.parseDouble(quntitys);
		SQLQuery query20=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT price FROM stockmodel  where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"'");
		Double priceA=(Double) query20.uniqueResult();
		System.out.println("priceAData"+priceA);
		String pricee=String.valueOf(priceA);
		System.out.println("sss"+pricee);
		Double totA=(qunitityd)*(priceA);
		
	    SQLQuery query9=this.sessionFactory.getCurrentSession().createSQLQuery("update stockmodel set ToTalAmount=('"+totAmoun+"')-('"+totA+"'), quntity=('"+quntitya+"')-('"+qunitityd+"') where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"' ");
	    query9.executeUpdate();
	    SQLQuery query15=this.sessionFactory.getCurrentSession().createSQLQuery("select quntity from stockmodel  where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"' ");
	   String priceAa=(String) query15.uniqueResult();
	   
	   SQLQuery query33=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT price FROM stockmodel  where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"'");
		Double Pricess=(Double) query33.uniqueResult();
		  NumberFormat nf=NumberFormat.getIntegerInstance();
			 String pris=  nf.format(Pricess);
			 int price1 = Integer.parseInt(pris.replaceAll(",", ""));
	    System.out.println("prodtye"+productT+" "+productN+"  "+price1+"  "+priceAa);
	    String purchaseCode=productT+""+productC+""+price1+""+priceAa;
		    SQLQuery query16=this.sessionFactory.getCurrentSession().createSQLQuery("update stockmodel set purchaseCode='"+purchaseCode+"' where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"' ");
		    query16.executeUpdate();
	}
	else
	{
		System.out.println("else");
		
		SQLQuery query2=this.sessionFactory.getCurrentSession().createSQLQuery("insert into salesbillingmodel(discount, paidAmount, paymentmode, remainingAmount, saledate, totalAmount, customerid) values('"+0+"','"+0+"','"+null+"','"+0+"','"+currentDate+"','"+0+"','"+customerid+"')");
		query2.executeUpdate();
		SQLQuery query3=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT max(salebillinngid) as id FROM salesbillingmodel ");
		int billId1=(int) query3.uniqueResult();
		SQLQuery query4=this.sessionFactory.getCurrentSession().createSQLQuery("insert into salesmodel(amounts, price, quantitys, productid, salebillinngid, subProductid,id,saledate) values('"+amountd+"','"+priced+"','"+qunitityd+"','"+productT+"','"+billId1+"','"+productN+"','"+idStock+"','"+saledate1+"')");
		query4.executeUpdate();
		SQLQuery query10=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ToTalAmount FROM stockmodel  where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"'");
		Double totAmoun=(Double)query10.uniqueResult();
		SQLQuery query11=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT quntity FROM stockmodel  where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"'");
		String quntity1=(String)query11.uniqueResult();
		SQLQuery query20=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT price FROM stockmodel  where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"'");
		Double priceA=(Double) query20.uniqueResult();
		Double totA=(qunitityd)*(priceA);
		Double quntitya=Double.parseDouble(quntity1);
		  SQLQuery query9=this.sessionFactory.getCurrentSession().createSQLQuery("update stockmodel set ToTalAmount=('"+totAmoun+"')-('"+totA+"'), quntity=('"+quntitya+"')-('"+qunitityd+"') where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"' ");
		    query9.executeUpdate();
		    SQLQuery query15=this.sessionFactory.getCurrentSession().createSQLQuery("select quntity from stockmodel  where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"' ");
		    String priceAa=(String) query15.uniqueResult();
		    SQLQuery query33=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT price FROM stockmodel  where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"'");
			Double Price1=(Double) query33.uniqueResult();
		   NumberFormat nf=NumberFormat.getIntegerInstance();
		 String prics=  nf.format(Price1);
		  
		  int price1 = Integer.parseInt(prics.replaceAll(",", ""));
		    String purchaseCode=productT+""+productC+""+price1+""+priceAa;
			 SQLQuery query16=this.sessionFactory.getCurrentSession().createSQLQuery("update stockmodel set purchaseCode='"+purchaseCode+"' where  productid='"+productT+"' and subProductid='"+productN+"' and purchaseCode='"+productCode+"' ");
			    query16.executeUpdate();
	}
		
	}
	@Override
	public List<String> getproductCodelist() {
		
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT purchaseCode FROM stockmodel where quntity !=0");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		productCodelist=query.list();
	
		return productCodelist;
	}
	@Override
	public List<String> getproductcode(String productCode) {
		String purchasess=productCode+"%";
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT p.productName,sub.SubProductName FROM stockmodel s inner join productmodel p on s.productid=p.productid inner join sub_productmodel sub on sub.subProductid=s.subProductid where purchaseCode like '"+purchasess+"'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		listProductCode=query.list();
		System.out.println("ssss"+listProductCode);
		return listProductCode;
	}
	@Override
	public List<String> getAllSalesList(String saleId) {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT stock.purchaseCode,s.saleid,sub.SubProductName, s.amounts, s.price, s.quantitys,p.productName FROM salesmodel s inner join sub_productmodel sub on sub.subProductid=s.subProductid inner join productmodel p on p.productid=s.productid inner join stockmodel stock on stock.id=s.id where s.salebillinngid='"+saleId+"'");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		getSalesList=query.list();
		return getSalesList;
	}
	@Override
	public void salesSave(String discount, String paymentMode,
			String paidAmount, String totalAmount, String customerName) {
		
		Double discout=Double.parseDouble(discount);
		Double paidAm=Double.parseDouble(paidAmount);
		Double totA=Double.parseDouble(totalAmount);
	
		SQLQuery query11=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT max(salebillinngid) FROM salesbillingmodel ");
		int maxbillid=(int) query11.uniqueResult();
		java.util.Date d=new java.util.Date();
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
		String currentDate=sdf.format(d);
		
		
	if(paymentMode.equals("Credit"))
	{
		if(paidAm>0)
		{
			SQLQuery query23=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT customerid FROM customermodel where customername='"+customerName+"'");
			int customerid=(int) query23.uniqueResult();
			System.out.println("000000");
			SQLQuery query10=this.sessionFactory.getCurrentSession().createSQLQuery("insert into  salesledgermodel(paidAmount, salesDate, totAmount, remainingA,customerid) values('"+paidAm+"','"+currentDate+"','"+totA+"',('"+totA+"')-('"+paidAm+"'),'"+customerid+"')");
		     query10.executeUpdate();

		}
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT paidAmount FROM customermodel  where customername='"+customerName+"'");
		Double paidA=(Double) query.uniqueResult();
		SQLQuery query1=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT remainingAmount FROM customermodel  where customername='"+customerName+"'");
		Double remainingA=(Double) query1.uniqueResult();
		SQLQuery query2=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT totalAmount FROM customermodel  where customername='"+customerName+"'");
		Double totAmount=(Double) query2.uniqueResult();
		SQLQuery query3=this.sessionFactory.getCurrentSession().createSQLQuery("update customermodel set  paidAmount=('"+paidAm+"')+('"+paidA+"'), totalAmount=('"+totA+"')+('"+totAmount+"') where customername='"+customerName+"'");
		query3.executeUpdate();
		SQLQuery query7=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT paidAmount FROM customermodel  where customername='"+customerName+"'");
		Double updatePaid=(Double) query7.uniqueResult();
		SQLQuery query8=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT totalAmount FROM customermodel  where customername='"+customerName+"'");
		Double updatetot=(Double) query8.uniqueResult();
		SQLQuery query9=this.sessionFactory.getCurrentSession().createSQLQuery("update customermodel set  remainingAmount=('"+updatetot+"')-('"+updatePaid+"') where customername='"+customerName+"'");
		query9.executeUpdate();
		SQLQuery query12=this.sessionFactory.getCurrentSession().createSQLQuery("update salesbillingmodel set discount='"+discout+"', paidAmount='"+paidAm+"', paymentmode='"+paymentMode+"', totalAmount='"+totA+"' where salebillinngid='"+maxbillid+"'");
	    query12.executeUpdate();	
	}
	else
	{
		SQLQuery query12=this.sessionFactory.getCurrentSession().createSQLQuery("update salesbillingmodel set discount='"+discout+"', paidAmount='"+paidAm+"', paymentmode='"+paymentMode+"', totalAmount='"+totA+"' where salebillinngid='"+maxbillid+"'");
	     query12.executeUpdate();	
	}
		
	}
	@Override
	public String getsaleid() {
		
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT max(salebillinngid) as saleid  FROM salesbillingmodel ");
	int id1=(int) query.uniqueResult();
    String id=String.valueOf(id1);
			return id;
	}
	@Override
	public List<String> getpurchasecode(String productCode) {
		
		String productC=productCode+"%";
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT purchaseCode FROM stockmodel  where purchaseCode like '"+productC+"' and quntity !=0 ");
		/*query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);*/
		List<String>purchasecodeList=new ArrayList<>();
		purchasecodeList=query.list();
		System.out.println("pu"+purchasecodeList);
		return purchasecodeList;
	}
	@Override
	public String getsalesAmount() {
		SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(amounts),0) as total FROM salesmodel ");
		//List<String> getSalesAmount=new ArrayList<>();
		double getSalesAmount1=(double) query.uniqueResult();
		String getSalesAmount=String.valueOf(getSalesAmount1);
		String salesAmount=getSalesAmount.substring(getSalesAmount.indexOf("."));
		if(salesAmount.length()>=3)
		{
			 getSalesAmount=getSalesAmount.substring(0,getSalesAmount.indexOf('.')+3);
		}
		
		return getSalesAmount;
	}
	@Override
	public String gettotalStockAmount() {
	SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ifnull(sum(ToTalAmount),0) as total FROM stockmodel ");
	// List<String>totalStockAmount=new ArrayList<>();
	Double totalStockAmounte=(Double) query.uniqueResult();
	String totalStockAmount=String.valueOf(totalStockAmounte);
		return totalStockAmount;
	}
	@Override
	public void updateSale(String salesid, String totAmount,
			String customerName, String productCo, String productType,
			String productItem, String quantity, String price, String amount,String billid) {

	        SQLQuery query33=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT amounts FROM salesmodel where saleid='"+salesid+"'");
	        Double recentAmount=(Double) query33.uniqueResult();
	        SQLQuery query34=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT quantitys FROM salesmodel where saleid='"+salesid+"'");
	         String recentquantity1=(String) query34.uniqueResult();
	        double recentquantity=Double.parseDouble(recentquantity1);
		    SQLQuery query23=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT st.purchaseCode FROM salesmodel s inner join stockmodel st on s.id=st.id where s.saleid='"+salesid+"'");
		    String productCode=(String) query23.uniqueResult();
		
		    SQLQuery query=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ToTalAmount FROM stockmodel s where purchaseCode='"+productCode+"'");
		    Double stockamount=(Double) query.uniqueResult();
		   
		    SQLQuery query2=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT quntity FROM stockmodel s where purchaseCode='"+productCode+"'");
		    String stockquantity21=(String) query2.uniqueResult();
		    SQLQuery query78=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT price FROM stockmodel s where purchaseCode='"+productCode+"'");
		    Double price12=(Double) query78.uniqueResult();
		    SQLQuery query80=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT price FROM stockmodel s where purchaseCode='"+productCo+"'");
		    Double price122=(Double) query80.uniqueResult();
		    double stockquantity=Double.parseDouble(stockquantity21);
		    SQLQuery query3=sessionFactory.getCurrentSession().createSQLQuery("update stockmodel set quntity=('"+stockquantity+"')+('"+recentquantity+"'),ToTalAmount=('"+stockamount+"')-(('"+price12+"')*('"+recentquantity+"')) where purchaseCode='"+productCode+"'");
		    query3.executeUpdate();
		   
		    SQLQuery query6=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT price FROM stockmodel s where purchaseCode='"+productCode+"'");
		    Double stockprice1=(Double) query6.uniqueResult();
		    SQLQuery query7=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT quntity FROM stockmodel s where purchaseCode='"+productCode+"'");
		    String stockquantity12=(String) query7.uniqueResult();
		    double stockquantity1=Double.parseDouble(stockquantity12);
		     SQLQuery query9=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT productid FROM stockmodel where purchaseCode='"+productCode+"'");
		     int productid=(int)query9.uniqueResult();
		     SQLQuery query49=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT subProductid FROM stockmodel where purchaseCode='"+productCode+"'");
		     int suproductid=(int)query49.uniqueResult();
		     SQLQuery query10=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT codeSub FROM sub_productmodel where productid= '"+productid+"' and  subProductid='"+suproductid+"'");
		     String subproductid=(String)query10.uniqueResult();
		   
		    NumberFormat nf= NumberFormat.getIntegerInstance();
             String convertQuantity=nf.format(stockquantity1);
             int totQuantity=Integer.parseInt(convertQuantity.replaceAll(",", ""));
             NumberFormat nf1=NumberFormat.getIntegerInstance();
             String formatprice=nf1.format(stockprice1);
             int totPrice=Integer.parseInt(formatprice.replaceAll(",", ""));
             String newProductCode=productid+""+subproductid+totPrice+totQuantity;
             SQLQuery query11=this.sessionFactory.getCurrentSession().createSQLQuery("update stockmodel set purchaseCode='"+newProductCode+"' where purchaseCode='"+productCode+"'");
             query11.executeUpdate();
             
             SQLQuery query12=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT customerid FROM customermodel  where customername='"+customerName+"'");
     	List<String> list=new ArrayList<>();
     	list=query12.list();
     	if(list.size()==0)
     	{
     		SQLQuery query13=this.sessionFactory.getCurrentSession().createSQLQuery("insert into customermodel ( customername, paidAmount, remainingAmount, totalAmount) values('"+customerName+"','"+0+"','"+0+"','"+0+"') ");
     		query13.executeUpdate();
     		SQLQuery query15=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT customerid FROM customermodel  where customername='"+customerName+"'");
     		int customerid=(int)query15.uniqueResult();
     		SQLQuery query16=this.sessionFactory.getCurrentSession().createSQLQuery("update salesbillingmodel set customerid='"+customerid+"' where salebillinngid='"+billid+"'");
     		query16.executeUpdate();
     	}
     	else
     	{
     		SQLQuery query15=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT customerid FROM customermodel  where customername='"+customerName+"'");
     		int customerid=(int)query15.uniqueResult();
     		SQLQuery query14=this.sessionFactory.getCurrentSession().createSQLQuery("update customermodel set customername='"+customerName+"' where customerid='"+customerid+"' ");
     		query14.executeUpdate();
     		SQLQuery query16=this.sessionFactory.getCurrentSession().createSQLQuery("update salesbillingmodel set customerid='"+customerid+"' where salebillinngid='"+billid+"'");
     		query16.executeUpdate();
     	}
     	 SQLQuery query99=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT productid FROM productmodel where productName='"+productType+"'");
	     int productid1=(int)query99.uniqueResult();
     	 SQLQuery query19=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT subProductid FROM sub_productmodel where productid= '"+productid1+"' and  SubProductName='"+productItem+"'");
		 int subProducts=(int) query19.uniqueResult();
		 SQLQuery query2w=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT ToTalAmount FROM stockmodel s where productid='"+productid1+"' and subProductid='"+subProducts+"' and price='"+price122+"'");
		 Double stockamount1=(Double) query2w.uniqueResult();
		   
	
		    SQLQuery query77=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT quntity FROM stockmodel s where productid='"+productid1+"' and subProductid='"+subProducts+"' and price='"+price122+"'");
		    String stockquantity111=(String) query77.uniqueResult();
		    double stockquantity11=Double.parseDouble(stockquantity111);
		     SQLQuery query109=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT codeSub FROM sub_productmodel where productid= '"+productid1+"' and  SubProductName='"+productItem+"'");
		     String subproductid2=(String)query109.uniqueResult();
		 
		     Double recentquantity15=Double.parseDouble(quantity);
			
		     Double recentamout=Double.parseDouble(amount);
		     SQLQuery query52=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT id FROM stockmodel  where productid='"+productid1+"' and subProductid='"+subProducts+"' and price='"+price122+"'");
			 int stockid=(int) query52.uniqueResult();
		     SQLQuery query51=this.sessionFactory.getCurrentSession().createSQLQuery("update salesmodel set amounts='"+amount+"',price='"+price+"',quantitys='"+quantity+"',productid='"+productid1+"',subProductid='"+subProducts+"',salebillinngid='"+billid+"',id='"+stockid+"' where saleid='"+salesid+"'");
			  query51.executeUpdate();
			  SQLQuery query66=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT price FROM stockmodel s where productid='"+productid1+"' and subProductid='"+subProducts+"' and price='"+price122+"'");
			    Double stockprice11=(Double) query66.uniqueResult();
		      SQLQuery query120=sessionFactory.getCurrentSession().createSQLQuery("update stockmodel set  quntity=('"+stockquantity11+"')-('"+recentquantity15+"'),ToTalAmount=('"+stockamount1+"')-(('"+stockprice11+"')*('"+recentquantity15+"')) where productid='"+productid1+"' and subProductid='"+subProducts+"' and price='"+price122+"'");
		      query120.executeUpdate();
			   
			    SQLQuery query782=this.sessionFactory.getCurrentSession().createSQLQuery("SELECT quntity FROM stockmodel s where productid='"+productid1+"' and subProductid='"+subProducts+"' and price='"+price122+"'");
			    String stockquantit1=(String) query782.uniqueResult();
			    double stockquantit=Double.parseDouble(stockquantit1);
			    NumberFormat nf6= NumberFormat.getIntegerInstance();
	             String converQuantity=nf6.format(stockquantit);
	             int totQantity=Integer.parseInt(converQuantity.replaceAll(",", ""));
	             NumberFormat nf7=NumberFormat.getIntegerInstance();
	             String formtprice=nf7.format(stockprice11);
	             int totPrce=Integer.parseInt(formtprice.replaceAll(",", ""));
	             
			    
			    String productcodee=productid1+""+subproductid2+totPrce+totQantity;
			    SQLQuery query43=this.sessionFactory.getCurrentSession().createSQLQuery("update stockmodel set purchaseCode='"+productcodee+"' where productid='"+productid1+"' and subProductid='"+subProducts+"' and price='"+price122+"' ");
			    query43.executeUpdate();
			   
		  
	}

}
