<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<table border="0">
			<tr>
				<td colspan="2" align="center"><h2>Registration Success...!!!</h2></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<h3>Thank you for registration! Here's your details... </h3>
				</td>
			</tr>
			
			<tr>
				<td>Username:</td>
				<td>${userForm.username}</td>
			</tr>
			
			<tr>
                <td>E-mail:</td>
                <td>${userForm.email}</td>
            </tr>
            
            <tr>
                <td>Birthday:</td>
                <td>${userForm.birthDate}</td>
            </tr>
            
            <tr>
                <td>Profession:</td>
                <td>${userForm.profession}</td>
            </tr>
		</table>
	</div>
</body>
</html>