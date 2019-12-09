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
	resetfunction();
	getMaxId();
	
	
	$("#stateForm").validate({
		rules : {
			
		  "productname" : {required : true, accept: "[a-zA-Z]+"},
		  "SubProductName" :{required: true, accept:"[a-zA-Z]+"},
		},
		 messages : {
		
			"productname" : {required:"<font size=2 color='red'>Product name must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
				"SubProductName":{required:"<font size=2 color='red'>Sub Product name must not be null</font>",
					accept:"<font size=2 color='red'>Please enter character only</font>"},
		}, 
		submitHandler : function(form) {
			form.submit();
		}
	});  
	
	function getMaxId()
	{
		//getMax Comapny Id
		$.getJSON('getMaxsubProductId.html').done(function(data){ 
				$("#subProductid").val(data[0].subProductid);
			});
	} 
	
	//when reset button clicked
	$("#resetbutton").click(function(){
		resetfunction();
		getMaxId();
	});
});

var showData = function($this) {

	$("#subProductid").val($($this).find("td").eq(0).text());
	
	var str=$($this).find("td").eq(3).text();
	var stringTrim=str.trim();
	
	$("#codeSub").val(stringTrim);
	var productname=$($this).find("td").eq(1).text();
	$.getJSON('getProductid.html',{productname:productname}).done(function(data){
		$("#productName").val(data[0].productid);
	});
	$("#SubProductName").val($($this).find("td").eq(2).text());
	

	
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
								<div class="caption"><i class="icon-reorder"></i>Add Sub Product</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
									<a href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form:form action="addSubProduct.html" commandName="addProductcmd" id="stateForm" class="form-horizontal">
									<div class="alert alert-success">
									<button class="close" data-dismiss="alert"></button>
									<strong><font>${message}</font></strong>
									</div>
									 <div class="control-group">
										<label class="control-label">Sub Product Id<span class="required">*</span></label>
										<div class="controls">
											<input type="text" readonly="readonly" id="subProductid"  name="subProductid" data-required="1" class="span6 m-wrap"/>
										</div>
									</div>
									<div class="control-group error">
										<label class="control-label"> Product Name<span class="required">*</span></label>
										<div class="controls">
											<select class="span6 m-wrap" name="productmodel.productid" id="productName">
												<option value="">------Select Product Type-------</option>
		     										 <c:forEach var="v" items="${Productnamelistss}">
		     										<option value="${v.key}">${v.value}</option>			
													</c:forEach>   		
											</select>
										</div>
									</div>
								<div class="control-group error">
										<label class="control-label">Sub Product Name<span class="required">*</span></label>
										<div class="controls">
											<input type="text" id="SubProductName"  name="SubProductName" data-required="1" class="span6 m-wrap"/>
										</div>
									</div>
									 	
									<div class="control-group error">
										<label class="control-label" >ProductCode</label>
											<div class="controls">
											<input type="text" id="codeSub" class="span6 m-wrap" name="codeSub">
											</div>
									</div>
													
									<div class="form-actions">
										<button type="submit" name="save" id="save" class="btn purple">Submit</button>
										<button type="submit" name="update" id="update" class="btn purple">Update</button>
										<button type="submit" name="delete" id="delete" class="btn purple">Delete</button>
										<button type="button" id="resetbutton" class="btn">Cancel</button>
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
								<div class="caption"><i class="icon-globe"></i> Sub Product Type</div>
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
											<th>Product Id</th>
											
											<th>Product Type</th>
											<th>Sub Product name</th>
											<th>ProductCode</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="v" items="${getSubProductList}">
										<tr onclick="showData(this);">					
 											<td><c:out value="${v.subProductid}"></c:out></td>
 											<td><c:out value="${v.productName}"></c:out></td>	
 											<td><c:out value="${v.SubProductName}"></c:out></td>
 											<td><c:out value="${v.codeSub}"></c:out>			
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