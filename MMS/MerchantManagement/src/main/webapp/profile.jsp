<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MERCHANT MANAGEMENT</title>
<style>
table {
    width: 100%; 
    border-collapse: collapse; 
    margin: 20px 0; 
}

td {
    padding: 10px; 
    border: 1px solid #ddd; 
    text-align: left; 
}

th {
    background-color: #f2f2f2; 
    padding: 10px; 
}

tbody tr:nth-child(even) {
    background-color: #f9f9f9; 
}

tbody tr:hover {
    background-color: #f1f1f1; 
}
</style>
</head>
<body>
	<%@include file="header.jsp"%>
	<form:form method="post" id="profile" modelAttribute="UserDTO"	action="">
	<form:hidden path="userId" id="userId" value="${userId}" />
		<table>
			<tbody>

				<tr>

					<td>USER ID<span style="color: red"></span></label></td>
					<td>${userVO.userId }</td>

					<td>LOGINID<span style="color: red"></span></label></td>
					<td>${userVO.loginId }</td>


					<td>FIRST NAME<span style="color: red"></span></label></td>
					<td>${userVO.firstName }</td>

				</tr>


				<tr>


					<td>LAST NAME<span style="color: red"></span></label></td>
					<td>${userVO.lastName }</td>


					<td>DISPLAY NAME<span style="color: red"></span></label></td>
					<td>${userVO.firstName }</td>


					<td>COUNTRY<span style="color: red"></span></label></td>
					<td>${userVO.country }</td>


				</tr>

				<tr>

					<td>STATE<span style="color: red"></span></label></td>
					<td>${userVO.state}</td>

					<td>CITY<span style="color: red"></span></label></td>
					<td>${userVO.city}</td>

					<td>CITY<span style="color: red"></span></label></td>

					<td>${userVO.city}</td>
				</tr>
			</tbody>
		</table>
	</form:form>
	<%@include file="footer.jsp"%>

</body>
</html>