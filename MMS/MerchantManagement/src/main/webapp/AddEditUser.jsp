<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MERCHANT MANAGEMENT</title>
<link rel="stylesheet" type="text/css" href="/css/newCustom.css" />
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/addUser.js"></script>
<script type="text/javascript" src="/js/addRole.js"></script>

<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="header.jsp"%>

	<form:form method="post" id="adduser" modelAttribute="UserDTO"
		action="" autocomplete="off">
		<form:hidden path="userId" id="userId" value="${userId}" />

		<div>User Registration</div>
		<hr>
		<div>
			<c:if test="${not empty adduser}">
				<form:input path="loginId" id="loginId" name="loginId"
					maxlength="50" placeholder="Enter login Id" autofocus="autofocus"
					value="" class="input-field" />
			</c:if>
			<div id="errLoginId">
				<span for="loginId" class="error"><form:errors path="loginId" /></span>
			</div>
		</div>
		<div>
			<form:input path="firstName" id="firstName" name="firstName"
				autofocus="autofocus" placeholder="Enter first Name" maxlength="50"
				class="input-field" />

			<div id="errFirstName">
				<span for="firstName" class="error"><form:errors
						path="firstName" /></span>
			</div>
		</div>
		<div>
			<form:input path="lastName" id="lastName" name="lastName"
				autofocus="autofocus" placeholder="Enter last Name" maxlength="50"
				class="input-field" />
			<div id="errLastName">
				<span for="lastName" class="error"><form:errors
						path="lastName" /></span>
			</div>
		</div>
		<div>
			<form:select class="select-field" path="country" multiple="country"
				id="country" name="country" autofocus="autofocus" value=""
				placeholder="--select--" maxlength="50" size="1">
				<c:forEach items="${countryNames}" var="country">
					<option value=0>select</option>
					<option value="${country.countryid}">${country.countryname}</option>
				</c:forEach>
			</form:select>
			<div id="errcountry">
				<span for="country" class="error"><form:errors path="country" /></span>
			</div>
		</div>
		<div>
			<form:select class="input-field" path="state" id="state" name="state"
				autofocus="autofocus" value="" placeholder="Enter state"
				maxlength="50">
				<form:options itemLabel="stateid" itemValue="statename"
					items="${stateList}" />`
			</form:select>
			<div id="errState">
				<span for="state" class="error"><form:errors path="state" /></span>
			</div>
		</div>
		<div>
			<form:select class="input-field" path="city" id="city" name="city"
				autofocus="autofocus" value="" placeholder="Enter city"
				maxlength="50">
				<form:options itemLabel="cityid" itemValue="cityname"
					items="${cityList}" />`
		</form:select>
			<div id="errCity">
				<span for="city" class="error"><form:errors path="city" /></span>
			</div>
		</div>
		<div>
			<label>Role Type<span style="color: red">*</span></label><br />
			<form:select path="roleType" class="input-field" id="roleType"
				name="roleType" autofocus="autofocus" value="${userVO.roleType}"
				placeholder="select" maxlength="50">
				<option value="M">Maker</option>
				<option value="C">Checker</option>
				<option value="V">Viewer</option>
			</form:select>
		</div>
		<c:if test="${requestType eq 'A'}">
		<div class="role-title">User Role Map</div>
		<div class="row">
			<div class="col-sm-12">
					<div class="col-sm-5">
						
							
								<strong>Available Roles</strong><br />

								<form:select class="select-field" path="roleList"
									multiple="multiple" id="roleList" name="roleList"
									autofocus="autofocus" value="" placeholder="--select--"
									maxlength="40" size="5">
									<form:options itemLabel="roleId" itemValue="roleName"
										items="${mappedRoles}" />`
						</form:select>
							</div>

							<div class="col-sm-1">

								<div class="row">
									<div class="col-sm-12">
										<img id="ShiftRight" src="images/transfer_right.png"
											style="width: 25px;">
									</div>
								</div>

								<div class="row">
									<div class="col-sm-12">
										<img id="ShiftLeft" src="images/transfer_left.png"
											style="width: 25px;">
									</div>
								</div>
							</div>
							<div class="col-sm-6">

								<strong>Selected Role</strong><br />
								<form:select id="assRoleList" path="assRoleList"
									multiple="multiple" class="select-field" name="assRoleList"
									autofocus="autofocus" value="" placeholder="Enter RoleType"
									maxlength="40" size="5">
								</form:select>

							</div>
						
					</div>
				</div>
		
		</c:if>
		<div align="center">
			<br>
			<button type="button" class="btn btn-success" id="addUserBtn">Submit</button>
			<button type="button" class="btn btn-primary">Back</button>
		</div>
	</form:form>
	<%@include file="footer.jsp"%>

</body>
</html>