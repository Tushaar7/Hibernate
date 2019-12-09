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
								<div class="caption"><i class="icon-reorder"></i>Stock </div>
								<div class="tools"> 
									<a href="javascript:;" class="collapse"></a>
									<a href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
		<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form:form action="prin.html"
									commandName="printSa" id="stateForm"
									class="form-horizontal">
									<div class="row-fluid">
										<div class="span4">
											<div class="control-group error">
												<label class="control-label">Total Stock Amount</label>
												<div class="controls">
                                                  <input type="text" id="totalStock" class="m-wrap span12" name="totalStock" value="${totalStockAmount1}">
													
												</div>
											</div>
										</div>
                                        
	                                     <div class="span4">
										<button type="submit" name="printbill" id="save"
											class="btn purple">printBill</button>
											</div>
										</div>
								</form:form>
		</div>
		</div>
		</div>
		</div>
	
						
		<BEGIN Data table advanced  PORTLET-->
						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption"><i class="icon-globe"></i>Stock</div>
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
											<th>Stock Id</th>
											<th>Product Type</th>
											<th>Sub Product name</th>
                                            <th>Quantity</th>
                                            <th>Price</th>
                                            <th>TotalAmount</th>                            
               										
										</tr>
									</thead>
									<tbody>
										<c:forEach var="v" items="${getStockSummary}">
										<tr onclick="showData(this);">					
 											<td><c:out value="${v.id}"></c:out></td>
 											<td><c:out value="${v.productName}"></c:out></td>	
 											<td><c:out value="${v.SubProductName}"></c:out></td>
 											<td><c:out value="${v.quntity}"></c:out></td>
 											<td><c:out value="${v.price}"></c:out></td>	
 											<td><c:out value="${v.ToTalAmount}"></c:out></td>			
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