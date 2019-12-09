package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model.AssetModel;
import com.model.AssetModel1;
import com.model.BillingModel;
import com.model.Labilites;
import com.model.Labilites2;
import com.model.PurchaseLedgerModel;
import com.model.PurchaseModel;
import com.model.SalesModel;
import com.model.SuplierModel;
import com.serviceI.ProductServiceI;
import com.serviceI.PurchaseServiceI;
import com.util.NumberToWord;
import com.util.PrintJasperReport;

@Controller
public class PurchaseController {
	  @Autowired
	  private ProductServiceI productServiceI;
      @Autowired
      private PurchaseServiceI purchaseServiceI;
   	private HashMap Productnamelistss=new HashMap<>();
  	private HashMap subproductlist=new HashMap<>();
  	List<String> paymentmodelist=new ArrayList<String>();
  	private List<String> PurchaseDetailslist=new ArrayList<String>();
  	private List<String> purchaseLedgerlist=new ArrayList<String>();
  	private List<String> getPurchaseLedgerlist=new ArrayList<String>();
  	private List<String> LedgerTypelist=new ArrayList<>();
  	private HashMap getladgerNamelist=new HashMap<>();
  	private HashMap getladgerCustomerNamelist=new HashMap<>();
  	private List<String>getTotalAmount=new ArrayList<String>();
  	private List<String> getReminingAmount=new ArrayList<String>();
  	private List<String>getAllProductList=new ArrayList<String>();
  	private List<String>billingidList=new ArrayList<String>();
  	private List<String> productnamelist=new ArrayList<>();
  	private List<String> purchasesPrice=new ArrayList<>();
  	private List<String>salesPrice=new ArrayList<>();
  	private List<String>closingStocklist=new ArrayList<>();
  
