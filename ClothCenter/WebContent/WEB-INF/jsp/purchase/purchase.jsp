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
	$("#updatep").hide();
	$("#purchaseB").show();
	getMaxId();
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
	$("#productName").change(function(){
		var productname=$("#productName").val();
		$("#subProductid").empty();
		$("#subProductid").append("<option>"+"--Select Item--"+"</option>");
		$.getJSON('productName.html',{productname:productname}).done(function(data){
		for(var i=0;i<data.length;i++)
			{
			$("#subProductid").append("<option>"+data[i].SubProductName+"</option>");
			}
			
		});
	});
	
	$("#Discount").change(function(){
		var amount=$("#totalsss").val();
		var discount=$("#Discount").val();
		var tot=amount-discount;
		$("#TotalAm").val(tot);
	});
	$("#stateForm").validate({
		rules : {
			
		  "productname" : {required : true, accept: "[a-zA-Z]+"},
		  "productType" :{required: true, accept:"[a-zA-Z]+"},
		  "quntity" : {required : true, accept: "[a-zA-Z]+"},
		  "Price" :{required: true, accept:"[a-zA-Z]+"},
		  "amount" :{required: true, accept:"[a-zA-Z]+"},
		  
		},
		 messages : {
		
			"productname" : {required:"<font size=2 color='red'>Product name must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
				"productType":{required:"<font size=2 color='red'>Sub Product name must not be null</font>",
					accept:"<font size=2 color='red'>Please enter character only</font>"},
					"quntity" : {required:"<font size=2 color='red'>quntity  must not be null</font>",
						accept:"<font size=2 color='red'>Please enter character only</font>"},
						"Price":{required:"<font size=2 color='red'>Sub Product name must not be null</font>",
							accept:"<font size=2 color='red'>Please enter character only</font>"},
							"amount":{required:"<font size=2 color='red'>Sub Product name must not be null</font>",
								accept:"<font size=2 color='red'>Please enter character only</font>"},
		}, 
		submitHandler : function(form) {
			form.submit();
		}
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
});

var showData = function($this) {
	$("#updatep").show();
	$("#purchaseB").hide();
	
	/* var productType=$($this).find("td").eq(1).text();
    var productItem=$($this).find("td").eq(2).text();
    var productCode=$($this).find("td").eq(6).text();
    var price=$($this).find("td").eq(4).text();
    var quantity=$($this).find("td").eq(3).text();
    var totAmount=$($this).find("td").eq(3).text();
    alert("Sss"+productType)
    $.getJSON('updateStock.html',{productType:productType,productItem:productItem,productCode:productCode,price:price,quantity:quantity,totAmount:totAmount}).done(function(data){
    	alert("sss")
    }); */
	var amount=$("#totAmount").val();
	var toAmount=$($this).find("td").eq(5).text();
	var totalAmount=parseFloat(amount)-parseFloat(toAmount);
	$("#producttot").val(totalAmount);
  $("#productid1").val($($this).find("td").eq(0).text());
	$("#quntity").val($($this).find("td").eq(3).text());
	var select=$($this).find("td").eq(2).text();
	$("#subProductid").append("<option selected>"+select+"</option>");
	$("#Price").val($($this).find("td").eq(4).text());
	$("#Amount").val($($this).find("td").eq(5).text());
	

	var productname=$($this).find("td").eq(1).text();
	
	$.getJSON('getProductid.html',{productname:productname}).done(function(data){
	
		$("#productName").val(data[0].productid);
	});
	/*  var productitem=$($this).find("td").eq(2).text();
	$.getJSON('getitemId.html',{productitem:productitem}).done(function(data){
		alert(data[0].subProductid);
		$("#subProductid").val(data[0].SubProductName);
	});  */
	
	
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
		
		var tot=parseFloat(price)*parseFloat(quntity);
	
		$("#Amount").val(tot);
		
	});
	/* $("#paymentmodel").change(function(){
	var paymentMode=$("#paymentmodel").val();
	if(paymentMode=="Cash")
		{
		
		}
	}); */
	
	$("#stateForm12").validate({
		rules:{
			
			"ledgerName":{required:true,accept:"[a-zA-Z]"},
			"Discount":{required:true,accept:"[a-zA-Z]"},
			"PaymentMode":{required:true,accept:"[a-zA-Z]"},
			
		},
		messages:{
			"ledgerName":{required:"<font size=2 color='red'>please enter supplierName</font>",
				accept:"<font size=2 color='red'></font>"},
			"Discount":{required:"<font size=2 color='red'>please enter Discount</font>",
				accept:"<font size=2 color='red'></font>"},
				"PaymentMode":{required:"<font size=2 color='red'>please enter PaymentMode</font>",
					accept:"<font size=2 color='red'></font>"},
					
		},
		submitHandler:function(from)
		{
			form.submit();
		}
		
	});
	
	
	
});
/* var showData=function($this)
{
	
	}; */
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
								<div class="caption"><i class="icon-reorder"></i>Purchase Form</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
									<a href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
							<div class="portlet-body form">
						
								
								<!-- BEGIN FORM-->
								<form:form action="PurchaseItem.html" commandName="PurchaseItemcmd" id="stateForm" class="form-horizontal">
									<div class="portlet-body form">
											<!-- BEGIN FORM-->
											
											
										<!-- 	<hidden filed>		 -->
										
											<input type="hidden" id="billid" class="m-wrap span12" name="billid" value="${billingidList}" >
										<input type="hidden" id="productid1" name="productid1">
								          <input type="hidden" id="totAmount" class="m-wrap span12" name="TotalAmount" value="${totalAmount}">
											<input type="hidden" id="producttot" name="producttot">				
												<div class="row-fluid">
												<div class="span4 ">
															<div class="control-group error">
															<label class="control-label">Product Type<span class="required">*</span></label>
										                    <div class="controls">
											            <select  class="m-wrap span12" name="productType" id="productName">
												     <option value="">--Select Product Type--</option>
		     										 <c:forEach var="v" items="${Productnamelistss}">
		     										<option value="${v.key}">${v.value}</option>			
													</c:forEach>   		
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
																	<c:forEach var="v" items="${subproductlistss}">
																	<option value="${v.key}">${v.value}</option>
																	</c:forEach>
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
													
										<button type="submit" name="purchaseB" id="purchaseB" class="btn purple">purchase</button>
										<input type="submit" name="updatepurchases" id="updatep" class="btn purple" value="update">
													<!--/span-->
												</div>
								
									
								</form:form>
								
								
							
								
								
								<form:form action="PurchaseFrom.html" commandName="PurchaseFromcmd" id="stateForm12" class="form-horizontal">
									<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="#" class="horizontal-form">
												
												
												<div class="row-fluid">
												<div class="span4 ">
															<div class="control-group error">
															<label class="control-label">SupplierName<span class="required">*</span></label>
										       <div class="controls">
											      <select name="ledgerName"  class="m-wrap span12" id="supplierName" >
															<option value="">--SelectName--</option>
															<c:forEach var="v" items="${getladgerNamelist}">
															<option value="${v.key}">${v.value}</option>
															</c:forEach>
															
															</select>	
										       </div>
													</div>
													</div>
													</div>
													
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
															<label class="control-label">PaymentMode<span class="required">*</span></label>
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
													
																<input type="text" id="totalsss" class="hidden" name="TotalxxxAmount" value="${totalAmount}">
															
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
										    <th>productid</th>
											<th>ProductType</th>
											<th>ProductName</th>
											<th>Quantity</th>
											<th>Price</th>
											<th>Amount</th>
											<th>ProductCode</th>											
										</tr>
									</thead>
									<tbody>
										<c:forEach var="v" items="${getAllProductList}">
										<tr onclick="showData(this);">	
										    <td><c:out value="${v.Purchaseid}"></c:out></td>				
 											<td><c:out value="${v.productName}"></c:out></td>
 											<td><c:out value="${v.SubProductName}"></c:out></td>
 											<td><c:out value="${v.Quntity}"></c:out></td>
 											<td><c:out value="${v.Price}"></c:out></td>
 											<td><c:out value="${v.amount}"></c:out></td>	
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