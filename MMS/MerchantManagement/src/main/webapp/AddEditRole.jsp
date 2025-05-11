<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MERCHANT MANAGEMENT</title>
<jsp:include page="Common/CommonImports.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/newCustom.css" />
<script type="text/javascript" src="/js/addRole.js"></script>
<script type="text/javascript" src="/js/login.js"></script>

</head>
<body>
	<%@include file="header.jsp"%>

	<div class="alert alert-danger jqueryError" role="alert"
		style="display: none" id="jqueryError"></div>
	<c:if test="${not empty successStatus}">
		<div class="alert alert-success" role="alert">${successStatus}
			<input type="hidden" id="success" value="successStatus"></input>
		</div>
	</c:if>
	<c:if test="${not empty errorStatus}">
		<div class="alert alert-danger" role="alert">${errorStatus}
			<input type="hidden" id="error" value="errorStatus"></input>
		</div>
	</c:if>
	<div id="errType" class="alert alert-danger" role="alert"
		style="display: none"></div>
	<div id="mypanelId">
		<strong><span class="glyphicon glyphicon-th"></span> <span
			data-i18n="Data">Role</span></strong>
	</div>
	<div class="panel-body">
		<c:if test="${not empty addRole}">
			<c:url value="addRole" var="submitRoleDetails" />
		</c:if>
		<c:if test="${not empty editRole}">
			<c:url value="editRole" var="submitRoleDetails" />
		</c:if>
		<form:form method="POST" id="addrole" modelAttribute="roleDTO"
			action="${submitRoleDetails}" autocomplete="off">

			<!-- First row -->
			<div class="row">
				<div class="col-sm-12">
					<div class="container-fluid">
					<form:hidden path="userId" id="userId" name="userId"
							value="${userId}" />
						<form:hidden path="roleId" id="roleId" name="roleId"
							value="${roleDTO.roleId}" />
						<div class="col-sm-6">
							<label>Role Name<span style="color: red">*</span></label>
							<c:if test="${not empty addRole}">
								<form:input path="roleName" id="roleName" name="roleName"
									maxlength="50" class="form-control medantory"
									placeholder="Enter role name" autofocus="autofocus" value="" />
							</c:if>
							<c:if test="${not empty editRole}">
								<form:hidden path="roleId" id="roleId" name="roleId"
									value="${roleDTO.roleId}" />
								<form:input path="roleName" id="roleName"
									value="${roleDTO.roleName}" name="roleName" readonly="true"
									maxlength="50" cssClass="form-control medantory" />

							</c:if>
							<div id="errRoleName">
								<span for="roleName" class="error"><form:errors
										path="roleName" /></span>
							</div>
						</div>
						<div class="col-sm-6">
							<label>Role Description<span style="color: red">*</span></label>
							<form:input path="roleDesc" id="roleDesc" name="roleDesc"
								maxlength="50" class="form-control medantory"
								placeholder="Enter role Description" autofocus="autofocus"
								value="${roleDTO.roleDesc}" />
							<div id="errRoleDesc">
								<span for="roleDesc" class="error"> <form:errors
										path="roleDesc" /></span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Second row -->
			<div class="row">
				<div class="col-sm-12">
					<div class="container-fluid">
						<c:if test="${requestType eq 'A'}">
							<div class="col-sm-4">
								Session Timeout<span style="color: red">*</span></label>
								<form:input class="form-control input-square" path="sessionTime"
									id="sessionTime" name="sessionTime" autofocus="autofocus"
									placeholder="Enter sessionTime" maxlength="50" value="30" />
								<br />

								<div id="errSessionTime">
									<span for="sessionTime" class="error"><form:errors
											path="sessionTime" /></span>
								</div>
							</div>
							<div class="col-sm-4">
								<label>Institution<span style="color: red">*</span></label>
								<form:select class="form-control input-square" path="instId"
									id="instId" name="instId" autofocus="autofocus" value=""
									placeholder="select" maxlength="50">
									<option value=0>select</option>
									<option value=1>India</option>
									<option value=2>Qatar</option>
								</form:select>
							</div>
						</c:if>

						<c:if test="${requestType eq 'E'}">
							<div class="col-sm-6">
								Session Timeout<span style="color: red">*</span></label>
								<form:input class="form-control input-square" path="sessionTime"
									id="sessionTime" name="sessionTime" autofocus="autofocus"
									placeholder="Enter sessionTime" maxlength="50"
									value="${roleDTO.sessionTime}" />
								<br />

								<div id="errSessionTime">
									<span for="sessionTime" class="error"><form:errors
											path="sessionTime" /></span>
								</div>
							</div>
							<div class="col-sm-6">
								<label>Institution<span style="color: red">*</span></label>
								<form:select class="form-control input-square" path="instId"
									id="instId" name="instId" autofocus="autofocus"
									value="${roleDTO.instId}" placeholder="--select--"
									maxlength="50">
									<option value=1>India</option>
									<option value=2>Qatar</option>
								</form:select>
							</div>
						</c:if>
						<div id="errInstId">
							<span for="instId" class="error"><form:errors
									path="instId" /></span>
						</div>

					</div>
				</div>
			</div>
			<!-- Third row -->
			<div class="row">
				<div class="col-sm-12">
					<div class="container-fluid">
						<div class="col-sm-6">
							<label>RoleType<span style="color: red">*</span></label>
							<c:if test="${requestType eq 'A'}">
								<form:select class="form-control input-square" path="roleType"
									id="roleType" name="roleType" autofocus="autofocus" value=""
									placeholder="select" maxlength="50">
									<option value="M">Maker</option>
									<option value="C">Checker</option>
									<option value="V">Viewer</option>
								</form:select>
							</c:if>
							<c:if test="${requestType eq 'E'}">
								<form:select class="form-control input-square" path="roleType"
									id="roleType" name="roleType" autofocus="autofocus" value=""
									placeholder="select" maxlength="50">
									<c:if test="${roleDTO.roleType == 'M'}">
										<option value="M">Maker</option>
										<option value="C">Checker</option>
										<option value="V">Viewer</option>
									</c:if>
									<c:if test="${roleDTO.roleType == 'C'}">
										<option value="C">Checker</option>
										<option value="M">Maker</option>
										<option value="V">Viewer</option>

									</c:if>
									<c:if test="${roleDTO.roleType == 'V'}">
										<option value="V">Viewer</option>
										<option value="M">Maker</option>
										<option value="C">Checker</option>
									</c:if>
								</form:select>
							</c:if>
							<div id="errRoleType">
								<span for="roleType" class="error"><form:errors
										path="roleType" /></span>
							</div>


						</div>
						<div class="col-sm-6">
							<label>Module List<span style="color: red">*</span></label>
							<form:select class="form-control input-square" path="moduleId"
								id="moduleId" name="moduleId" autofocus="autofocus" value=""
								placeholder="select" maxlength="50">
								<option value=0>select</option>
								<c:forEach items="${moduleNames}" var="module">
									<option value="${module.moduleId}">${module.moduleName}</option>
								</c:forEach>
							</form:select>
							<div id="errModuleId">
								<span for="moduleId" class="error"><form:errors
										path="moduleId" /></span>
							</div>

						</div>
					</div>
				</div>
			</div>
			<br>
			<!-- Fourth row -->
			<div class="row">
				<div class="col-sm-12">
					<div class="container-fluid">

						<div class="col-sm-6">
							<div id="mypanelId">

								<strong><span class="glyphicon glyphicon-plus-sign"></span>
									<span data-i18n="Data">Available Function</span></strong>
							</div>
						</div>
						<div class="col-sm-6">
							<div id="mypanelId">
								<strong><span class="glyphicon glyphicon-certificate"></span><span
									data-i18n="Data">Selected Function</span></strong>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">


					<div class="container-fluid" style="margin-left: 20px">
						<div class="col-sm-5">
							<form:select id="availableFunList" path="availableFunList"
								multiple="multiple" name="availableFunList"
								autofocus="autofocus" value="" class="form-control input-square"
								size="5" style="width:75%">

								<form:options itemLabel="funcId" itemValue="funcName"
									items="${functionList}" />
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

							<c:if test="${requestType eq 'A'}">
								<form:select id="assignFunList" path="assignFunList"
									multiple="multiple" size="5" style="width:75%"
									class="form-control input-square" name="assignFunList"
									autofocus="autofocus" value=""
									placeholder="select function List" maxlength="50">

								</form:select>
							</c:if>

							<c:if test="${requestType eq 'E'}">
								<form:select id="assignFunList" path="assignFunList"
									multiple="multiple" size="10" style="width:75%"
									class="form-control input-square" name="assignFunList"
									autofocus="autofocus" placeholder="" maxlength="50">

									<form:options itemLabel="funcName" itemValue="funcId"
										items="${assignFunList}" />
								</form:select>
							</c:if>
							<div id="errAssignFunList">
								<span for="assignFunList" class="error"><form:errors
										path="assignFunList" /></span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<span id="msg"></span>
			<div align="center">
				<br> <br>

				<c:if test="${requestType eq 'A'}">
					<c:if test="${not empty showbutton}">
						<button type="button" class="btn btn-success" id="roleSubmit"
							onclick="validateAddEditRole('${showbutton}');">Add</button>
					</c:if>
					<button type="button" id="resetBtn" name="resetBtn"
						class="btn btn-primary">RESET</button>
				</c:if>
				<c:if test="${requestType eq 'E'}">
					<c:if test="${not empty showbutton}">
						<button type="button" class="btn btn-success" id="roleSubmit"
							onclick="validateAddEditRole('${showbutton}');">Edit</button>
					</c:if>
				</c:if>
				<button type="button" id="cancelBtn" class="btn btn-primary"
					name="cancelBtn">Back</button>
			</div>
		</form:form>
		<%@include file="footer.jsp"%>
</body>

</html>