      @RequestMapping("purchaseOpenPage")
      public ModelAndView purchaseopen(Model model,@ModelAttribute("PurchaseFromcmd")PurchaseModel purchaseModel,HttpServletRequest req,HttpSession session)
      { 
    	  session=req.getSession();
    	  
    	  paymentmodelist=getpaymentlist();
    	  System.out.println("sasa"+paymentmodelist);
    	  model.addAttribute("paymentmodelist",paymentmodelist);
    	  Productnamelistss=productServiceI.getsubproductlist();
    	  getladgerNamelist=purchaseServiceI.getladgername();
    	  model.addAttribute("getladgerNamelist",getladgerNamelist);
    	  Double totalAmount=(double) 0;
    	  model.addAttribute("totalAmount",totalAmount);
  		model.addAttribute("Productnamelistss",Productnamelistss);
        
  		subproductlist=purchaseServiceI.getsubproductid();
  		System.out.println("list"+subproductlist);
  		model.addAttribute("subproductlist",subproductlist);
  		billingidList=purchaseServiceI.getBillinglist();
  		session.setAttribute("billingidList", billingidList);
  		model.addAttribute("billingidList",billingidList.get(0));
    	  return new ModelAndView("PurchasePageOpen");
      }
  	
    
      @RequestMapping(value="PurchaseItem",params="purchaseB")
      public ModelAndView saveItem(Model model,@ModelAttribute("PurchaseItemcmd")BillingModel billingModel,HttpServletRequest req,HttpSession session)
      {
    	  session=req.getSession();
    	  System.out.println("ssssssssssssssssssssssssssssss");
    	  String productType=req.getParameter("productType");
    	  String productItem=req.getParameter("productname");
    	  String quntity=req.getParameter("quntity");
    	  
    	  String price=req.getParameter("Price");
    	
    	  String amountString=req.getParameter("amount");
    	  Double amountdo=Double.parseDouble(amountString);
    	  String amount=String.valueOf(amountdo);
    	  System.out.println("amout"+amount);
    	  String outbond=amount.substring(amount.indexOf("."));
    	 
    	  if(outbond.length()>=3)
    	  {
    		 
    		  amount=amount.substring(0, amount.indexOf('.')+3);
    		  System.out.println("hoe to configur"+amount);
    	  }
    	  System.out.println("mahesh");
    	  System.out.println("amount"+amount);
    	  String billingId=req.getParameter("billid");
    	  session.setAttribute("billingId", billingId);
    	  System.out.println("billlidd"+billingId);
    	  String totalA=req.getParameter("TotalAmount");
    	  System.out.println("totalB"+totalA);
    	  System.out.println("totalAmount"+totalA);
    	  Double Atot=(Double.parseDouble(price))*(Double.parseDouble(quntity));
    	  Double totalAmount=(Double.parseDouble(totalA))+(Atot);
    	  String toalamount=String.valueOf(totalAmount);
    	  System.out.println("toao"+toalamount);
    	  String totOut=toalamount.substring(toalamount.indexOf("."));
    	  if(totOut.length()>=3)
    	  {
    		  toalamount=toalamount.substring(0, toalamount.indexOf('.')+3);
    		  totalAmount=Double.parseDouble(toalamount);
    	  }
    	 
    	  
    	  System.out.println("mahesh"+totalAmount);
          System.out.println("totA"+totalAmount);
    	  purchaseServiceI.saveItem(productType,productItem,quntity,price,amount,billingId);
    	
    	  getladgerNamelist=purchaseServiceI.getladgername();
	      model.addAttribute("getladgerNamelist",getladgerNamelist);
    	  billingidList=purchaseServiceI.getMaxBillid();
    	  model.addAttribute("billingidList",billingidList.get(0));
    	  getAllProductList=purchaseServiceI.getalllist();
    	  System.out.println("dddd"+getAllProductList);
    	  model.addAttribute("getAllProductList",getAllProductList);
    	  model.addAttribute("totalAmount",totalAmount);
    	  Productnamelistss=productServiceI.getsubproductlist();
		  model.addAttribute("Productnamelistss",Productnamelistss);
	   	  subproductlist=purchaseServiceI.getsubproductid();
		  model.addAttribute("subproductlist",subproductlist);
    	  return new ModelAndView("PurchasePageSaveopen");
      }
    //purchase Update
    	@RequestMapping(value="PurchaseItem",params="updatepurchases")
    	public ModelAndView  update(Model model,@ModelAttribute("PurchaseItemcmd")PurchaseModel purchaseModel,HttpServletRequest req,HttpSession session)
    	{
    		String id=req.getParameter("productid1");
    		String productType=req.getParameter("productType");
    		String productItem=req.getParameter("productname");
    		String quantity=req.getParameter("quntity");
    		String price=req.getParameter("Price");
    		String amount9=req.getParameter("amount");
    		double amountD=Double.parseDouble(amount9);
    		String amount2=String.valueOf(amountD);
    		double amountd=Double.parseDouble(amount2);
    		String amount=String.valueOf(amountd);
    		String outofbond=amount.substring(amount.indexOf("."));
    		if(outofbond.length()>=3)
    		{
    			 amount=amount.substring(0, amount.indexOf('.')+3);
    		}
    		
    		
    		
    		System.out.println("amount"+amount);
    		System.out.println("amount"+amount);
    		String total=req.getParameter("producttot");
    		//String total=total1.substring(0, total1.indexOf('.')+3);
    	
    		System.out.println("tott"+total);
    		String billid=req.getParameter("billid");
    		System.out.println("mahesh Dongare");
    		double totalAmount=(Double.parseDouble(total))+(Double.parseDouble(amount));
    		String totalAmo=String.valueOf(totalAmount);
    		String outofmem=totalAmo.substring(totalAmo.indexOf("."));
    		if(outofmem.length()>=3)
    		{
    			String toalAmount3=totalAmo.substring(0, totalAmo.indexOf('.')+3);
        	     totalAmount=Double.valueOf(toalAmount3);
    		}
    		
    		System.out.println("sss"+totalAmount);
    		purchaseServiceI.update(id,productType,productItem,quantity,price,amount,totalAmount,billid);
    		  
      	  paymentmodelist=getpaymentlist();
      	  System.out.println("sasa"+paymentmodelist);
      	  model.addAttribute("paymentmodelist",paymentmodelist);
      	  Productnamelistss=productServiceI.getsubproductlist();
      	  getladgerNamelist=purchaseServiceI.getladgername();
          model.addAttribute("Productnamelistss",Productnamelistss);
  		  subproductlist=purchaseServiceI.getsubproductid();
  		  System.out.println("list"+subproductlist);
  		  model.addAttribute("subproductlist",subproductlist);
      	  model.addAttribute("getladgerNamelist",getladgerNamelist);
      	 getAllProductList=purchaseServiceI.getalllist();
   	     model.addAttribute("getAllProductList",getAllProductList);
    	  model.addAttribute("totalAmount", totalAmount);
    	  model.addAttribute("billingidList",billid);
    	  return new ModelAndView("PurchasePageOpen");
    		 
    	}
      @RequestMapping(value="PurchaseFrom",params="save")
     public ModelAndView addpurchase(Model model,@ModelAttribute("PurchaseFromcmd")PurchaseModel purchaseModel,HttpServletRequest req,HttpSession session,HttpServletResponse res) throws JRException, NamingException, SQLException, IOException
     {
    	  session=req.getSession();
    	  String supplierName=req.getParameter("ledgerName");
    	  String discount=req.getParameter("Discount");
    	  String paymentMode=req.getParameter("PaymentMode");
    	  String paidAmount=req.getParameter("paidAmount");
    	  String totAmount=req.getParameter("TotalAmount");
    	  String billid=(String) session.getAttribute("billingId");
    	
    	  System.out.println("discount");
    	  purchaseServiceI.savpurchase(supplierName,discount,paymentMode,paidAmount,totAmount);
    	 Double totalAmount= (double) 0;
    	 String  supliername=purchaseServiceI.getsupplierName(supplierName);
        	  Productnamelistss=productServiceI.getsubproductlist();
		  model.addAttribute("Productnamelistss",Productnamelistss);
	   	  subproductlist=purchaseServiceI.getsubproductid();
		  model.addAttribute("subproductlist",subproductlist);
    	    billingidList=purchaseServiceI.getBillinglist();
    		model.addAttribute("billingidList",billingidList.get(0));
    		  getladgerNamelist=purchaseServiceI.getladgername();
    	      model.addAttribute("getladgerNamelist",getladgerNamelist);
    	      model.addAttribute("totalAmount",totalAmount);
    	      String fileReportName="purchase";
    	      ServletContext context=session.getServletContext();
    	      String banner=context.getRealPath("/images/logo.png");
    	      HashMap<String, Object> hm=new HashMap<String,Object>();
    	      hm.put("Billid", billid);
    	      hm.put("discount", discount);
    	      hm.put("supplierName", supliername);
    	      hm.put("totalAmount", totAmount);
    	      hm.put("banner", banner);
    	      PrintJasperReport.printreport(fileReportName, req, res, hm);
    	 return new ModelAndView("PurchasePageOpen");
     }
      @RequestMapping(value="getMaxPurchaseId",method=RequestMethod.GET)
     @ResponseBody
     public List<String> getmaxpurchaseid()
     {
    	  List<String> getMaxpurchaseid=new ArrayList<String>();
    	  getMaxpurchaseid=purchaseServiceI.getmaxproductid();
    	  return getMaxpurchaseid;
     }
      public List<String> getpaymentlist()
      {
    	  paymentmodelist.clear();
    	  paymentmodelist.add("Credit");
    	  paymentmodelist.add("Cash");
    	  return paymentmodelist;
      }
   /*   OpenDayOf Book Page*/
      @RequestMapping("OpenPurchaseDetials")
      public ModelAndView openPurchasePage(Model model)
      {
    	 // List<String>totalPurchaseAmount=new ArrayList<>();
    	String  totalPurchaseAmount=purchaseServiceI.getpurchasetotAmount(); 
    	  PurchaseDetailslist=purchaseServiceI.getpurchaselist();
    	  model.addAttribute("PurchaseDetailslist",PurchaseDetailslist);
    	  model.addAttribute("totalPurchaseAmount", totalPurchaseAmount);
    	  return new ModelAndView("OpenPurchasePage");
      }
    /*  Open Ladger Page*/
      @RequestMapping("PurchaseLedger")
      public ModelAndView purchaseledger(Model model,@ModelAttribute("PurchaseLedgercmd")BillingModel billingModel,HttpServletRequest req)
      { 
    	  getladgerNamelist=purchaseServiceI.getlistname();
    	  model.addAttribute("getladgerNamelist",getladgerNamelist);
    	  return new ModelAndView("OpenPurchasledgerePage");
      }
      @RequestMapping(value="getPurchaseLedger",method=RequestMethod.GET)
      @ResponseBody
      public List<String> getpurchaselist(String billid)
      {
    	  getPurchaseLedgerlist=purchaseServiceI.getpurchaseLedgerlist(billid);
    	  return getPurchaseLedgerlist;
      }
      @RequestMapping(value="PurchaseLedger",params="submit")
      public ModelAndView addpurchaseLedger(Model model,@ModelAttribute("PurchaseLedgercmd")BillingModel billingModel,PurchaseLedgerModel purchaseLedgerModel,HttpServletRequest req)
      {
    	  productServiceI.addpurchaseLedger(req,purchaseLedgerModel,billingModel);
    	  return new ModelAndView("OpenPurchasledgerePage");  
      }
      public List<String>LedgerTypeList()
      {
    	  LedgerTypelist.clear();
    	  LedgerTypelist.add("Sales");
    	  LedgerTypelist.add("Purchase");
    	  return LedgerTypelist;
      }
     /* @RequestMapping(value="PurchaseLedger",params="serach")
      public ModelAndView serach(Model model,@ModelAttribute("PurchaseLedgercmd")BillingModel billingModel,HttpServletRequest req)
      {
    	  System.out.println("ssss");
    	  String ledgerType=req.getParameter("PaymentMode");
    	  String ledgername=req.getParameter("ledgerName");
    	 
    	  purchaseLedgerlist=purchaseServiceI.getpurchaseLedgerlist(ledgerType,ledgername);
    	  model.addAttribute("purchaseLedgerlist",purchaseLedgerlist);
    	  return new ModelAndView("OpenPurchasledgerePage");
      }*/
       
