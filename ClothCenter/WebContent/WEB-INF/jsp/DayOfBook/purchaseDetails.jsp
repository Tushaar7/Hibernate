<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<!-- 
Author: Raju sable
-->

<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
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
								<div class="caption">
									<i class="icon-reorder"></i>Purchase Day Of Book
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a> <a
										href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a> <a
										href="javascript:;" class="remove"></a>
								</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form:form action="printPurchaseBill.html"
									commandName="printpurchasecmd" id="stateForm"
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
												<label class="control-label">Total Purchase Amount</label>
												<div class="controls">
													<input type="text" id="totAmount" class="m-wrap span12"
														name="totAmount" value="${totalPurchaseAmount}">
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

				<BEGIN Data table advanced PORTLET-->
				<div class="portlet box purple">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-globe"></i>Purchase Day Of Book
						</div>
						<div class="actions">
							<div class="btn-group">
								<a class="btn" href="#" data-toggle="dropdown"> Columns <i
									class="icon-angle-down"></i>
								</a>
								<div id="sample_2_column_toggler"
									class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
									<label><input type="checkbox" checked data-column="0">State
										Id</label> <label><input type="checkbox" checked
										data-column="1">State Name</label>
								</div>
							</div>
						</div>
					</div>
					<div class="portlet-body">
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2">
							<thead>
								<tr>

									<th>Supplier Name</th>
									<th>Bill Number</th>
									<th>Purchase Date</th>
									<th>Payment Mode</th>
									<th>TotalAmount</th>


								</tr>
							</thead>
							<tbody>
								<c:forEach var="v" items="${PurchaseDetailslist}">
									<tr onclick="showData(this);">
										<td><c:out value="${v.Supliername}"></c:out></td>
										<td><c:out value="${v.bilid}"></c:out></td>
										<td><c:out value="${v.Date}"></c:out></td>
										<td><c:out value="${v.PaymentMode}"></c:out></td>
										<td><c:out value="${v.TotalAmount}"></c:out></td>

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