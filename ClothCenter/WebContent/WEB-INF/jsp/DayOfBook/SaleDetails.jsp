<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<!-- 
Author: Raju sable
-->

<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>

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
								<div class="caption"><i class="icon-reorder"></i>Sales Day of Book</div>
								<div class="tools"> 
									<a href="javascript:;" class="collapse"></a>
									<a href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form:form action="printSalesBill.html"
									commandName="printSalescmd" id="stateForm"
									class="form-horizontal">
									<div class="row-fluid">
										<div class="span4">
											<div class="control-group error">
												<label class="control-label">Enter Bill id</label>
												<div class="controls">
                                                  <input type="text" id="billid" class="m-wrap span12" name="billid">
													
												</div>
											</div>
										</div>
                                        
	                                     <div class="span4">
										<button type="submit" name="printbill" id="save"
											class="btn purple">printBill</button>
											</div>
										</div>
								</form:form>
								<form:form action="totalAmountpurchase.html"
									commandName="totalAmountpurchasecmd" id="stateForm"
									class="form-horizontal">




									<div class="row-fluid">

										<div class="span4">
											<div class="control-group error">
												<label class="control-label">Total Sales Amount</label>
												<div class="controls">
													<input type="text" id="totAmount" class="m-wrap span12"
														name="totAmount" value="${getSalesAmount}">
												</div>
											</div>
										</div>
									</div>


								</form:form>
								
								<!-- END FORM-->
							</div>
						</div>
						<!-- END VALIDATION STATES-->
					</div>
				</div>
						
		<BEGIN Data table advanced  PORTLET-->
						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption"><i class="icon-globe"></i>Sales Day of Book</div>
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
										    <th>Customer Name</th>
											<th>Bill Number</th>
											 <th>Sales Date</th> 
											<th>Payment Mode</th>
                                            <th>TotalAmount</th>
                               		
										</tr>
									</thead>
									<tbody>
										<c:forEach var="v" items="${getSalesDetailslist}">
										<tr onclick="showData(this);">
											<td><c:out value="${v.customername}"></c:out></td>						
 											<td><c:out value="${v.salebillinngid}"></c:out></td>
 										     <td><c:out value="${v.saledate}"></c:out>	
 											<td><c:out value="${v.paymentmode}"></c:out></td>
 										    <td><c:out value="${v.totalAmount}"></c:out></td>
 										 	
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