      //Serach 
     @RequestMapping(value="PurchaseLedger",params="save")
     public ModelAndView ledger(Model model,@ModelAttribute("PurchaseLedgercmd")BillingModel billingModel,HttpServletRequest req,HttpSession session)
     {
	    	 session=req.getSession();
	   	  String ledgerType=req.getParameter("PaymentMode");
	   	  String ledgername=req.getParameter("ledgerName");
	   	  String customerName=req.getParameter("customerName");
	   	   session.setAttribute("ledgername", ledgername);
	   	  purchaseLedgerlist=purchaseServiceI.getpurchaseLedger(ledgerType,ledgername,customerName);
	      String supplierName=(String)session.getAttribute("ledgername");
	    String  getTotalAmount=purchaseServiceI.gettotalAmount(supplierName);
	     String getReminingAmount=purchaseServiceI.getRemainingAmounts(supplierName);
	      System.out.println("suppliername"+supplierName);
	    
    	  getladgerNamelist=purchaseServiceI.getlistname();
    	  model.addAttribute("getladgerNamelist",getladgerNamelist);
	   	  model.addAttribute("purchaseLedgerlist",purchaseLedgerlist);
	   	  model.addAttribute("getTotalAmount", getTotalAmount);
	   	  model.addAttribute("getReminingAmount",getReminingAmount);
	   	  return new ModelAndView("OpenPurchasledgerePage");
	     }
     
     
     //Create report
     @RequestMapping(value="PaidAmount",params="paymentSave")
     public ModelAndView paymentMode(Model model,@ModelAttribute("PaidAmountcmd")SuplierModel suplierModel,HttpServletRequest req,HttpSession session,HttpServletResponse res) throws JRException, NamingException, SQLException, IOException
     {
    	 session=req.getSession();
    	 String supplierName=(String)session.getAttribute("ledgername");
    	 String totalAmount=req.getParameter("TotalAmount");
    	 String remainingAmount=req.getParameter("remainingAmount");
    	 String paidAmount=req.getParameter("paidAmount");
    	  purchaseServiceI.paymentMode(supplierName,totalAmount,remainingAmount,paidAmount,session);
    	  getladgerNamelist=purchaseServiceI.getlistname();
    	  model.addAttribute("getladgerNamelist",getladgerNamelist);
    	  String reportFileName="PaymentReceipt1";
    	  ServletContext context=session.getServletContext();
    	  String banner=context.getRealPath("/images/logo.png");
    	  String nameSupplier=(String) session.getAttribute("suppplerName");
    	  int tot=Integer.parseInt(paidAmount);
    	  String totalpaid=NumberToWord.convertNumberToWords(tot)+" "+"Rupees Only";
    	  HashMap<String, Object> hm=new HashMap<String,Object>();
    	  hm.put("supplierName", supplierName);
    	  hm.put("banner", banner);
    	  hm.put("totalAmount", totalAmount);
    	  hm.put("remainingAmount", remainingAmount);
    	  hm.put("amountInDigit", paidAmount);
    	  hm.put("amountInWord", totalpaid);
    	  PrintJasperReport.printreport(reportFileName, req, res, hm);
    	  
    	  return new ModelAndView("OpenPurchasledgerePage");
     }
     @RequestMapping(value="productName",method=RequestMethod.GET)
     @ResponseBody
     public List<String> productList(String productname)
     {
    	 productnamelist=purchaseServiceI.productList(productname);
    	 return productnamelist;
     }
   //open balacesheet
     @RequestMapping("balaceSheet")
     public ModelAndView openbalance(Model model)
     {
    	 purchasesPrice=purchaseServiceI.getpurchasepriceList();
      String salesPrice=purchaseServiceI.salesPriceList();
       String closingStocklist=purchaseServiceI.closingStockList();
         model.addAttribute("purchasesPrice", purchasesPrice.get(0));
         model.addAttribute("salesPrice", salesPrice);
         model.addAttribute("closingStocklist",closingStocklist);
    	 return new ModelAndView("OpenBalaceSheet");
    
     }
     
     
 
