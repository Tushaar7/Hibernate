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
$(document).ready(function()
{
	$("#totAmountaaa").click(function(){
		
		var totAmount=$("#totalAmountss").val();
		var fromdate=$("#toDate").val();
		var toDate=$("#fromDate").val();
		$.getJSON('gettotalA.html',{totAmount:totAmount}).done(function(data){
			var addAmu=data[0].amountss;
			var lessA=data[0].amount;
	      
	       
		
			var lessAs=addAmu-lessA;
		
			var tot=parseFloat(totAmount)+parseFloat(lessAs);
		
			$("#totalAmountss").val(tot);
		});
		
	});
	
	$("#opening").change(function(){
		
		var tota=$("#opening").val();
	 
	   $("#totalAmount").val(tota);
	});
	$("#dateSubs").click(function(){
	
		var fromdate=$("#toDate").val();
		var toDate=$("#fromDate").val();
		
		$.getJSON('gettotalAmount.html',{fromdate:fromdate,toDate:toDate}).done(function(data){
			
		$("#netProfit").val(data[0].netProfit);	
		$("#closeStock").val(data[0].closingbalance);
		});
		
	
	});
	
	
	 $('#todate').datetimepicker({
			
		});
	 $("#toDate").datetimepicker({
		 
	 });
	 $("#fromDate").datetimepicker({
		 
	 });
	 $('#todate1').datetimepicker({
			
		});
	$("#total").click(function(){
		
	var tot	=$("#totalAmount1").val();
	var netprofit=$("#netProfit").val();
	var total=parseFloat(tot)+parseFloat(netprofit);
		$("#finalAmount").val(total);
	var opeining=	$("#opening").val();
	var netProfit=$("#netProfit").val();
	var totalam=$("#finalAmount").val();
	var fromdate=$("#toDate").val();
	var toDate=$("#fromDate").val();
	var Dates=$("#insertDate").val();

	
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
								<div class="caption"><i class="icon-reorder"></i>Ledger Account</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
									<a href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								 <form:form action="finalBalanceSheet.html" commandName="finalBalanceSheetcmd" id="stateForm" class="form-horizontal">
									<div class="portlet-body form">
											<!-- BEGIN FORM-->
											 
											  <center><h2> Liabilities</h2></center>
											<form action="#" class="horizontal-form">
												
												<div class="row-fluid">
												
											
											<div class="control-group">
										<label class="control-label">Select Date</label>
										<div class="controls">
											 <input type="text" id="todate" class="m-wrap span6" readonly="readonly" name="todate" value="${todate1}" >
											</div>
										</div>
									
														
												</div> 
												
	                                            	<div class="row-fluid">
												<div class="span4 ">
												<div class="control-group error">
										               <label class="control-label">ToDate</label>
										                 <div class="controls">
											                <input type="text" id="toDate" class="m-wrap span10"  name="toDate" value="${todate}" >
											              </div>
										              </div>
										              </div>
													<div class="span4 ">
												<div class="control-group error">
										               <label class="control-label">fromDate</label>
										                 <div class="controls">
											                <input type="text" id="fromDate" class="m-wrap span10" name="fromDate" value="${fromDate1}" >
											              </div>
										              </div>
										             
										              </div>
										              
													<!--/span-->
												 <div class="span4">
										              <input type="button" id="dateSubs" name="dateSubmit" class="btn purple" value="submit">
										              </div>
												</div>
												
													<div class="row-fluid">
												
											
											       	<div class="control-group error">
										              <label class="control-label">OpeningBalance</label>
										                <div class="controls">
											             <input type="text" id="opening" class="m-wrap span8" name="openingBalance" value="${opening}">
											            </div>
										            </div>
									
														
												   </div>
											<div class="row-fluid">
												
													<div class="span4">
															<div class="control-group error">
															<label class="control-label" >AddItem</label>
															<div class="controls">
																<input type="text" id="addItem" class="m-wrap span12" name="addladger" >
																
															</div>
														</div>
													</div>
													<div class="span4 ">
														<div class="control-group error">
															<label class="control-label" >Amount</label>
															<div class="controls">
																<input type="text" id="totAmount" class="m-wrap span12" name="Amount">
																
															</div>
														</div>
													</div>
													<div class="span4">
														<input type="submit" name="AddLadger" id="serach" value="AddItem" class="btn purple" />
						

										               
										                  </div>
												</div>
									
											
												
													
																<input type="text" id="totalAmount" class="hidden" name="totAmount" value="${amount}">
															
												
									
								
									</form>
									</div>
									
								</form:form> 
									<form:form action="lessfinalBalance.html" target="blank" commandName="finalBalanceless" id="stateForm" class="form-horizontal">
							
									<div class="portlet-body form">
									
											<!-- BEGIN FORM-->
											<form action="#" class="horizontal-form">
												
													
															
																
															
													
									
													<div class="row-fluid">
												
													 <div class="span4">
															<div class="control-group error">
															<label class="control-label" >LessItem</label>
															<div class="controls">
																<input type="text" id="lessLager" class="m-wrap span12" name="lessLager" >
																
															</div>
														</div>
													</div>
													
													<div class="span4">
															<div class="control-group error">
															<label class="control-label" >LessAmount</label>
															<div class="controls">
																<input type="text" id="amountless" class="m-wrap span12" name="amountless" >
																
															</div>
														</div>
													</div>
													
													<div class="span4">
														<input type="submit" name="lessItem" id="less" value="LessItem" class="btn purple" />
						

										               
										                  </div>
												</div>
													<div class="row-fluid">
												<div class="span4">
															<div class="control-group error">
															<label class="control-label" >TotalAmount</label>
															<div class="controls">
																<input type="text" id="totalAmount1" class="m-wrap span12" name="lessAmount" value="${amount}">
																
															</div>
														</div>
													</div>
													
													</div>
												<div class="row-fluid">
												        <div class="span6">
															<div class="control-group error">
															<label class="control-label" >NetProfit</label>
															<div class="controls">
																<input type="text" id="netProfit" class="m-wrap span12" name="netProfit" value="${netProfit}">
																
															</div>
														</div>
													</div>
													</div>
													<div class="row-fluid">
												        <div class="span4">
															<div class="control-group error">
															<label class="control-label" >finalTotalAmount</label>
															<div class="controls">
																<input type="text" id="finalAmount" class="m-wrap span12" name="finalAmount" >
																
															</div>
														</div>
													</div>
													 <div class="span4">
															<div class="control-group error">
															<label class="control-label" >Date</label>
															<div class="controls">
																<input type="text" id="insertDate" class="m-wrap span12" name="insertDate" >
																
															</div>
														</div>
													</div>
								                    	<div class="span4">
														<input type="button" name="lessItem" id="total" value="Total" class="btn purple" />
						
                                                            </div>
										               
										                  </div>
										               <input type="submit" name="Print" id="print" value="Print" class="btn purple" />
								
									</form>
									</div>
									
								</form:form>
								
								<!-- END FORM-->
								<!-- BEGIN FORM-->
								<form:form action="assetFinalBalance.html" commandName="assetFinalBalancecmd" id="stateForm" class="form-horizontal">
									<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<center><h2>Assets</h2></center>
											<form action="#" class="horizontal-form">
												
												
													<div class="row-fluid">
												
											
											<div class="control-group">
										<label class="control-label">Select Date</label>
										<div class="controls">
											 <input type="text" id="todate1" class="m-wrap span6" readonly="readonly" name="todate" value="${todate1}" >
											</div>
										</div>
									
														
												</div>
													<div class="row-fluid">
												
											
											       	<div class="control-group error">
										              <label class="control-label">Closing Stock</label>
										                <div class="controls">
											             <input type="text" id="closeStock" class="m-wrap span8" name="closeStock" value=${closeingStock} >
											            </div>
											             <input type="text" id="totalAmount" class="hidden" name="totalAmount" value=${totalAmount} >
									                     </div>
														
												    </div>
											<div class="row-fluid">
												
													<div class="span4">
															<div class="control-group error">
															<label class="control-label" >AddItem</label>
															<div class="controls">
																<input type="text" id="totAmount" class="m-wrap span12" name="addladger" >
																
															</div>
														</div>
													</div>
													<div class="span4 ">
														<div class="control-group error">
															<label class="control-label" >Amount</label>
															<div class="controls">
																<input type="text" id="paidAmount" class="m-wrap span12" name="amount">
																
															</div>
														</div>
													</div>
													<div class="span4">
														<input type="submit" name="AddLadger" id="serach" value="AddItem" class="btn purple" />
						

										               
										                  </div>
												</div>
									
												
									
								
									</form>
									</div>
									
								</form:form>
								<form:form action="lablitesLessItem.html" target="blank" commandName="lablitesLessItemcmd" class="form-horizontal">
									<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="#" class="horizontal-form">
												
													<div class="row-fluid">
												
													<div class="span4">
															<div class="control-group error">
															<label class="control-label" >LessItem</label>
															<div class="controls">
																<input type="text" id="lessLager" class="m-wrap span12" name="lessLager" >
																
															</div>
														</div>
													</div>
													<div class="span4">
														<div class="control-group error">
															<label class="control-label" >Amount</label>
															<div class="controls">
																<input type="text" id="lessAmount" class="m-wrap span12" name="lessAmount">
																
															</div>
														</div>
													</div>
													
													<div class="span4">
														<input type="submit" name="lessAmount" id="serach" value="LessItem" class="btn purple" />
						

										               
										                  </div>
												</div>
												
									<div class="row-fluid">
												
											
											       	<div class="control-group error">
										              <label class="control-label">TotalAmount</label>
										                <div class="controls">
											              <input type="text" id="totalAmountss" class="m-wrap span6" name="totalAmount" value=${totalAmount} >
											            </div>
											            <input type="button"  id="totAmountaaa" value="totAmount" class="savevalue" />
									                     </div>
														
												    </div>
												    <div class="row-fluid">
										<div class="control-group error">
										<div class="sm-4">
									<input type="submit" name="printLa" id="serachs" value="printssss" class="btn purple" />
									</div>
									</div>
								   </div>
									</form>
									</div>
									
								</form:form>
								
							</div>
							
						
						</div>
						<!-- END VALIDATION STATES-->
					</div>
				</div>
						
		
	
	
					
	</div>
	<!-- END CONTAINER -->
	</div>
	</div> 
	
</body>

<!-- END BODY -->
</html>