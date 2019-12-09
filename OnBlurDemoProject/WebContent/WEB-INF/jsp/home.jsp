<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">	
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>On Blur</title>

<script type="text/javascript">
	$(document).ready(function() {
		$("#price").blur(function() {

			var price1 = $("#price").val();
			var quantity1 = $("#quant").val();

			var total = price1 * quantity1;
			$("#amt").val(total);
		});
	});

	$(document).ready(function() {
		$("#firstname").change(function() {
			$("#middlename").prop("readonly", false);
			$("#firstname").prop("readonly", true);
		});
	});

	$(document).ready(function() {
		$("#middlename").change(function() {
			$("#lastname").prop("readonly", false);
			$("#middlename").prop("readonly", true);
		});
	});
</script>

 
 </head>
<body>

	<div align="center">

		<form:form>
			<table border="1">
				<h1>${msg}</h1>

				<tr>
					<td>Date</td>
					<td><input type="date" name="date" id="date" /></td>
				</tr>

				<tr>
					<td>Quantity</td>
					<td><input type="text" name="quantity" id="quant" /></td>
				</tr>
			
				<tr>
					<td>Price</td>
					<td><input type="text" name="price" id="price" /></td>
				</tr>
			
				<tr>
					<td>Amount</td>
					<td><input type="text" name="amount" id="amt" /></td>
				</tr>

<!-- 			<div id="imeiId">
			<tr>
				<td>IMEI</td>
				<td><input type="text" name="imei" id="imei"/></td>
			</tr>
			</div>
 -->

			</table>
			
			<div id="imeiId">
			IMEI
			<input type="text" name="imei" id="imei"/>
			
			</div>
			
		</form:form>
	</div>


	<div align="center">
		<h1>Fill Info</h1>

		<form:form>
			<table border="1">

				<tr>
					<td>First Name</td>
					<td><input type="text" name="first" id="firstname" /></td>
				</tr>
			
				<tr>
					<td>Middle Name</td>
					<td><input type="text" name="middle" id="middlename" readonly /></td>
				</tr>
			
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="last" id="lastname" readonly /></td>
				</tr>
			
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Submit" id="submitbutton" ></td>
				</tr>
			</table>
		</form:form>
	</div> 
 </body>
</html>
