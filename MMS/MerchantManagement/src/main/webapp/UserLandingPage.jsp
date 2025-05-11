<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MERCHANT MANAGEMENT</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript" src="/js/jquery.min.js"></script>
</head>
<body>
	<%@include file="header.jsp"%>
	<h2>User</h2>
	<hr>
	<table id="role-table" class="table table-borderless table-data">

		<thead>
			<tr>
				<th>User id</th>
				<th>Login id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Country</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userDTO}">
				<tr>
					<td>${user.userId}</td>
					<td>${user.loginId}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.country}</td>
					<%-- 				 <td><a href="javascript:viewRole('${role.roleId}')" data-toggle="tooltip" title=""><span class="view"><spring:message code="label.msg.editAndView" /></span></a></td>
 --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@include file="footer.jsp"%>
</body>
</html>