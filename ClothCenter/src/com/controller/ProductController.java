package com.controller;

import java.io.IOException;
import java.sql.SQLException;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.NetProfit;
import com.model.ProductModel;
import com.model.SalesModel;
import com.model.Sub_ProductModel;
import com.model.SuplierModel;
import com.serviceI.ProductServiceI;
import com.util.PrintJasperReport;

@Controller
public class ProductController {
	@Autowired
	private ProductServiceI productServiceI;
	List<String> productlist=new ArrayList<String>();
	private List<String> getMaxId=new ArrayList<String>();
	private List<String> getProductMaxId=new ArrayList<String>();
	private HashMap Productnamelistss=new HashMap<>();
	private List<String> getSubProductList=new ArrayList<String>();
	private List<String> getSupplierlist=new ArrayList<String>();
	@RequestMapping("ProductMasterpage")
	public ModelAndView viewproductpage(Model model)
	{
		productlist=productServiceI.getproductlist();
		model.addAttribute("productlist",productlist);
		return new ModelAndView("ViewProductPage");
	}
	@RequestMapping(value="AddProduct",params="save")
	public ModelAndView saveProduct(Model model,@ModelAttribute("addProductcmd")ProductModel productmodel)
	{
		System.out.println("sssssss");
		productServiceI.saveproduct(productmodel);
		productlist=productServiceI.getproductlist();
		model.addAttribute("productlist",productlist);
		return new ModelAndView("ViewProductPage");
	}
	@RequestMapping(value="AddProduct",params="update")
	public ModelAndView updateProduct(Model model,@ModelAttribute("addProductcmd")ProductModel productmodel)
	{
		System.out.println("sssssss");
		productServiceI.updateproduct(productmodel);
		productlist=productServiceI.getproductlist();
		model.addAttribute("productlist",productlist);
		return new ModelAndView("ViewProductPage");
	}
	@RequestMapping(value="AddProduct",params="delete")
	public ModelAndView deleteProduct(Model model,@ModelAttribute("addProductcmd")ProductModel productmodel)
	{
		System.out.println("sssssss");
		productServiceI.deleteproduct(productmodel);
		productlist=productServiceI.getproductlist();
		model.addAttribute("productlist",productlist);
		return new ModelAndView("ViewProductPage");
		
	}
	
	
	
	
	@RequestMapping(value="getMaxProductId",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getMaxProductId()
	{
		getMaxId=productServiceI.getmaxproduct();
		return getMaxId;
		
		
	}
	/*Open SubProductPage*/
	@RequestMapping("subProductMasterpage")
	public ModelAndView sunproductview(Model model)
	{
		
		Productnamelistss=productServiceI.getsubproductlist();
		model.addAttribute("Productnamelistss",Productnamelistss);
		getSubProductList=productServiceI.getsubproduct();
		model.addAttribute("getSubProductList",getSubProductList);
		return new ModelAndView("viewSubProductPage");
		
	}
	
	
	@RequestMapping(value="getMaxsubProductId",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getmaxsubid()
	{
		getProductMaxId=productServiceI.getmaxid();
		return getProductMaxId;
	}
	@RequestMapping(value="addSubProduct",params="save")
	public ModelAndView savesubproduct(Model model,@ModelAttribute("addProductcmd")Sub_ProductModel sbProductModel,HttpServletRequest req)
	{
	
		productServiceI.savesubproduct(sbProductModel);
		Productnamelistss=productServiceI.getsubproductlist();
		model.addAttribute("Productnamelistss",Productnamelistss);
		getSubProductList=productServiceI.getsubproduct();
		System.out.println("listfeach"+getSubProductList);
		model.addAttribute("getSubProductList",getSubProductList);
		return new ModelAndView("viewSubProductPage");
	}
	@RequestMapping(value="addSubProduct",params="update")
	public ModelAndView updatesubproduct(Model model,@ModelAttribute("addProductcmd")Sub_ProductModel sbProductModel)
	{
		productServiceI.updatesubproduct(sbProductModel);
		Productnamelistss=productServiceI.getsubproductlist();
		model.addAttribute("Productnamelistss",Productnamelistss);
		getSubProductList=productServiceI.getsubproduct();
		model.addAttribute("getSubProductList",getSubProductList);
		return new ModelAndView("viewSubProductPage");
	}
	@RequestMapping(value="addSubProduct",params="delete")
	public ModelAndView deletesubproduct(Model model,@ModelAttribute("addProductcmd")Sub_ProductModel sbProductModel)
	{
		productServiceI.deletesubproduct(sbProductModel);
		Productnamelistss=productServiceI.getsubproductlist();
		model.addAttribute("Productnamelistss",Productnamelistss);
		getSubProductList=productServiceI.getsubproduct();
		model.addAttribute("getSubProductList",getSubProductList);
		return new ModelAndView("viewSubProductPage");
	}
	@RequestMapping(value="getProductid",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getproductid(String productname)
	{
		List<String> getproductid=new ArrayList<String>();
		getproductid=productServiceI.getproductid(productname);
		return getproductid;
	}
	
	
	/*Supplier Save update delete Id Feach*/
	@RequestMapping("Supplierpageopen")
	public ModelAndView  supplierpage(Model model)
	{
		getSupplierlist=productServiceI.getsupplierlist();
		model.addAttribute("getSupplierlist",getSupplierlist);
		return new ModelAndView("OpenSupplierpage");
	}
	@RequestMapping(value="getMaxSupplierId",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getmaxsupplierid()
	{
		List<String> getMaxSupplerlist=new ArrayList<String>();
		getMaxSupplerlist=productServiceI.getidlist();
		return getMaxSupplerlist;
	}
	@RequestMapping(value="AddSupplier",params="save")
	public ModelAndView addsupplier(Model model,@ModelAttribute("addSuppliercmd")SuplierModel suplierModel)
	{
		productServiceI.addsupplier(suplierModel);
		getSupplierlist=productServiceI.getsupplierlist();
		model.addAttribute("getSupplierlist",getSupplierlist);
		return new ModelAndView("OpenSupplierpage");
	}
	@RequestMapping(value="AddSupplier",params="delete")
	public ModelAndView deletesupplier(Model model,@ModelAttribute("addSuppliercmd")SuplierModel suplierModel)
	{
		productServiceI.deletesupplier(suplierModel);
		getSupplierlist=productServiceI.getsupplierlist();
		model.addAttribute("getSupplierlist",getSupplierlist);
		return new ModelAndView("OpenSupplierpage");
	}
	@RequestMapping(value="AddSupplier",params="update")
	public ModelAndView updatesupplier(Model model,@ModelAttribute("addSuppliercmd")SuplierModel suplierModel)
	{
		productServiceI.updatesupplier(suplierModel);
		getSupplierlist=productServiceI.getsupplierlist();
		model.addAttribute("getSupplierlist",getSupplierlist);
		return new ModelAndView("OpenSupplierpage");
	}
	//reports profitLoossAccount
	@RequestMapping(value="balanceSheet",params="printLossProfit")
	public ModelAndView printlossprofit(@ModelAttribute("balanceSheetcmd")SalesModel salesModel,NetProfit netprofit,HttpServletRequest req,HttpSession session,HttpServletResponse res) throws JRException, NamingException, SQLException, IOException
	{
		
		String openingStock=req.getParameter("openingStock");
		String purchases=req.getParameter("toPurchase");
		String grossProfit=req.getParameter("toGrossProfit");
		double byGrossProfit=Double.parseDouble(grossProfit);
		double openingStocks=Double.parseDouble(openingStock);
		double purchase=Double.parseDouble(purchases);
		double tot=Double.parseDouble(grossProfit);
		double totalCredit=openingStocks+purchase+tot;
		String toDate=req.getParameter("toDate");
		String formDate=req.getParameter("fromDate");
		String salery=req.getParameter("toSalery");
		String pmcRent=req.getParameter("rent");
		String electricity=req.getParameter("electricity");
		String telephone=req.getParameter("toTelephone");
		String shopExpenses=req.getParameter("toShopExpenses");
		String consultingFees=req.getParameter("consulting");
		String depreciation=req.getParameter("toDepreciation");
		String netProfit=req.getParameter("toNetProfit");
		String sales=req.getParameter("bySale");
		String closingStock=req.getParameter("byClosingStock");
		double totalGrossprofits=(Double.parseDouble(salery))+(Double.parseDouble(pmcRent))+(Double.parseDouble(electricity))+(Double.parseDouble(telephone))+(Double.parseDouble(shopExpenses))+(Double.parseDouble(consultingFees))+(Double.parseDouble(depreciation))+(Double.parseDouble(netProfit));
		double saless=Double.parseDouble(sales);
		double closingStock1=Double.parseDouble(closingStock);
		double totalAmount=saless+closingStock1;
		
		String homeRent=req.getParameter("commission");
		String otherIncome=req.getParameter("income");
		double byHomeRent=Double.parseDouble(homeRent);
		double byotherIncome=Double.parseDouble(otherIncome);
		double totalBalance=byGrossProfit+byHomeRent+byotherIncome;
		String reportFileName="profitLoss";
		session=req.getSession();
		Double netprof=Double.parseDouble(netProfit);
		netprofit.setNetProfit(netprof);
		ServletContext context=session.getServletContext();
		String banner=context.getRealPath("/images/logo.png");
		HashMap<String, Object> hm=new HashMap<String,Object>();
		hm.put("banner", banner);
		hm.put("closingstock", closingStock);
		hm.put("consultingFees", consultingFees);
		hm.put("depreciation", depreciation);
		hm.put("electricity", electricity);
		hm.put("grossProfit", grossProfit);
		hm.put("homerent", homeRent);
		hm.put("netProfit", netProfit);
		hm.put("openingStock", openingStock);
		hm.put("otherIncome", otherIncome);
		hm.put("pmcRent", pmcRent);
		hm.put("purchases", purchases);
		hm.put("salery", salery);
		hm.put("sales", sales);
		hm.put("formDate", formDate);
		hm.put("toDate", toDate);
		hm.put("shopExpenses", shopExpenses);
		hm.put("telephone", telephone);
		hm.put("totalA", totalCredit);
		hm.put("totalAmount", totalBalance);
		hm.put("totalGrossProfit", totalGrossprofits);
		hm.put("totalAmou", totalAmount);
		netprofit.setClosingbalance(Double.parseDouble(closingStock));
		Date d=new Date();
	   SimpleDateFormat dates=new SimpleDateFormat("yyyy/MM/dd HH:mm");
	   String currentDate=dates.format(d);
	     netprofit.setTodate(currentDate);
		productServiceI.saveNetProfit(netprofit);
		PrintJasperReport.printreport(reportFileName, req, res, hm);
		return new ModelAndView("OpenBalaceSheet");
	}
	
	//getPurchaseBalance
	@RequestMapping(value="getpurchaseBalance",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getpurchaseBalance(String todate,String fromdate)
	{
		List <String>getpurchasebalance=new ArrayList<>();
		getpurchasebalance=productServiceI.getpurchasebalance(todate,fromdate);
		return getpurchasebalance;
	}
	@RequestMapping(value="getsalesBalance",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getsalesBalance(String todate,String fromdate) throws ParseException
	{
		List<String> getsalesBalanceAmount=new ArrayList<>();
		
  
		getsalesBalanceAmount=productServiceI.getsalesBalance(todate,fromdate);
		
		return getsalesBalanceAmount;
	}
	@RequestMapping(value="getClosingBalance",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getClosingBalance(String todate,String fromdate)
	{
		List<String> getclosingbalance=new ArrayList<>();
		getclosingbalance=productServiceI.getClosing(todate,fromdate);
		
		return getclosingbalance;
	}
	@RequestMapping(value="gettotalAmount",method=RequestMethod.GET)
	@ResponseBody
	public List<NetProfit> getTotalAmount(String toDate,String fromdate,HttpServletRequest req,HttpSession session,NetProfit netProfit) throws ParseException
	{
		List<NetProfit> getTotoalA=new ArrayList<>();
		SimpleDateFormat ds=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date ds1=ds.parse(toDate);
		Date ds2=ds.parse(fromdate);
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String toDates=sdf.format(ds1);
		String toFroms=sdf.format(ds2);
		String getTotolAmount=productServiceI.gettotalAmounts(toDates, toFroms);
		
		session.setAttribute("totalAAa", getTotolAmount);
		session=req.getSession();
		
		getTotoalA=productServiceI.gettotalAmount(toDate,fromdate);
	
		double netProfits=getTotoalA.get(0).getNetProfit();
	    double closeingStock=getTotoalA.get(0).getClosingbalance();
		session.setAttribute("netProfit", netProfits);
		session.setAttribute("closeingStock", closeingStock);
		return getTotoalA;
	}
}
