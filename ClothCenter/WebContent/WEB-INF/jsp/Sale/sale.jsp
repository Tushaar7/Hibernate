<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<!-- 
Author: Raju sable
-->

<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> 
  <link rel="stylesheet" href="/resources/demos/style.css"> 
  <!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>  -->
  <script>
  $(document).ready(function(){
	 
  });
  </script>
<script type="text/javascript">
$(document).ready(function(){
$("#update").hide();
$("#Sales").show();
	getMaxId();
	/*  $("#purchasecode").keyup(function(){
		
	alert("sss");
		  var availableTags = new Array();
		  $.getJSON('purchaseAutoComplete.html',{productCode:productCode}).done(function(data1){
			  availableTags = data1;
		  });
		    $( "#purchasecode" ).autocomplete({
		      source: availableTags
		    });
		  }); */
	
	 $("#purchasecode").keyup(function(){
		 // alert("key up");
		  var availableTags;
		  var productCode=$("#purchasecode").val();
		  $.getJSON('purchaseAutoComplete.html',{productCode:productCode}).done(function(data1){
				//alert("json call");
			  availableTags = data1;
				$("#purchasecode").autocomplete({
					source : availableTags,
					
				});
			});
	 });
	  
	/* 
		
	
	$.getJSON('getpurchaseCode1.html',{productCode:productCode}).done(function(data1){
		
		alert(data1[0].purchaseCode);
		 $( "#purchasecode" ).autocomplete({
		      source: data1[0].purchaseCode;
		    });
	
	}); */
	
	$("#purchasecode").blur(function(){
		var	productCode=$("#purchasecode").val();
		$("#productName").empty();
		$("#subProductid").empty();
	$.getJSON('getproductCode.html',{productCode:productCode}).done(function(data){
	
		$("#productName").append("<option selected>"+data[0].productName+"</option>");
		$("#subProductid").append("<option selected>"+data[0].SubProductName+"</option>");
	});
		
	});
});
	
		
	function getMaxId()
	{
		//getMax Comapny Id
		$.getJSON('getMaxPurchaseId.html').done(function(data){ 
				$("#Purchaseid").val(data[0].Purchaseid);
			});
	} 
	
	//when reset button clicked
	$("#resetbutton").click(function(){
		resetfunction();
		getMaxId();
	});

var showData = function($this) {
	$("#update").show();
	$("#Sales").hide();
	var totAmount=$("#totAmount").val();
	var price=$($this).find("td").eq(5).text();
	var amount =parseFloat(totAmount)-parseFloat(price);
	$("#amounttot").val(amount);
	$("#saleid").val($($this).find("td").eq(0).text());
	var productType=$($this).find("td").eq(1).text();
	var productItem=$($this).find("td").eq(2).text();
	$("#productName").append("<option selected>"+productType+"</option>");
	$("#subProductid").append("<option selected>"+productItem+"</option>");
	$("#quntity").val($($this).find("td").eq(3).text());
	$("#Price").val($($this).find("td").eq(4).text());
	$("#Amount").val($($this).find("td").eq(5).text());
	$("#purchasecode").val($($this).find("td").eq(6).text());
	
	$("#save").hide();
	$("#update").show();
};
	
//for reset value of textbox
function resetfunction()
{
	$("#stateId").val("");
	$("#stateName").val("");
	$("#productname").focus();
	$("#update").hide();
	$("#save").show();
}
</script>
  <script type="text/javascript">
