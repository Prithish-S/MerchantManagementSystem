<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>MERCHANT MANAGEMENT</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<jsp:include page="Common/CommonImports.jsp"></jsp:include>
<script type="text/javascript" src="/js/viewRole.js"></script>
</head>
<body>
<%@include file="header.jsp"%>

<form:form method="post" id="rolelandingpage" name ="rolelandingpage" class="form-signin"
			modelAttribute="RoleDTO" autocomplete="off">
					<form:hidden path="userId" id="userId" name="userId" />
			
		 <div class="table-title">
                		<div class="row">
                    		<div class="col-sm-5">
                        		<h3>Role Landing Page</h3>
                   			 </div>
                   		<div class="col-sm-7">
		                    <button type="button" id="addRoleBtn" name="addRoleBtn"	class="btn btn-secondary"> 
		                    <strong><span class="glyphicon glyphicon-th"></span>Add Role</strong></button>
                         </div>
                	</div>
                </div>
		
		<br/>
		<!-- <div class="panel-body" >
			<table id="role-table" class="table table-striped" width="100%"> -->
			 <div class="table-responsive m-b-40">
          
                <table id="role-table" class="table table-borderless table-data">
			
			<thead>
				<tr>
					<th>Role Id</th>
					<th>Role Name</th>
					<th>Role Description></th>
					<th>Action</th>
				</tr>
			 </thead>
			 <tbody>
			 <c:forEach var="role" items="${roleDTOList}">
			 <tr>
				 <td>${role.roleId}</td>
				 <td>${role.roleName}</td>
				 <td>${role.roleDesc}</td>
				
				 <td><a href="javascript:viewRole('${role.roleId}')" data-toggle="tooltip" title="">View</a></td>
			
			 </tr>
			 </c:forEach>
			 </tbody>
			</table></div>
	<div align="center">
		<button type="button" id="cancelBtn" class="btn btn-primary btn-lg" name="cancelBtn">Back</button>
	</div>	
</form:form>
<%@include file="footer.jsp"%>

</body>

</html>