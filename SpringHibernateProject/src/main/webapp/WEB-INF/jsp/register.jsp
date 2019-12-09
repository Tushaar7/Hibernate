<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="student" commandName="student"
		action="saveStudent.htm">
		<c:out value="${msg}" />
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input type="text" path="name" />
			</tr>
			<tr>
				<td>Address:</td>
				<td><form:input type="text" path="addr" />
			</tr>
			<tr>
				<td>Phone:</td>
				<td><form:input type="text" path="ph" />
			</tr>
			<tr colspan="2">
				<td><input type="submit" value="Save">
			</tr>
		</table>
	</form:form>

</body>
</html>