  // reportsupplierPayment
     @RequestMapping(value="printpurchase",params="printsupplier")
     public ModelAndView printpaidAmount(@ModelAttribute("printpurchasecmd")PurchaseLedgerModel purchaseModel,HttpServletRequest req,HttpServletResponse res,HttpSession session) throws JRException, NamingException, SQLException, IOException
     {
    	
    	 session=req.getSession();
    	 String suppliername=(String)session.getAttribute("ledgername");
    	
    	 ServletContext context=session.getServletContext();
	      String banner=context.getRealPath("/images/logo.png");
	      String reportFileName="SupplierPaidDetails";
	      HashMap <String,Object>hm=new HashMap<String,Object>();
   	  		hm.put("supplierName", suppliername);
	      hm.put("banner", banner);
	      PrintJasperReport.printreport(reportFileName, req, res, hm);
   	 
    	 return new ModelAndView("OpenPurchasledgerePage");
     }
//open Final BalanceSheet
	@RequestMapping("finalBalanceSheet")
	public ModelAndView finalBalance(Model model,@ModelAttribute("finalBalanceSheetcmd")SalesModel salesModel,HttpServletRequest req,HttpSession session)
	{
	
	//String netProfit=purchaseServiceI.getnetprofit();
	//model.addAttribute("netProfit", netProfit);
	//String closeingStock=purchaseServiceI.getclosingstock();
	//String totalAmount=closeingStock;
	//model.addAttribute("closeingStock",closeingStock);
	
	//model.addAttribute("totalAmount",totalAmount);
	
		return new ModelAndView("opneFinalBalanceSheet");
	}
	//add finalsheet Item
	@RequestMapping(value="finalBalanceSheet",params="AddLadger")
	public ModelAndView addItem(Model model,@ModelAttribute("finalBalanceSheetcmd")AssetModel assetModel,HttpServletRequest req,HttpSession session) throws ParseException
	{
		System.out.println("first");
		String todate12=req.getParameter("todate");
		  session=req.getSession();
	  SimpleDateFormat format1=new SimpleDateFormat("yyyy/MM/dd HH:mm");
	  Date todate123=format1.parse(todate12);
	  java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
	  String todate=sdf.format(todate123);
	      session.setAttribute("toDate", todate);
		String openingBalance=req.getParameter("openingBalance");
		String toDate=req.getParameter("toDate");
		String fromDate=req.getParameter("fromDate");
		System.out.println("toDAte"+toDate);
		System.out.println("fromDate"+fromDate);
		
		session.setAttribute("toDate", toDate);
		session.setAttribute("fromDate", fromDate);
		
		
		
		String total=req.getParameter("totAmount");
		
		String addItemName=req.getParameter("addladger");
		
		String addAmount=req.getParameter("Amount");
	String totalAm=(String) session.getAttribute("totalAAa");
	
	  double totalAmout=(Double.parseDouble(total))+(Double.parseDouble(addAmount)+(Double.parseDouble(totalAm)));
		 session.setAttribute("total", totalAmout);
	  assetModel.setAddAmount(Double.parseDouble(addAmount));
	  assetModel.setAssetDate(todate);
	  assetModel.setAddItemName(addItemName);
	 
	  purchaseServiceI.addItem(assetModel,totalAmout,openingBalance);
	  session.setAttribute("totalAAa", "0.0");
	  
	  
	     String todat=(String)session.getAttribute("toDate");
		String fromDate1=(String) session.getAttribute("fromDate");
		System.out.println("toDate11"+todat);
		System.out.println("fromDate"+fromDate1);
		model.addAttribute("todate", todat);
		model.addAttribute("fromDate1",fromDate1);
		
	  System.out.println("sssssssssss");
	  double  closeingStock=(double) session.getAttribute("closeingStock");
		double netProfit=(double) session.getAttribute("netProfit");
	 
	  model.addAttribute("closeingStock",closeingStock);
	  session.setAttribute("totalAmout", totalAmout);
	  session.setAttribute("todate", todate12);
	  session.setAttribute("openingBalance", openingBalance);
	String opening= (String) session.getAttribute("openingBalance");
	  String todate1=(String) session.getAttribute("todate");
	System.out.println("ksas");
	  double amount=(double) session.getAttribute("totalAmout");
	  model.addAttribute("amount",amount);
	  double totalAmount=closeingStock;
		model.addAttribute("todate1",todate1);
		model.addAttribute("opening",opening);
	
		model.addAttribute("netProfit", netProfit);
		model.addAttribute("totalAmount",totalAmount);
		return new ModelAndView("opneFinalBalanceSheet");
	}
	//lessItem
	@RequestMapping(value="lessfinalBalance",params="lessItem")
	public ModelAndView lessItems(Model model,@ModelAttribute("finalBalanceless")AssetModel1 assetModel,HttpServletRequest req,HttpSession session) throws ParseException
	{
		System.out.println("sssss");
		session=req.getSession();
		String assetDate12=(String) session.getAttribute("todate");
		  SimpleDateFormat format1=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		  Date assetDate1=format1.parse(assetDate12);
		  java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
		  String assetDate=sdf.format(assetDate1);
		String totalAmout=req.getParameter("lessAmount");
		String lessAmount=req.getParameter("amountless");
		String lessItemName=req.getParameter("lessLager");
		
		
		
		
		Double totalAmount=(Double.parseDouble(totalAmout))-(Double.parseDouble(lessAmount));
		session.setAttribute("total", totalAmount);
		 session.setAttribute("totalAmout", totalAmout);
		assetModel.setAssetDate(assetDate);
		assetModel.setLessAmount(Double.parseDouble(lessAmount));
		assetModel.setLessItem(lessItemName);
		purchaseServiceI.lessItem(assetModel,totalAmount);
		String opening= (String) session.getAttribute("openingBalance");
		    String todate11=(String)session.getAttribute("toDate");
			String fromDate1=(String) session.getAttribute("fromDate");
			System.out.println("toLessda"+todate11);
			System.out.println("fromDless"+fromDate1);
			model.addAttribute("todate", todate11);
			model.addAttribute("fromDate1",fromDate1);
			
		model.addAttribute("opening",opening);
		double  closeingStock=(double) session.getAttribute("closeingStock");
		double netProfit=(double) session.getAttribute("netProfit");
		 model.addAttribute("closeingStock",closeingStock);
		 double totalAmount1=closeingStock;
		model.addAttribute("amount",totalAmount);
		model.addAttribute("totalAmount",totalAmount1);
		model.addAttribute("todate1", assetDate12);
		
		model.addAttribute("netProfit", netProfit);
		return new ModelAndView("opneFinalBalanceSheet");
	}
	//add lablites
	@RequestMapping(value="assetFinalBalance",params="AddLadger")
	public ModelAndView finalBalance(Model model,@ModelAttribute("assetFinalBalancecmd")Labilites labilites,HttpServletRequest req,HttpSession session) throws ParseException
	
