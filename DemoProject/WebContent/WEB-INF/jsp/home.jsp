<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fill up the data</title>
</head>
<body bgcolor="gray">

	<div align="center">

		<h1>${msg}</h1>

		<form:form action="saverec" commandName="save" method="post">

			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="uname" /></td>
					<br>
				</tr>
				
				<tr>
					<td>Mobile No.</td>
					<td><input type="text" name="number" /></td>
					<br>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit" value="Submit" /> </td>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>