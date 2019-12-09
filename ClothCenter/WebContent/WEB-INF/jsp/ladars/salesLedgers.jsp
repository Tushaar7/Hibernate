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
$("#paidAmount").change(function(){
var remainingAmount=${getSalesPaidA};
var paidAmount=	$("#paidAmount").val();
var total=remainingAmount-paidAmount;
$("#remainingAmount").val(total);
	
});
});
 
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#stateForm").validate({
		rules : {
			
		  "ledgerName" : {required : true, accept: "[a-zA-Z]+"},
		 
		  
		},
		 messages : {
		
			"ledgerName" : {required:"<font size=2 color='red'>Product name must not be null</font>",
				accept:"<font size=2 color='red'>Please enter character only</font>"},
				
		}, 
		submitHandler : function(form) {
			form.submit();
		}
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
		<div class="row-fluid" id="div1">
					<div class="span12">
						<!-- BEGIN VALIDATION STATES-->
						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i> Customer Ledger Account</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
									<a href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form:form action="SalesLedger.html" commandName="SalesLedgercmd" id="stateForm" class="form-horizontal">
									<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="#" class="horizontal-form">
												
													<div class="row-fluid">
												
													<%-- <div class="span4">
													<label class="control-label">LedgerType<span class="required">*</span></label>
														<div class="controls">
													       	
													       	<form:select path="PaymentMode"  class="m-wrap span12" id="ledgerType">
													       	<form:option value="">--SelectLedgerType--</form:option>
													       	<form:options items="${LedgerTypelist}"/>
													       
													       	</form:select>
														</div>
													</div> --%>
													<div class="span4" id="div4">
														<label class="control-label">CustomerName<span class="required">*</span></label>
														<div class="controls">
															<select name="ledgerName"  class="m-wrap span12" id="supplierName" >
															<option value="">--SelectName--</option>
															<c:forEach var="v" items="${getladgerCustomerNamelist}">
															<option value="${v.key}">${v.value}</option>
															</c:forEach>
															
															</select>													
														</div>
												
													</div>
													
														<div class="span4">
														<input type="submit" name="Serach" id="serach" value="serach" class="btn purple" />
										                
										                  </div>
												</div>
												
											<br>
									
												
									
								
									</form>
									</div>
									
								</form:form>
								<form:form action="PaidAmountCust.html" commandName="PaidAmountCustcmd">
								
								<div class="row-fluid">
												
													<div class="span4">
														<div class="control-group">
															<label class="control-label" >TotalAmount</label>
															<div class="controls">
																<input type="text" id="totAmount" class="m-wrap span12" name="TotalAmount" value="${getSalesTotalA}">
																
															</div>
														</div>
													</div>
													<div class="span4 ">
														<div class="control-group error">
															<label class="control-label" >PaidAmount</label>
															<div class="controls">
																<input type="text" id="paidAmount" class="m-wrap span12" name="paidAmount" >
																
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span4 ">
														<div class="control-group">
															<label class="control-label" >RemainingAmount</label>
															<div class="controls">
																<input type="text" id="remainingAmount" class="m-wrap span12" name="remainingAmount" value="${getSalesPaidA}" >
																
															</div>
														</div>
													</div>
												</div>
									<div class="form-actions">
										<button type="submit" name="submit" id="save" class="btn purple">Payment</button>
										
										
									</div>
								
								</form:form>
								<!-- END FORM-->
								<form:form action="salesPaidAmount.html" commandName="SalespaidACmd">
								<div class="form-actions">
								<button type="submit" name="print" id="save" class="btn purple">PaidCustomer</button>
								</div>
								</form:form>
							</div>
						
						</div>
						<!-- END VALIDATION STATES-->
					</div>
				</div>
	
	<BEGIN Data table advanced  PORTLET-->
						<div class="portlet box purple" id="div3">
							<div class="portlet-title">
								<div class="caption"><i class="icon-globe"></i>Customer Ledger Account</div>
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
											<th>Bill Number</th>
											<th>Customer Name</th>
                                            <th>TotalAmount</th>
                                         <!--    <th>ProductCode</th> -->
                                            <th>Date</th> 
                                                                     
               										
										</tr>
									</thead>
									<tbody>
										<c:forEach var="v" items="${getSalesLadgerlist}">
										<tr onclick="showData(this);">					
 											<td><c:out value="${v.salebillinngid}"></c:out></td>
 											<td><c:out value="${v.customername}"></c:out></td>	
 										    <td><c:out value="${v.totalAmount}"></c:out></td>
 										    <td><c:out value="${v.saledate}"></c:out></td>
 										 	
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