	{
		
		System.out.println("de");
		session=req.getSession();
		String labilitesdate1=req.getParameter("todate");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date d=sdf.parse(labilitesdate1);
		SimpleDateFormat date=new SimpleDateFormat("dd/MM/yyyy");
		String labilitesdate=date.format(d);
		System.out.println("labilitesdate"+labilitesdate);
		String addItem=req.getParameter("addladger");
		System.out.println("addItem"+addItem);
		String addAmount=req.getParameter("amount");
		System.out.println("addAmount"+addAmount);
		 String todate=(String)session.getAttribute("toDate");
			String fromDate=(String) session.getAttribute("fromDate");
			
			SimpleDateFormat ds=new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Date ds1=ds.parse(todate);
			Date ds2=ds.parse(fromDate);
			SimpleDateFormat sdfs=new SimpleDateFormat("dd/MM/yyyy");
			String toDates=sdfs.format(ds1);
			String toFroms=sdf.format(ds2);
			
	     String totalLablitesA=purchaseServiceI.gettotalA(toDates,toFroms);
		    System.out.println("ss"+totalLablitesA);
		String totalAmount=req.getParameter("totalAmount");
		System.out.println("totalAmount"+totalAmount);
        Double totAmount=(Double.parseDouble(totalAmount))+(Double.parseDouble(addAmount));
        System.out.println("totAmount");
        labilites.setAddItem(addItem);
        labilites.setLabDate(labilitesdate);
        session.setAttribute("labilitesdate", labilitesdate1);
        labilites.setAddAmount(Double.parseDouble(addAmount));
        purchaseServiceI.addLablites(labilites,totAmount);
        double totAmountAsset=(double) session.getAttribute("total");
        double  closeingStock=(double) session.getAttribute("closeingStock");
		double netProfit=(double) session.getAttribute("netProfit");
        model.addAttribute("amount",totAmountAsset);
        String todate11=(String)session.getAttribute("toDate");
		String fromDate1=(String) session.getAttribute("fromDate");
		model.addAttribute("todate", todate11);
		model.addAttribute("fromDate1",fromDate1);
		model.addAttribute("netProfit", netProfit);
        String opening= (String) session.getAttribute("openingBalance");
		model.addAttribute("opening",opening);
        model.addAttribute("closeingStock",closeingStock);
        model.addAttribute("totalAmount",totAmount);
        model.addAttribute("todate1",labilitesdate1);
		return new ModelAndView("opneFinalBalanceSheet");
	}
    // less Items
	@RequestMapping(value="lablitesLessItem",params="lessAmount")
	public ModelAndView lablitesLess(Model model,@ModelAttribute("lablitesLessItemcmd")Labilites2 labilites,HttpServletRequest req,HttpSession session) throws ParseException
	{
		String lessItem=req.getParameter("lessLager");
		String lessAmount=req.getParameter("lessAmount");
		session=req.getSession();
		String lablitesDate12=(String) session.getAttribute("labilitesdate");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date d=sdf.parse(lablitesDate12);
		SimpleDateFormat date=new SimpleDateFormat("dd/MM/yyyy");
		String labilitesdate=date.format(d);
		
		
		String totalAmount=req.getParameter("totalAmount");
		Double totA=(Double.parseDouble(totalAmount))-(Double.parseDouble(lessAmount));
		
		labilites.setLabDate(labilitesdate);
		labilites.setLessAmmount(Double.parseDouble(lessAmount));
		labilites.setLessItem(lessItem);
		purchaseServiceI.lesslabItem(labilites);
		 
	        double  closeingStock=(double) session.getAttribute("closeingStock");
		System.out.println("tot"+totA);
		
		double netProfit=(double) session.getAttribute("netProfit");
		model.addAttribute("netProfit", netProfit);
        String opening= (String) session.getAttribute("openingBalance");
		model.addAttribute("opening",opening);
		model.addAttribute("closeingStock",closeingStock);
	    model.addAttribute("todate1",lablitesDate12);
		model.addAttribute("totalAmount",totA);
		    String todate11=(String)session.getAttribute("toDate");
			String fromDate1=(String) session.getAttribute("fromDate");
			model.addAttribute("todate", todate11);
			model.addAttribute("fromDate1",fromDate1);
		   double totAmountAsset=(double) session.getAttribute("total");
	        model.addAttribute("amount",totAmountAsset);
	
		return new ModelAndView("opneFinalBalanceSheet");
	}
	//purchaseBillReport Day of Book
	@RequestMapping(value="printPurchaseBill",params="printbill")
	public ModelAndView printpurchase(Model model,@ModelAttribute("printpurchasecmd")PurchaseModel purchaseModel,HttpServletRequest req,HttpServletResponse res,HttpSession session) throws JRException, NamingException, SQLException, IOException
	{
		String billnumber=req.getParameter("billid");
		System.out.println("bill"+billnumber);
		ServletContext context=session.getServletContext();
		String banner=context.getRealPath("/images/logo.png");
		String reportFileName="purchaseBill";
		HashMap<String, Object> hm=new HashMap<String,Object>();
		hm.put("Billid", billnumber);
		hm.put("banner", banner);
		PrintJasperReport.printreport(reportFileName, req, res, hm);
		return new ModelAndView("OpenPurchasledgerePage");
	}
	@RequestMapping(value="getitemId",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getitemlist(String productitem)
	{
		List<String> getitemList=new ArrayList<>();
		getitemList=purchaseServiceI.getitemlist(productitem);
		return getitemList; 
	}
	@RequestMapping("finalBalanceReport")
	public ModelAndView finalbalanceReport(Model model,@ModelAttribute("FinalBalanceR")PurchaseModel purchaseModel,HttpServletRequest req,HttpSession session)
	{
		List<String> openingBalance=new ArrayList<>();
		List<String> netProfitL=new ArrayList<>();
		List<String> addLablites=new ArrayList<>();
		List<String> lessLablites=new ArrayList<>();
		List<String> closingStock=new ArrayList<>();
		List<String> addAsset=new ArrayList<>();
		List<String> lessAsesst=new ArrayList<>();
		session=req.getSession();
		String lablitesDate12=(String) session.getAttribute("toDate");
		System.out.println("lab"+lablitesDate12);
		openingBalance=purchaseServiceI.opeingBalance(lablitesDate12);
		netProfitL=purchaseServiceI.netprofit(lablitesDate12);
		addLablites=purchaseServiceI.addlablites(lablitesDate12);
		lessLablites=purchaseServiceI.lessLablitesl(lablitesDate12);
		model.addAttribute("openingBalance",openingBalance);
		model.addAttribute("netProfitL",netProfitL);
		model.addAttribute("addLablites",addLablites);
		model.addAttribute("lessLablites",lessLablites);
		return new ModelAndView("finalReportBalance");
	}
	@RequestMapping(value="FinalReport",method=RequestMethod.GET)
	@ResponseBody
	public List<String> finalReport(String opeining,String netProfit,String totalam, String toDate,String fromdate,String Dates,HttpServletRequest req,HttpServletResponse res,HttpSession session) 
	{
	System.out.println("aaaa");
		/*ServletContext context=session.getServletContext();
		String banner=context.getRealPath("/images/logo.png");
		SimpleDateFormat ds=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date ds1=ds.parse(toDate);
		Date ds2=ds.parse(fromdate);
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String toDates=sdf.format(ds1);
		String toFroms=sdf.format(ds2);
		
		String filename="lablites";
		HashMap<String, Object> hm=new HashMap<String,Object>();
		hm.put("banner", banner);
		hm.put("OpeningAmount", opeining);
		hm.put("Netprofit",netProfit);
		hm.put("totalA",totalam);
		hm.put("toDate", toDates);
		hm.put("fromDate", toFroms);
	    hm.put("Datess", "Dates");
		 PrintJasperReport.printreport(filename, req, res, hm);*/
		return null;
	}
	@RequestMapping(value="lessfinalBalance",params="Print")
	public ModelAndView less(Model  model,@ModelAttribute("finalBalanceless")PurchaseModel purchaseModel,HttpServletRequest req,HttpServletResponse res,HttpSession session) throws ParseException, JRException, NamingException, SQLException, IOException
	{
		session=req.getSession();
		 String todate11=(String)session.getAttribute("toDate");
			String fromDate1=(String) session.getAttribute("fromDate");
		ServletContext context=session.getServletContext();
		String Dates=req.getParameter("insertDate");
		String banner=context.getRealPath("/images/logo.png");
		  String opening= (String) session.getAttribute("openingBalance");
			double netProfit=(double) session.getAttribute("netProfit");
			   double totAmountAsset=(double) session.getAttribute("total");
		SimpleDateFormat ds=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date ds1=ds.parse(todate11);
		Date ds2=ds.parse(fromDate1);
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String toDates=sdf.format(ds1);
		String toFroms=sdf.format(ds2);
		String finalAmount=req.getParameter("finalAmount");
		String filename="lablites";
		HashMap<String, Object> hm=new HashMap<String,Object>();
		hm.put("banner", banner);
		hm.put("OpeningAmount", opening);
		hm.put("Netprofit",netProfit);
		hm.put("totalA",finalAmount);
		hm.put("toDate", toDates);
		hm.put("fromDate", toFroms);
	    hm.put("Datess", Dates);
		 PrintJasperReport.printreport(filename, req, res, hm);
		return new ModelAndView("finalReportBalance");
	}
	/*@RequestMapping(value="lablitesLessItem",params="printLablites")
	public ModelAndView  libites(Model model,@ModelAttribute("lablitesLessItemcmd")PurchaseModel purchaseModel,HttpServletRequest req,HttpServletResponse res,HttpSession session) throws JRException, NamingException, SQLException, IOException
	{
		//String totalAmount=req.getParameter("totalAmount");
		//System.out.println("tot"+totalAmount);
		System.out.println("ssdssasds");
		String totalAmount="0";
	    String todate11=(String)session.getAttribute("toDate");
	    System.out.println("todate"+todate11);
	    String fromDate1=(String) session.getAttribute("fromDate");
	    System.out.println("fromdate"+fromDate1);
		  double  closeingStock=(double) session.getAttribute("closeingStock");
		  System.out.println("cl"+closeingStock);
		  String reportFileName="asset";
		  HashMap<String, Object> hm=new HashMap<String,Object>();
		  hm.put("totalA", totalAmount);
		  hm.put("toDate", todate11);
		  hm.put("fromDate", fromDate1);
		  hm.put("closingA", closeingStock);
		  PrintJasperReport.printreport(reportFileName, req, res, hm);
		return new ModelAndView("finalReportBalance");
	}
	*/
	@RequestMapping(value="lablitesLessItem",params="printLa")
	public ModelAndView printlabliites(Model model,@ModelAttribute("lablitesLessItemcmd")PurchaseModel purchaseModel,HttpServletRequest req,HttpServletResponse res,HttpSession session) throws JRException, NamingException, SQLException, IOException, ParseException
	{
		
		String totalAmount=req.getParameter("totalAmount");
		System.out.println("tot"+totalAmount);
		
	    String todate11=(String)session.getAttribute("toDate");
	    System.out.println("todate"+todate11);
	    String fromDate1=(String) session.getAttribute("fromDate");
		SimpleDateFormat ds=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date ds1=ds.parse(todate11);
		Date ds2=ds.parse(fromDate1);
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String toDates=sdf.format(ds1);
		String toFroms=sdf.format(ds2);
	    System.out.println("fromdate"+fromDate1);
		  double  closeingStock=(double) session.getAttribute("closeingStock");
		  System.out.println("cl"+closeingStock);
		  String reportFileName="asset";
		  HashMap<String, Object> hm=new HashMap<String,Object>();
		  hm.put("totalA", totalAmount);
		  hm.put("toDate", toDates);
		  hm.put("fromDate", toFroms);
		  hm.put("closingA", closeingStock);
		  PrintJasperReport.printreport(reportFileName, req, res, hm);
		return new ModelAndView("finalReportBalance");
		
		
	}
	@RequestMapping(value="gettotalA",method=RequestMethod.GET)
	@ResponseBody
	public List<String> gettotalA(String totAmount,HttpServletRequest req,HttpSession session) throws ParseException
	{
		List<String> gettotalA=new ArrayList<>();
		session=req.getSession();
		 String todate=(String)session.getAttribute("toDate");
			String fromDate=(String) session.getAttribute("fromDate");
			
			SimpleDateFormat ds=new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Date ds1=ds.parse(todate);
			Date ds2=ds.parse(fromDate);
			SimpleDateFormat sdfs=new SimpleDateFormat("dd/MM/yyyy");
			String toDates=sdfs.format(ds1);
			String toFroms=sdfs.format(ds2);
			gettotalA=purchaseServiceI.getTotalA(toDates,toFroms);
			return gettotalA;
	}
}
