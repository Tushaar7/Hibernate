<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<!-- 
Author: Raju sable
-->

<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<script type="text/javascript">
$(document).ready(function(){
	 $('#toDate').datetimepicker({
			
		});
	 $('#fromDate').datetimepicker({
			
		});
	 $("#dateSubmit").click(function(){
		
	var todate=	$("#toDate").val();
	var fromdate=$("#fromDate").val();
	
	$.getJSON('getpurchaseBalance.html',{todate:todate,fromdate:fromdate}).done(function(data){
		var purcha=data[0].price;
		$("#toPurchase").val(data[0].price);
		/* alert(purcha);
		if(purc = purcha.indexOf("."))
		{
           alert("sss");
			$("#toPurchase").val(purcha.substring(0,purc+3));
		}
		else
		{
			alert("ddd");
			
		}
			 */
		
		    
	
	});
	$.getJSON('getsalesBalance.html',{todate:todate,fromdate:fromdate}).done(function(data){
		$("#bySale").val(data[0].amount);
	});
	$.getJSON('getClosingBalance.html',{todate:todate,fromdate:fromdate}).done(function(data){
		$("#byClosingStock").val(data[0].asamounta);
	});
	 });
	$("#openingStock").change(function(){
	var opeining=$("#openingStock").val();
	var purchaseprice=$("#toPurchase").val();
	var totpurchase=parseFloat(opeining)+parseFloat(purchaseprice);
	
	var bysale=$("#bySale").val();
	var byClosingStock=$("#byClosingStock").val();
	var totsales=parseFloat(bysale)+parseFloat(byClosingStock);
	
    var totalA=parseFloat(totsales)-parseFloat(totpurchase);
   
    $("#toGrossProfit").val(totalA);
	});
	$("#toSalery").blur(function(){
		var grossprofit=$("#toGrossProfit").val();
		var salery1=$("#toSalery").val();
		if(salery1=="")
			{
			 salery=0;
			}
		else
			{
			salery=$("#toSalery").val();
			}
		var consulting1=$("#consulting").val();
		if(consulting1=="")
			{
			consulting=0;
			}
		else
			{
			 consulting=$("#consulting").val();
			}
		var commission1=$("#commission").val();
		if(commission1=="")
		{
			commission=0;
		}
		else
			{
			 commission=$("#commission").val();
			}
	var income1=$("#income").val();
	if(income1=="")
		{
		income=0;
		}
	   else
		{
		income=$("#income").val();
		}
		var rent1=$("#rent").val();
		if(rent1=="")
			{
			rent=0;
			}
		else
			{
			 rent=$("#rent").val();
			}
		
		var electricity1=$("#electricity").val();
		if(electricity1=="")
			{
			electricity=0;
			}
		else
			{
			 electricity=$("#electricity").val();
			}
		var toTelephone1=$("#toTelephone").val();
		if(toTelephone1=="")
			{
			toTelephone=0;
			}
		else
			{
			toTelephone=$("#toTelephone").val();
			}
		
		var toShopExpenses1=$("#toShopExpenses").val();
		if(toShopExpenses1=="")
			{
			toShopExpenses=0;
			}
		else
			{
			 toShopExpenses=$("#toShopExpenses").val();
			}
		var toDepreciation1=$("#toDepreciation").val();
		if(toDepreciation1=="")
			{
			toDepreciation=0;
			}
		else
			{
			toDepreciation=$("#toDepreciation").val();
			}
		var totalamo=parseFloat(salery)+parseFloat(rent)+parseFloat(electricity)+parseFloat(toTelephone)+parseFloat(toShopExpenses)+parseFloat(toDepreciation)+parseFloat(consulting);
		var totalAmount=parseFloat(grossprofit)-parseFloat(totalamo);
		var otherincome=parseFloat(income)+parseFloat(commission)+parseFloat(totalAmount);
		$("#toNetProfit").val(otherincome);
	
	});
	$("#rent").blur(function(){
		var grossprofit=$("#toGrossProfit").val();
		var salery1=$("#toSalery").val();
		if(salery1=="")
			{
			 salery=0;
			}
		else
			{
			salery=$("#toSalery").val();
			}
		var consulting1=$("#consulting").val();
		if(consulting1=="")
			{
			consulting=0;
			}
		else
			{
			 consulting=$("#consulting").val();
			}
		var commission1=$("#commission").val();
		if(commission1=="")
		{
			commission=0;
		}
		else
			{
			 commission=$("#commission").val();
			}
	var income1=$("#income").val();
	if(income1=="")
		{
		income=0;
		}
	   else
		{
		income=$("#income").val();
		}
		var rent1=$("#rent").val();
		if(rent1=="")
			{
			rent=0;
			}
		else
			{
			 rent=$("#rent").val();
			}
		
		var electricity1=$("#electricity").val();
		if(electricity1=="")
			{
			electricity=0;
			}
		else
			{
			 electricity=$("#electricity").val();
			}
		var toTelephone1=$("#toTelephone").val();
		if(toTelephone1=="")
			{
			toTelephone=0;
			}
		else
			{
			toTelephone=$("#toTelephone").val();
			}
		
		var toShopExpenses1=$("#toShopExpenses").val();
		if(toShopExpenses1=="")
			{
			toShopExpenses=0;
			}
		else
			{
			 toShopExpenses=$("#toShopExpenses").val();
			}
		var toDepreciation1=$("#toDepreciation").val();
		if(toDepreciation1=="")
			{
			toDepreciation=0;
			}
		else
			{
			toDepreciation=$("#toDepreciation").val();
			}
		var totalamo=parseFloat(salery)+parseFloat(rent)+parseFloat(electricity)+parseFloat(toTelephone)+parseFloat(toShopExpenses)+parseFloat(toDepreciation)+parseFloat(consulting);
		var totalAmount=parseFloat(grossprofit)-parseFloat(totalamo);
		var otherincome=parseFloat(income)+parseFloat(commission)+parseFloat(totalAmount);
		$("#toNetProfit").val(otherincome);
	});
	$("#electricity").blur(function(){
		var grossprofit=$("#toGrossProfit").val();
		var salery1=$("#toSalery").val();
		if(salery1=="")
			{
			 salery=0;
			}
		else
			{
			salery=$("#toSalery").val();
			}
		var consulting1=$("#consulting").val();
		if(consulting1=="")
			{
			consulting=0;
			}
		else
			{
			 consulting=$("#consulting").val();
			}
		var commission1=$("#commission").val();
		if(commission1=="")
		{
			commission=0;
		}
		else
			{
			 commission=$("#commission").val();
			}
	var income1=$("#income").val();
	if(income1=="")
		{
		income=0;
		}
	   else
		{
		income=$("#income").val();
		}
		var rent1=$("#rent").val();
		if(rent1=="")
			{
			rent=0;
			}
		else
			{
			 rent=$("#rent").val();
			}
		
		var electricity1=$("#electricity").val();
		if(electricity1=="")
			{
			electricity=0;
			}
		else
			{
			 electricity=$("#electricity").val();
			}
		var toTelephone1=$("#toTelephone").val();
		if(toTelephone1=="")
			{
			toTelephone=0;
			}
		else
			{
			toTelephone=$("#toTelephone").val();
			}
		
		var toShopExpenses1=$("#toShopExpenses").val();
		if(toShopExpenses1=="")
			{
			toShopExpenses=0;
			}
		else
			{
			 toShopExpenses=$("#toShopExpenses").val();
			}
		var toDepreciation1=$("#toDepreciation").val();
		if(toDepreciation1=="")
			{
			toDepreciation=0;
			}
		else
			{
			toDepreciation=$("#toDepreciation").val();
			}
		var totalamo=parseFloat(salery)+parseFloat(rent)+parseFloat(electricity)+parseFloat(toTelephone)+parseFloat(toShopExpenses)+parseFloat(toDepreciation)+parseFloat(consulting);
		var totalAmount=parseFloat(grossprofit)-parseFloat(totalamo);
		var otherincome=parseFloat(income)+parseFloat(commission)+parseFloat(totalAmount);
		$("#toNetProfit").val(otherincome);
	});
	$("#toTelephone").blur(function(){
		var grossprofit=$("#toGrossProfit").val();
		var salery1=$("#toSalery").val();
		if(salery1=="")
			{
			 salery=0;
			}
		else
			{
			salery=$("#toSalery").val();
			}
		var consulting1=$("#consulting").val();
		if(consulting1=="")
			{
			consulting=0;
			}
		else
			{
			 consulting=$("#consulting").val();
			}
		var commission1=$("#commission").val();
		if(commission1=="")
		{
			commission=0;
		}
		else
			{
			 commission=$("#commission").val();
			}
	var income1=$("#income").val();
	if(income1=="")
		{
		income=0;
		}
	   else
		{
		income=$("#income").val();
		}
		var rent1=$("#rent").val();
		if(rent1=="")
			{
			rent=0;
			}
		else
			{
			 rent=$("#rent").val();
			}
		
		var electricity1=$("#electricity").val();
		if(electricity1=="")
			{
			electricity=0;
			}
		else
			{
			 electricity=$("#electricity").val();
			}
		var toTelephone1=$("#toTelephone").val();
		if(toTelephone1=="")
			{
			toTelephone=0;
			}
		else
			{
			toTelephone=$("#toTelephone").val();
			}
		
		var toShopExpenses1=$("#toShopExpenses").val();
		if(toShopExpenses1=="")
			{
			toShopExpenses=0;
			}
		else
			{
			 toShopExpenses=$("#toShopExpenses").val();
			}
		var toDepreciation1=$("#toDepreciation").val();
		if(toDepreciation1=="")
			{
			toDepreciation=0;
			}
		else
			{
			toDepreciation=$("#toDepreciation").val();
			}
		var totalamo=parseFloat(salery)+parseFloat(rent)+parseFloat(electricity)+parseFloat(toTelephone)+parseFloat(toShopExpenses)+parseFloat(toDepreciation)+parseFloat(consulting);
		var totalAmount=parseFloat(grossprofit)-parseFloat(totalamo);
		var otherincome=parseFloat(income)+parseFloat(commission)+parseFloat(totalAmount);
		$("#toNetProfit").val(otherincome);
	});
	$("#toShopExpenses").blur(function(){
		var grossprofit=$("#toGrossProfit").val();
		var salery1=$("#toSalery").val();
		if(salery1=="")
			{
			 salery=0;
			}
		else
			{
			salery=$("#toSalery").val();
			}
		var consulting1=$("#consulting").val();
		if(consulting1=="")
			{
			consulting=0;
			}
		else
			{
			 consulting=$("#consulting").val();
			}
		var commission1=$("#commission").val();
		if(commission1=="")
		{
			commission=0;
		}
		else
			{
			 commission=$("#commission").val();
			}
	var income1=$("#income").val();
	if(income1=="")
		{
		income=0;
		}
	   else
		{
		income=$("#income").val();
		}
		var rent1=$("#rent").val();
		if(rent1=="")
			{
			rent=0;
			}
		else
			{
			 rent=$("#rent").val();
			}
		
		var electricity1=$("#electricity").val();
		if(electricity1=="")
			{
			electricity=0;
			}
		else
			{
			 electricity=$("#electricity").val();
			}
		var toTelephone1=$("#toTelephone").val();
		if(toTelephone1=="")
			{
			toTelephone=0;
			}
		else
			{
			toTelephone=$("#toTelephone").val();
			}
		
		var toShopExpenses1=$("#toShopExpenses").val();
		if(toShopExpenses1=="")
			{
			toShopExpenses=0;
			}
		else
			{
			 toShopExpenses=$("#toShopExpenses").val();
			}
		var toDepreciation1=$("#toDepreciation").val();
		if(toDepreciation1=="")
			{
			toDepreciation=0;
			}
		else
			{
			toDepreciation=$("#toDepreciation").val();
			}
		var totalamo=parseFloat(salery)+parseFloat(rent)+parseFloat(electricity)+parseFloat(toTelephone)+parseFloat(toShopExpenses)+parseFloat(toDepreciation)+parseFloat(consulting);
		var totalAmount=parseFloat(grossprofit)-parseFloat(totalamo);
		var otherincome=parseFloat(income)+parseFloat(commission)+parseFloat(totalAmount);
		$("#toNetProfit").val(otherincome);
	});
	$("#toDepreciation").blur(function()
	{
		var grossprofit=$("#toGrossProfit").val();
		var salery1=$("#toSalery").val();
		if(salery1=="")
			{
			 salery=0;
			}
		else
			{
			salery=$("#toSalery").val();
			}
		var consulting1=$("#consulting").val();
		if(consulting1=="")
			{
			consulting=0;
			}
		else
			{
			 consulting=$("#consulting").val();
			}
		var commission1=$("#commission").val();
		if(commission1=="")
		{
			commission=0;
		}
		else
			{
			 commission=$("#commission").val();
			}
	var income1=$("#income").val();
	if(income1=="")
		{
		income=0;
		}
	   else
		{
		income=$("#income").val();
		}
		var rent1=$("#rent").val();
		if(rent1=="")
			{
			rent=0;
			}
		else
			{
			 rent=$("#rent").val();
			}
		
		var electricity1=$("#electricity").val();
		if(electricity1=="")
			{
			electricity=0;
			}
		else
			{
			 electricity=$("#electricity").val();
			}
		var toTelephone1=$("#toTelephone").val();
		if(toTelephone1=="")
			{
			toTelephone=0;
			}
		else
			{
			toTelephone=$("#toTelephone").val();
			}
		
		var toShopExpenses1=$("#toShopExpenses").val();
		if(toShopExpenses1=="")
			{
			toShopExpenses=0;
			}
		else
			{
			 toShopExpenses=$("#toShopExpenses").val();
			}
		var toDepreciation1=$("#toDepreciation").val();
		if(toDepreciation1=="")
			{
			toDepreciation=0;
			}
		else
			{
			toDepreciation=$("#toDepreciation").val();
			}
		var totalamo=parseFloat(salery)+parseFloat(rent)+parseFloat(electricity)+parseFloat(toTelephone)+parseFloat(toShopExpenses)+parseFloat(toDepreciation)+parseFloat(consulting);
		var totalAmount=parseFloat(grossprofit)-parseFloat(totalamo);
		var otherincome=parseFloat(income)+parseFloat(commission)+parseFloat(totalAmount);
		$("#toNetProfit").val(otherincome);
	});
	$("#commission").blur(function(){
		var grossprofit=$("#toGrossProfit").val();
		var salery1=$("#toSalery").val();
		if(salery1=="")
			{
			 salery=0;
			}
		else
			{
			salery=$("#toSalery").val();
			}
		var consulting1=$("#consulting").val();
		if(consulting1=="")
			{
			consulting=0;
			}
		else
			{
			 consulting=$("#consulting").val();
			}
		var commission1=$("#commission").val();
		if(commission1=="")
		{
			commission=0;
		}
		else
			{
			 commission=$("#commission").val();
			}
	var income1=$("#income").val();
	if(income1=="")
		{
		income=0;
		}
	   else
		{
		income=$("#income").val();
		}
		var rent1=$("#rent").val();
		if(rent1=="")
			{
			rent=0;
			}
		else
			{
			 rent=$("#rent").val();
			}
		
		var electricity1=$("#electricity").val();
		if(electricity1=="")
			{
			electricity=0;
			}
		else
			{
			 electricity=$("#electricity").val();
			}
		var toTelephone1=$("#toTelephone").val();
		if(toTelephone1=="")
			{
			toTelephone=0;
			}
		else
			{
			toTelephone=$("#toTelephone").val();
			}
		
		var toShopExpenses1=$("#toShopExpenses").val();
		if(toShopExpenses1=="")
			{
			toShopExpenses=0;
			}
		else
			{
			 toShopExpenses=$("#toShopExpenses").val();
			}
		var toDepreciation1=$("#toDepreciation").val();
		if(toDepreciation1=="")
			{
			toDepreciation=0;
			}
		else
			{
			toDepreciation=$("#toDepreciation").val();
			}
		var totalamo=parseFloat(salery)+parseFloat(rent)+parseFloat(electricity)+parseFloat(toTelephone)+parseFloat(toShopExpenses)+parseFloat(toDepreciation)+parseFloat(consulting);
		var totalAmount=parseFloat(grossprofit)-parseFloat(totalamo);
		var otherincome=parseFloat(income)+parseFloat(commission)+parseFloat(totalAmount);
		$("#toNetProfit").val(otherincome);
	});
	$("#consulting").blur(function(){
		var grossprofit=$("#toGrossProfit").val();
		var salery1=$("#toSalery").val();
		if(salery1=="")
			{
			 salery=0;
			}
		else
			{
			salery=$("#toSalery").val();
			}
		var consulting1=$("#consulting").val();
		if(consulting1=="")
			{
			consulting=0;
			}
		else
			{
			 consulting=$("#consulting").val();
			}
		var commission1=$("#commission").val();
		if(commission1=="")
		{
			commission=0;
		}
		else
			{
			 commission=$("#commission").val();
			}
	var income1=$("#income").val();
	if(income1=="")
		{
		income=0;
		}
	   else
		{
		income=$("#income").val();
		}
		var rent1=$("#rent").val();
		if(rent1=="")
			{
			rent=0;
			}
		else
			{
			 rent=$("#rent").val();
			}
		
		var electricity1=$("#electricity").val();
		if(electricity1=="")
			{
			electricity=0;
			}
		else
			{
			 electricity=$("#electricity").val();
			}
		var toTelephone1=$("#toTelephone").val();
		if(toTelephone1=="")
			{
			toTelephone=0;
			}
		else
			{
			toTelephone=$("#toTelephone").val();
			}
		
		var toShopExpenses1=$("#toShopExpenses").val();
		if(toShopExpenses1=="")
			{
			toShopExpenses=0;
			}
		else
			{
			 toShopExpenses=$("#toShopExpenses").val();
			}
		var toDepreciation1=$("#toDepreciation").val();
		if(toDepreciation1=="")
			{
			toDepreciation=0;
			}
		else
			{
			toDepreciation=$("#toDepreciation").val();
			}
		var totalamo=parseFloat(salery)+parseFloat(rent)+parseFloat(electricity)+parseFloat(toTelephone)+parseFloat(toShopExpenses)+parseFloat(toDepreciation)+parseFloat(consulting);
		var totalAmount=parseFloat(grossprofit)-parseFloat(totalamo);
		var otherincome=parseFloat(income)+parseFloat(commission)+parseFloat(totalAmount);
		$("#toNetProfit").val(otherincome);	
	});
	$("#income").blur(function(){
		var grossprofit=$("#toGrossProfit").val();
		var salery1=$("#toSalery").val();
		if(salery1=="")
			{
			 salery=0;
			}
		else
			{
			salery=$("#toSalery").val();
			}
		var consulting1=$("#consulting").val();
		if(consulting1=="")
			{
			consulting=0;
			}
		else
			{
			 consulting=$("#consulting").val();
			}
		var commission1=$("#commission").val();
		if(commission1=="")
		{
			commission=0;
		}
		else
			{
			 commission=$("#commission").val();
			}
	var income1=$("#income").val();
	if(income1=="")
		{
		income=0;
		}
	   else
		{
		income=$("#income").val();
		}
		var rent1=$("#rent").val();
		if(rent1=="")
			{
			rent=0;
			}
		else
			{
			 rent=$("#rent").val();
			}
		
		var electricity1=$("#electricity").val();
		if(electricity1=="")
			{
			electricity=0;
			}
		else
			{
			 electricity=$("#electricity").val();
			}
		var toTelephone1=$("#toTelephone").val();
		if(toTelephone1=="")
			{
			toTelephone=0;
			}
		else
			{
			toTelephone=$("#toTelephone").val();
			}
		
		var toShopExpenses1=$("#toShopExpenses").val();
		if(toShopExpenses1=="")
			{
			toShopExpenses=0;
			}
		else
			{
			 toShopExpenses=$("#toShopExpenses").val();
			}
		var toDepreciation1=$("#toDepreciation").val();
		if(toDepreciation1=="")
			{
			toDepreciation=0;
			}
		else
			{
			toDepreciation=$("#toDepreciation").val();
			}
		var totalamo=parseFloat(salery)+parseFloat(rent)+parseFloat(electricity)+parseFloat(toTelephone)+parseFloat(toShopExpenses)+parseFloat(toDepreciation)+parseFloat(consulting);
		var totalAmount=parseFloat(grossprofit)-parseFloat(totalamo);
		var otherincome=parseFloat(income)+parseFloat(commission)+parseFloat(totalAmount);
		$("#toNetProfit").val(otherincome);
	});
});
</script>  
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN CONTAINER -->
	<div class="page-content">
	<div class="page-container">
	<!-- BEGIN General form PAGE CONTENT-->
	<div class="container-fluid">
		
		<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN VALIDATION STATES-->
						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>Purchase From</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
									<a href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
							<div class="portlet-body form">
						
								
								<!-- BEGIN FORM-->
								<form:form action="balanceSheet.html" commandName="balanceSheetcmd" id="stateForm" class="form-horizontal">
									<div class="portlet-body form">
										
											
											<div class="row-fluid">
												<div class="span4 ">
												<div class="control-group error">
										               <label class="control-label">ToDate</label>
										                 <div class="controls">
											                <input type="text" id="toDate" class="m-wrap span10" readonly="readonly" name="toDate" >
											              </div>
										              </div>
										              </div>
													<div class="span4 ">
												<div class="control-group error">
										               <label class="control-label">fromDate</label>
										                 <div class="controls">
											                <input type="text" id="fromDate" class="m-wrap span10" readonly="readonly" name="fromDate" >
											              </div>
										              </div>
										             
										              </div>
										              
													<!--/span-->
												 <div class="span4">
										              <input type="button" id="dateSubmit" name="dateSubmit" class="btn purple" value="submit">
										              </div>
												</div>
											
												<div class="row-fluid">
												
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >TO Opening Stock</label>
															<div class="controls">
																<input type="text" id="openingStock" class="m-wrap span12" name="openingStock">			
															</div>
														</div>
													</div>
													<div class="span4 ">
														<div class="control-group error">
															<label class="control-label" >By Sales</label>
															<div class="controls">
																<input type="text" id="bySale" class="m-wrap span12" name="bySale" >
															</div>
														</div>
													</div>
													<!--/span-->
												
												</div>
												
												<div class="row-fluid">
												
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >To Purchases</label>
															<div class="controls">
																<input type="text" id="toPurchase" class="m-wrap span12" name="toPurchase" >			
															</div>
														</div>
													</div>
													<div class="span4 ">
														<div class="control-group error">
															<label class="control-label" >By Closing Stock</label>
															<div class="controls">
																<input type="text" id="byClosingStock" class="m-wrap span12" name="byClosingStock" ">
															</div>
														</div>
													</div>
													<!--/span-->
												
												</div>
												<div class="row-fluid">
												
													<div class="span8">
													<div class="control-group error">
															<label class="control-label" >To Gross Profit</label>
															<div class="controls">
																<input type="text" id="toGrossProfit" class="m-wrap span12" name="toGrossProfit">			
															</div>
														</div>
													</div>
												
												
												
												</div>
												
													<div class="row-fluid">
												
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >To Salary</label>
															<div class="controls">
																<input type="text" id="toSalery" class="m-wrap span12" name="toSalery">			
															</div>
														</div>
													</div>
													
													<!--/span-->
												
												</div>
													<div class="row-fluid">
												
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >To PMC Rent</label>
															<div class="controls">
																<input type="text" id="rent" class="m-wrap span12" name="rent">			
															</div>
														</div>
													</div>
													
													<!--/span-->
												
												</div>
													<div class="row-fluid">
												
													<div class="span4">
													<div class="control-group error">
															<label class="control-label" >To Electricity</label>
															<div class="controls">
																<input type="text" id="electricity" class="m-wrap span12" name="electricity">			
															</div>
														</div>
													</div>
													
													<!--/span-->
												
												</div>
													<div class="row-fluid">
												
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >To Telephone</label>
															<div class="controls">
																<input type="text" id="toTelephone" class="m-wrap span12" name="toTelephone">			
															</div>
														</div>
													</div>
													
													<!--/span-->
												
												</div>
													<div class="row-fluid">
												
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >To Shop Expenses</label>
															<div class="controls">
																<input type="text" id="toShopExpenses" class="m-wrap span12" name="toShopExpenses">			
															</div>
														</div>
													</div>
													
													<!--/span-->
												
												</div>
												<div class="row-fluid">
												
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >To Consulting Fees</label>
															<div class="controls">
																<input type="text" id="consulting" class="m-wrap span12" name="consulting">			
															</div>
														</div>
													</div>
													</div>
													<div class="row-fluid">
												
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >To Depreciation</label>
															<div class="controls">
																<input type="text" id="toDepreciation" class="m-wrap span12" name="toDepreciation">			
															</div>
														</div>
													</div>
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >By Rent</label>
															<div class="controls">
																<input type="text" id="commission" class="m-wrap span12" name="commission">			
															</div>
														</div>
													</div>
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >Other Income</label>
															<div class="controls">
																<input type="text" id="income" class="m-wrap span12" name="income">			
															</div>
														</div>
													</div>
													<!--/span-->
													</div>
													
											
													<div class="row-fluid">
												
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >To Net Profit</label>
															<div class="controls">
																<input type="text" id="toNetProfit" class="m-wrap span12" name="toNetProfit">			
															</div>
														</div>
													</div>
													
													<!--/span-->
												
												</div>	
										<button type="submit" name="printLossProfit" id="purchaseB" class="btn purple">Print</button>
										
													<!--/span-->
												</div>
								
									
								</form:form>
						
								
							</div>
						
						</div>
						<!-- END VALIDATION STATES-->
					</div>
				</div>
					<!-- BEGIN Data table advanced  PORTLET-->
						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption"><i class="icon-globe"></i>purchaseProduct</div>
								<div class="actions">
									<div class="btn-group">
										<a class="btn" href="#" data-toggle="dropdown">
										Columns
										<i class="icon-angle-down"></i>
										</a>
										<div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
											<label><input type="checkbox" checked data-column="0">State Id</label>
											<label><input type="checkbox" checked data-column="1">State Name</label>
											
										</div>
									</div>
								</div>
							</div>
							<div class="portlet-body">
								<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
									<thead>
										<tr>
											<th>ProductType</th>
											<th>ProductName</th>
											<th>Quntity</th>
											<th>Price</th>
											<th>Amount</th>
											<th>ProductCode</th>											
										</tr>
									</thead>
									<tbody>
										<c:forEach var="v" items="${getAllProductList}">
										<tr onclick="showData(this);">					
 											<td><c:out value="${v.productName}"></c:out></td>
 											<td><c:out value="${v.SubProductName}"></c:out></td>
 											<td><c:out value="${v.Quntity}"></c:out></td>
 											<td><c:out value="${v.Price}"></c:out></td>
 											<td><c:out value="${v.amount}"></c:out></td>	
 											<td><c:out value="${v.productCode}"></c:out></td>				
										</tr>
										</c:forEach>
									 
									</tbody>
								</table>
							</div>
						</div>
						<!-- END Advanced Data TABLE PORTLET-->	
		
					
	</div>
	<!-- END CONTAINER -->
	</div>
	</div>
</body>
<!-- END BODY -->
</html>