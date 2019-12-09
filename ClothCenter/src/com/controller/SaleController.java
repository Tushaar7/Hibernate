package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.model.CustomerModel;
import com.model.PurchaseModel;
import com.model.SalesBillingModel;
import com.model.SalesModel;
import com.model.StockModel;
import com.serviceI.ProductServiceI;
import com.serviceI.PurchaseServiceI;
import com.serviceI.SaleServiceI;
import com.util.NumberToWord;
import com.util.PrintJasperReport;

@Controller
public class SaleController {
	@Autowired
	private PurchaseServiceI purchaseServiceI;
	@Autowired
	private SaleServiceI saleService;
	@Autowired
	private ProductServiceI productServiceI;
	private HashMap Productnamelistss = new HashMap<>();
	private HashMap subproductlist = new HashMap<>();
	List<String> paymentmodelist = new ArrayList<String>();
	List<String> getmaxsaleid = new ArrayList<String>();
	List<String> getStockSummary = new ArrayList<String>();
	List<String> getSalesDetailslist = new ArrayList<String>();
	private HashMap getladgerCustomerNamelist = new HashMap<>();
	private List<String> getSalesLadgerlist = new ArrayList<>();
	private List<String> getSalesTotalA = new ArrayList<>();
	private List<String> getSalesPaidA = new ArrayList<>();
	private List<String> getSalesid = new ArrayList<>();
	private List<String> productCodelist = new ArrayList<>();
	private List<String> listProductCode = new ArrayList<>();
	private List<String> getSalesList = new ArrayList<>();

	@RequestMapping("SalesFrom")
	public ModelAndView openpage(Model model,
			@ModelAttribute("SaleItemcmd") StockModel stockmodel,
			HttpServletRequest req, HttpSession session) {
		session = req.getSession();
		session.invalidate();
		paymentmodelist = getpaymentmode();
		model.addAttribute("paymentmodelist", paymentmodelist);
		Productnamelistss = productServiceI.getsubproductlist();
		getSalesid = saleService.getsalesid();
		model.addAttribute("getSalesid", getSalesid.get(0));
		Double totalAmount = (double) 0;
		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("Productnamelistss", Productnamelistss);
		int salesid = 0;
		model.addAttribute("salesid", salesid);
		subproductlist = purchaseServiceI.getsubproductid();
		model.addAttribute("subproductlist", subproductlist);
		productCodelist = saleService.getproductCodelist();
		// System.out.println("codelist"+productCodelist);
		model.addAttribute("productCodelist", productCodelist);
		return new ModelAndView("OpenSaleFrom");

	}

	public List<String> getpaymentmode() {
		paymentmodelist.clear();
		paymentmodelist.add("Credit");
		paymentmodelist.add("Cash");
		return paymentmodelist;
	}

