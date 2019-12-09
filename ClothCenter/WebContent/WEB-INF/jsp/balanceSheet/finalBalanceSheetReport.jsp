<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s" %>
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
		<div class="row-fluid" id="div1">
					<div class="span12">
						<!-- BEGIN VALIDATION STATES-->
						<div class="portlet box purple">
							
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								 <form:form action="finalBalanceReort.html" commandName="FinalBalanceR" id="stateForm" class="form-horizontal">
									<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<table>
											<tr>
											<td>
											<div class="logo" >
		                                          <img src="images/logo.png" alt="" /> 
	                                                  </div>
	                                                  </td>
	                                                  <td>
	                                                  	<div style="margin-left: 250px;"><h2 style="color: red;">Shree Ganesh Cloth Center</h2></div> 
										                <div style="margin-left: 250px;"><h4>Shilavihar Colony Corner,Paud road,Pune 38</h4></div></td>
										                </tr>
											
											</table>
											<div style="margin-left: 50px;" >
											<div align="center">
											<b><h3>BALANCE SHEET AS ON </h3></b>
											</div>
									       <table>
									       <tr>
									       <td><h4>CAPITAL A/C</h4></td>
									      
									       </tr>
									 
									       </table></div>
											<div style="margin-left: 50px;" ><table>
											<tr>
											<td style="padding-right: 100px">OPENING BALANCE</td>
											<td>
											 <c:forEach var="v" items="${openingBalance}">
									       <c:out value="${v.totalAmount}"></c:out>
									       </c:forEach></td>
									       </tr>
											</table></div>
											<div style="margin-left: 50px;" >
										 <table>
											<tr>
											<td style="padding-right: 120px">ADD: NETPROFIT</td>
											<td >
											<c:forEach var="s" items="${netProfitL}">
											<c:out value="${s.netProfit}"></c:out>
											</c:forEach></td></tr>
											</table></div> 
											<div style="margin-left: 50px;">
											<table >
											<tr>
											<td style="padding-right: 120px">
											<c:forEach var ="c" items="${addLablites}">
											
										<c:out value="${c.addItem}"></c:out><td ></td>
											</c:forEach>
											</td>
											<td style="padding-right: 120px">
											<c:forEach var ="x" items="${addLablites}">
											
											<c:out value="${x.addAmount}"></c:out><td style="padding-right: 120px"></td>
											</c:forEach>
											</td>
											</tr></table></div>
											
										<div style="margin-left: 50px;">
											<table >
											<tr>
											<td style="padding-right: 120px">
											<c:forEach var ="c" items="${lessLablites}">
											
										    <c:out value="${c.lessItem}"></c:out><td ></td>
											</c:forEach>
											</td>
											<td style="padding-right: 120px">
											<c:forEach var ="x" items="${lessLablites}">
											
											<c:out value="${x.lessAmmount}"></c:out><td style="padding-right: 120px"></td>
											</c:forEach>
											</td>
											</tr></table></div>
										
											
											
											
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