$(document).ready(function(){

	$("#Price").blur(function(){
		
		var price=$("#Price").val();
		var quntity=$("#quntity").val();
	
		var tot=price*quntity;
		$("#Amount").val(tot);
		
		
		
	});
	$("#quntity").blur(function(){
		
		var price=$("#Price").val();
		var quntity=$("#quntity").val();
		
		var tot=price*quntity;
		$("#Amount").val(tot);
		
	});
	/* $("#paymentmodel").change(function(){
	var paymentMode=$("#paymentmodel").val();
	if(paymentMode=="Cash")
		{
		
		}
	}); */
	
	$("#stateForm").validate({
		
		rules : {
			"customerName":{required: true, accept:"[a-zA-Z]+"},
			"purchaseCode":{required: true, accept:"[a-zA-Z]+"},
			"productType":{required: true, accept:"[a-zA-Z]+"},
			"productname" :{required: true, accept:"[a-zA-Z]+"},
			"quntity" :{required: true, accept:"[a-zA-Z]+"},
			"Price" :{required: true, accept:"[a-zA-Z]+"},
			"amount" :{required: true, accept:"[a-zA-Z]+"},
			
		},
		messages:{
			
			"customerName": {required:"<font size=2 color='red'>Customer name must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
			"purchaseCode": {required:"<font size=2 color='red'>purchaseCode name must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
			"productType": {required:"<font size=2 color='red'>producttype must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
			"productname":  {required:"<font size=2 color='red'>product name must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
			"quntity" : {required:"<font size=2 color='red'>quantity  must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
			"Price" : {required:"<font size=2 color='red'>price must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
			"amount" : {required:"<font size=2 color='red'>amount must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
		},
		 submitHandler:function(form){
			 form.submit();
			
		}
		
	});
	$("#stateForm12").validate({
		rules : {
			"Discount":{required:true, accept:"[a-zA-Z]+"}, 
			"PaymentMode":{required:true, accept:"[a-zA-Z]+"},
			
		},
		messages:{
			
			 "Discount": {required:"<font size=2 color='red'>Discount  must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
			"PaymentMode": {required:"<font size=2 color='red'>PaymentMode  must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
		
		},
		 submitHandler:function(from){
			 form.submit();
			
		}
		
	}); 
$("#paymentmodel").change(function(){
		
		var payment=$("#paymentmodel").val();
		var amount=$("#TotalAm").val();
		if(payment=="Cash")
			{
			$("#paidAmount").val(amount);
			
			}
		else
			if(payment=="Credit")
				{
				$("#paidAmount").val(0);
				}
	});
	
	
	$("#Discount").change(function(){
		var amount=$("#TotalAm").val();
		var discount=$("#Discount").val();
		var tot=amount-discount;
		$("#TotalAm").val(tot);
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
								<div class="caption"><i class="icon-reorder"></i>Sales Form</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
									<a href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
							
							<div class="portlet-body form">
						
								
								<!-- BEGIN FORM-->
								<form:form action="SalesItem.html" commandName="SaleItemcmd" id="stateForm" class="form-horizontal">
									<div class="portlet-body form">
											<!-- BEGIN FORM-->
											
											
										<!-- 	<hidden filed>		 -->
											<input type="hidden" id="billid"  name="billid" value="${getSalesid}" >
										
								          <input type="hidden" id="totAmount"  name="TotalAmount" value="${totalAmount}">
								         <input type="hidden" id="saleid" name="saleid" value="${salesid}">
								         <input type="hidden" id="amounttot" name="amounttot" value="${salesid}">
												<div class="row-fluid">
												<div class="span4 ">
															<div class="control-group error">
															<label class="control-label">CustomerName<span class="required">*</span></label>
										       <div class="controls">
											     	<input type="text" id="customerName" class="m-wrap span12" name="customerName" value="${customerName}">	
										       </div>
													</div>
													</div>
													</div>
												
												<div class="row-fluid">
												<div class="span4 ">
															<div class="control-group error">
															<label class="control-label">Purchase Code<span class="required">*</span></label>
															 <div class="controls">
															<input type="text" id="purchasecode" class="m-wrap span12" name="purchaseCode">
															</div>
										      					<%--  <div class="controls">
											                        <select  class="m-wrap span12" name="purchaseCode" id="purchasecode">
											                        <option value="" >--Select Product Name--</option>
																	<c:forEach var="v" items="${productCodelist}">
																	<option value="${v.purchaseCode}">${v.purchaseCode}</option>
																	</c:forEach>
																	</select>
										                          </div> --%>
													</div>
													</div>
													
													</div>	
													
												<div class="row-fluid">
												<div class="span4 ">
															<div class="control-group error">
															<label class="control-label">Product Type<span class="required">*</span></label>
										       <div class="controls">
											       <select  class="m-wrap span12" name="productType" id="productName">
												     <option value="">--Select Product Type--</option>
		     										<%--  <c:forEach var="v" items="${Productnamelistss}">
		     										<option value="${v.key}">${v.value}</option>		 --%>	
													<%-- </c:forEach>   	 --%>	
											      </select>
										       </div>
													</div>
													</div>
														<div class="span4 ">
															<div class="control-group error">
															<label class="control-label">Product Item<span class="required">*</span></label>
										       <div class="controls">
											       <select class="m-wrap span12" name="productname" id="subProductid">
																	<option value="">--Select Product Name--</option>
																	<%-- <c:forEach var="v" items="${subproductlist}">
																	<option value="${v.key}">${v.value}</option>
																	</c:forEach> --%> 
																</select>
										       </div>
													</div>
													</div>
													</div>
												<div class="row-fluid">
												
													<div class="span4">
															<div class="control-group error">
															<label class="control-label" >Quantity</label>
															<div class="controls">
																<input type="text" id="quntity" class="m-wrap span12" name="quntity">			
															</div>
														</div>
													</div>
													<div class="span4 ">
														<div class="control-group error">
															<label class="control-label" >Price</label>
															<div class="controls">
																<input type="text" id="Price" class="m-wrap span12" name="Price">
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span4 ">
															<div class="control-group error">
															<label class="control-label" >Amount</label>
															<div class="controls">
																<input type="text" id="Amount" class="m-wrap span12" name="amount">
															</div>
														</div>
													</div>
												</div>
													
										<button type="submit" name="Sales" id="Sales" class="btn purple">Sales</button>
										<button type="submit" name="updatesale" id="update" class="btn purple">update</button>
													<!--/span-->
												</div>
								
									
								</form:form>
								
								
							
								
								
								<form:form action="SaleFrom12.html" commandName="SaleFromcmd" id="stateForm12" class="form-horizontal">
									<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="#" class="horizontal-form">
												
												
													
												 <div class="row-fluid">
													<div class="span4">
															<div class="control-group error">
															<label class="control-label" >Discount</label>
															<div class="controls">
																<input type="text" id="Discount" class="m-wrap span12" name="Discount">
															</div>
														</div>
													</div>
													<div class="span4 ">
														<div class="control-group error">
															<label class="control-label">PaymentMode<span >*</span></label>
															<div class="controls">
																<select name="PaymentMode" class="m-wrap span12"   id="paymentmodel">
																<option>---Select---</option>
																<option>Credit</option>
																<option>Cash</option>	
															</select>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span4 ">
															<div class="control-group error">
															<label class="control-label" >PaidAmount</label>
															<div class="controls">
																<input type="text" id="paidAmount" class="m-wrap span12" name="paidAmount">
															</div>
														</div>
													</div>
												</div>
													<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group error">
															<label class="control-label" >TotalAmount</label>
															<div class="controls">
																<input type="text" id="TotalAm" class="m-wrap span12" name="TotalAmount" value="${totalAmount}">
																
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
									<div class="form-actions">
										<button type="submit" name="save" id="save" class="btn purple">Submit</button>
										
									</div>
									</form>
									</div>
									
								</form:form>
								
								<!-- END FORM-->
								
							</div>
						
						</div>
						<!-- END VALIDATION STATES-->
					</div>
				</div>
					<!-- BEGIN Data table advanced  PORTLET-->
						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption"><i class="icon-globe"></i>Salesproduct</div>
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
										    <th>SaleId</th>
											<th>ProductType</th>
											<th>ProductName</th>
											<th>Quantity</th>
											<th>Price</th>
											<th>Amount</th>
											<th>productCode</th>
																					
										</tr>
									</thead>
									<tbody>
										<c:forEach var="v" items="${getSalesList}">
										<tr onclick="showData(this);">
										    <td><c:out value="${v.saleid}"></c:out></td>					
 											<td><c:out value="${v.productName}"></c:out></td>
 											<td><c:out value="${v.SubProductName}"></c:out></td>
 											<td><c:out value="${v.quantitys}"></c:out></td>
 											<td><c:out value="${v.price}"></c:out></td>
 											<td><c:out value="${v.amounts}"></c:out></td>
 											<td><c:out value="${v.purchaseCode}"></c:out></td>	
 														
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