	@RequestMapping(value = "getMaxSaleId", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getmaxsaleid() {
		getmaxsaleid = saleService.getmaxsaleid();

		return getmaxsaleid;

	}

	@RequestMapping(value = "SaleFrom", params = "save")
	public ModelAndView addsale(Model model,
			@ModelAttribute("SaleFromcmd") SalesBillingModel salesBillingModel,
			CustomerModel customerModel, SalesModel salesModel,
			HttpServletRequest req) {

		String customername = req.getParameter("CustomerModel.customername");
		String totalAmount = req.getParameter("totalAmount");
		String paidAmount = req.getParameter("paidAmount");
		String paymentMode = req.getParameter("paymentmode");
		salesBillingModel.setPaymentmode(paymentMode);

		customerModel.setCustomername(customername);
		List productTypelist = salesModel.getProductType();
		List productNamelist = salesModel.getProductname();
		List quntitylist = salesModel.getQuantity();
		List Pricelist = salesModel.getProductprice();
		List amountlist = salesModel.getAmount();

		saleService.addsale(totalAmount, paidAmount, customerModel,
				productTypelist, productNamelist, quntitylist, Pricelist,
				amountlist, salesBillingModel);
		paymentmodelist = getpaymentmode();
		model.addAttribute("paymentmodelist", paymentmodelist);
		Productnamelistss = productServiceI.getsubproductlist();
		model.addAttribute("Productnamelistss", Productnamelistss);
		subproductlist = purchaseServiceI.getsubproductid();
		model.addAttribute("subproductlist", subproductlist);
		return new ModelAndView("OpenSaleFrom");
	}

	// Sales product
	@RequestMapping(value = "SalesItem", params = "Sales")
	public ModelAndView sales(Model model,
			@ModelAttribute("SaleItemcmd") StockModel stockmodel,
			SalesModel salesModel, HttpServletRequest req, HttpSession session) {
		System.out.println("sssssssfjnjeq");
		String productType = req.getParameter("productType");
		String productname = req.getParameter("productname");
		String quntity = req.getParameter("quntity");
		String price = req.getParameter("Price");
		String amount4 = req.getParameter("amount");
		Double amount2=Double.parseDouble(amount4);
		String amount=String.valueOf(amount2);
		String outOfBound = amount.substring(amount.indexOf("."));
		if (outOfBound.length() >= 3) {
			 amount = amount.substring(0, amount.indexOf('.') + 3);
		} 

		System.out.println("amount" + amount);
		String totalAmount = req.getParameter("TotalAmount");
		String saleId = req.getParameter("billid");
		String productCode = req.getParameter("purchaseCode");
		Double Amounttot = (Double.parseDouble(totalAmount))
				+ (Double.parseDouble(amount));
		System.out.println("amounttot");
		String tot1 = String.valueOf(Amounttot);
		String totOut=tot1.substring(tot1.indexOf("."));
		if(totOut.length()>=3)
		{
			String tot2 = tot1.substring(0, tot1.indexOf('.') + 3);
			 Amounttot = Double.parseDouble(tot2);
		}
		
		String customerName = req.getParameter("customerName");
		session = req.getSession();
		session.setAttribute("customerName", customerName);
		try {
			saleService.sales(productType, productname, quntity, price, amount,
					totalAmount, saleId, productCode, customerName);
			getSalesList = saleService.getAllSalesList(saleId);
		} catch (NullPointerException e) {
			return new ModelAndView("OpenSaleFrom");
		}
		int salesid1 = 0;
		model.addAttribute("salesid", salesid1);
		model.addAttribute("getSalesList", getSalesList);
		model.addAttribute("getSalesid", saleId);
		model.addAttribute("totalAmount", Amounttot);
		model.addAttribute("customerName", customerName);
		Productnamelistss = productServiceI.getsubproductlist();
		model.addAttribute("Productnamelistss", Productnamelistss);
		subproductlist = purchaseServiceI.getsubproductid();
		model.addAttribute("subproductlist", subproductlist);
		productCodelist = saleService.getproductCodelist();
		// System.out.println("codelist"+productCodelist);
		model.addAttribute("productCodelist", productCodelist);
		return new ModelAndView("OpenSaleFrom");
	}

	// updateSalesProduct
	@RequestMapping(value = "SalesItem", params = "updatesale")
	public ModelAndView update(Model model,
			@ModelAttribute("SaleItemcmd") SalesModel salesModel,
			HttpServletRequest req, HttpSession session) {
		String billid = req.getParameter("billid");
		String salesid = req.getParameter("saleid");
		String totAmount = req.getParameter("amounttot");
		String customerName = req.getParameter("customerName");
		String productCode = req.getParameter("purchaseCode");
		String productType = req.getParameter("productType");
		String productItem = req.getParameter("productname");
		String quantity = req.getParameter("quntity");
		String price = req.getParameter("Price");
		String amountd1 = req.getParameter("amount");
		double amount23=Double.parseDouble(amountd1);
		String amount=String.valueOf(amount23);
		String outOfAmount=amount.substring(amount.indexOf("."));
		if(outOfAmount.length()>=3)
		{
			 amount = amount.substring(0, amount.indexOf('.') + 3);
		}
		
		
		double totalAmount = (Double.parseDouble(totAmount) + (Double
				.parseDouble(amount)));
		 String tot1=String.valueOf(totalAmount);
				 String outofbond=tot1.substring(tot1.indexOf("."));
				if (outofbond.length()>=3)
				{
					String tot2=tot1.substring(0, tot1.indexOf('.')+3);
					  totalAmount=Double.parseDouble(tot2);
				}
		
		saleService.updateSale(salesid, totAmount, customerName, productCode,
				productType, productItem, quantity, price, amount, billid);
		paymentmodelist = getpaymentmode();
		getSalesList = saleService.getAllSalesList(billid);
		model.addAttribute("getSalesList", getSalesList);
		model.addAttribute("paymentmodelist", paymentmodelist);
		Productnamelistss = productServiceI.getsubproductlist();
		model.addAttribute("Productnamelistss", Productnamelistss);
		subproductlist = purchaseServiceI.getsubproductid();
		model.addAttribute("subproductlist", subproductlist);
		model.addAttribute("totalAmount", totalAmount);
		int salesid1 = 0;
		model.addAttribute("customerName", customerName);
		model.addAttribute("salesid", salesid1);
		model.addAttribute("getSalesid", billid);
		session = req.getSession();
		session.setAttribute("customerName", customerName);
		productCodelist = saleService.getproductCodelist();
		model.addAttribute("productCodelist", productCodelist);
		return new ModelAndView("OpenSaleFrom");
	}

	// Salesproduct
	@RequestMapping(value = "SaleFrom12", params = "save")
	public ModelAndView saveproduct(Model model,
			@ModelAttribute("SaleFromcmd") StockModel stockModel,
			HttpServletRequest req, HttpSession session, HttpServletResponse res)
			throws JRException, NamingException, SQLException, IOException {
		String discount = req.getParameter("Discount");
		String paymentMode = req.getParameter("PaymentMode");
		String paidAmount = req.getParameter("paidAmount");
		String totalAmount = req.getParameter("TotalAmount");
		session = req.getSession();
		String customerName = (String) session.getAttribute("customerName");
		System.out.println("sssssssss");
		saleService.salesSave(discount, paymentMode, paidAmount, totalAmount,
				customerName);

		Productnamelistss = productServiceI.getsubproductlist();
		getSalesid = saleService.getsalesid();
		String id = saleService.getsaleid();
		model.addAttribute("getSalesid", getSalesid.get(0));
		Double totalAmo = (double) 0;
		model.addAttribute("totalAmount", totalAmo);
		model.addAttribute("Productnamelistss", Productnamelistss);
		String fileReportname = "saleReport";
		ServletContext context = session.getServletContext();
		String banner = context.getRealPath("/images/logo.png");
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("Billid", id);
		hm.put("supplierName", customerName);
		hm.put("discount", discount);
		hm.put("totAmount", totalAmount);
		hm.put("banner", banner);
		PrintJasperReport.printreport(fileReportname, req, res, hm);
		subproductlist = purchaseServiceI.getsubproductid();
		model.addAttribute("subproductlist", subproductlist);
		productCodelist = saleService.getproductCodelist();
		// System.out.println("codelist"+productCodelist);
		model.addAttribute("productCodelist", productCodelist);

		return new ModelAndView("OpenSaleFrom");
	}

	/* Open stock page */
	@RequestMapping("Openstock")
	public ModelAndView openstock(Model model) {
		// List<String>totalStockAmount=new ArrayList<>();
		String totalStockAmount = saleService.gettotalStockAmount();
		String totStock=totalStockAmount.substring(totalStockAmount.indexOf("."));
		if(totStock.length()>=3)
		{
			 totalStockAmount = totalStockAmount.substring(0,
					totalStockAmount.indexOf('.') + 3);
		}
		
		getStockSummary = saleService.getstocklist();
		model.addAttribute("totalStockAmount1", totalStockAmount);
		model.addAttribute("getStockSummary", getStockSummary);
		return new ModelAndView("OpenStockPage");
	}

	// Open SalesDetails
	@RequestMapping("OpenSalesfromDetails")
	public ModelAndView opensalespage(Model model) {
		// List<String> getSalesAmount=new ArrayList<>();
		String getSalesAmount = saleService.getsalesAmount();

		getSalesDetailslist = saleService.getsalesDetails();
		model.addAttribute("getSalesDetailslist", getSalesDetailslist);
		model.addAttribute("getSalesAmount", getSalesAmount);
		return new ModelAndView("OpenSalesPage");
	}

	// open salesLedgerAccount
	@RequestMapping("SalesLedgers")
	public ModelAndView SalesLedger(Model model) {

		getladgerCustomerNamelist = purchaseServiceI.getCustomerNamelist();
		System.out.println("getladgerCustomerNamelist"
				+ getladgerCustomerNamelist);
		model.addAttribute("getladgerCustomerNamelist",
				getladgerCustomerNamelist);

		return new ModelAndView("OpenSalesLedgers");
	}

	@RequestMapping(value = "SalesLedger", params = "Serach")
	public ModelAndView salesLedger(Model model,
			@ModelAttribute("SalesLedgercmd") CustomerModel customerModel,
			HttpServletRequest req, HttpSession session) {
		String customerName = req.getParameter("ledgerName");
		getSalesLadgerlist = saleService.getLadgerList(customerName);
		session = req.getSession();
		session.setAttribute("customerName", customerName);
		getladgerCustomerNamelist = purchaseServiceI.getCustomerNamelist();
		String customerN = (String) session.getAttribute("customerName");
		  String getSalesTotalA = purchaseServiceI.getSalesTotalAmount(customerN);
		String getSalesPaidA = purchaseServiceI.getSalesPaidAmount(customerN);
		//model.addAttribute("getSalesTotalA", getSalesTotalA.get(0));
		model.addAttribute("getSalesTotalA", getSalesTotalA);
		model.addAttribute("getSalesPaidA", getSalesPaidA);
		model.addAttribute("getladgerCustomerNamelist",
				getladgerCustomerNamelist);
		model.addAttribute("getSalesLadgerlist", getSalesLadgerlist);
		return new ModelAndView("OpenSalesLedgers");
	}

	@RequestMapping(value = "PaidAmountCust", params = "submit")
	public ModelAndView salespayment(Model model,
			@ModelAttribute("PaidAmountCustcmd") CustomerModel customerModel,
			HttpServletRequest req, HttpSession session, HttpServletResponse res)
			throws JRException, NamingException, SQLException, IOException {
		session = req.getSession();
		String customerName = (String) session.getAttribute("customerName");
		String totalAmount = req.getParameter("TotalAmount");
		String paidAmount = req.getParameter("paidAmount");
		String remainingAmount = req.getParameter("remainingAmount");

		saleService.updatePayment(totalAmount, paidAmount, remainingAmount,
				customerName, session);
		getladgerCustomerNamelist = purchaseServiceI.getCustomerNamelist();
		model.addAttribute("getladgerCustomerNamelist",
				getladgerCustomerNamelist);
		String nameCustomer = (String) session.getAttribute("customerName");
		int tot = Integer.parseInt(paidAmount);
		String totalpaid = NumberToWord.convertNumberToWords(tot) + " "
				+ "Rupees Only";
		ServletContext context = session.getServletContext();
		String banner = context.getRealPath("/images/logo.png");
		String reportFileName = "PaymentReceipt2";
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("supplierName", customerName);
		hm.put("totalAmount", totalAmount);
		hm.put("amountInWord", totalpaid);
		hm.put("remainingAmount", remainingAmount);
		hm.put("amountInDigit", paidAmount);
		hm.put("banner", banner);
		PrintJasperReport.printreport(reportFileName, req, res, hm);

		return new ModelAndView("OpenSalesLedgers");
	}

	@RequestMapping(value = "getproductCode", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getproductCode(String productCode) {
		listProductCode = saleService.getproductcode(productCode);
		return listProductCode;
	}

	@RequestMapping(value = "salesPaidAmount", params = "print")
	public ModelAndView printCustomerPaid(
			@ModelAttribute("SalespaidACmd") SalesModel salemodel,
			HttpServletRequest req, HttpServletResponse res, HttpSession session)
			throws JRException, NamingException, SQLException, IOException {
		String customerName = (String) session.getAttribute("customerName");
		System.out.println("ssss" + customerName);
		ServletContext context = session.getServletContext();
		String banner = context.getRealPath("/images/logo.png");
		String reportFileName = "CustomerPaidDetails";
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("customerName", customerName);
		hm.put("banner", banner);
		PrintJasperReport.printreport(reportFileName, req, res, hm);
		// String nameCustomer=(String) session.getAttribute("customerName");
		return new ModelAndView("OpenSalesLedgers");
	}

	// printSalesbill
	@RequestMapping(value = "printSalesBill", params = "printbill")
	public ModelAndView printsalesbill(
			@ModelAttribute("printSalescmd") SalesModel salesModel,
			HttpServletRequest req, HttpServletResponse res, HttpSession session)
			throws JRException, NamingException, SQLException, IOException {
		String billid = req.getParameter("billid");
		String reportFileName = "salesBill";

		ServletContext context = session.getServletContext();
		String banner = context.getRealPath("/images/logo.png");
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("Billid", billid);
		hm.put("banner", banner);
		PrintJasperReport.printreport(reportFileName, req, res, hm);
		return new ModelAndView("OpenSalesLedgers");
	}

	// StockCode
	@RequestMapping(value = "purchaseAutoComplete", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getpurchasecode(String productCode) {
		System.out.println("in controller pushcae cocee");
		List<String> purchasecodeList = new ArrayList<>();
		purchasecodeList = saleService.getpurchasecode(productCode);
		return purchasecodeList;
	}
}
