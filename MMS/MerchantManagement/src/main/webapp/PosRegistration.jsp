<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MERCHANT MANAGEMENT</title>
<jsp:include page="Common/CommonImports.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/addUser.js"></script>
<script type="text/javascript" src="/js/PosRegistration.js"></script>

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
			data-i18n="Data">Point On Sale Machine On-board</span></strong>
	</div>
	<div class="panel panel-default">
		<c:if test="${not empty addMerchant}">
			<c:url value="posOnboard" var="submitRoleDetails" />
		</c:if>
		<c:if test="${not empty editMerchant}">
			<c:url value="editPos" var="submitRoleDetails" />
		</c:if>
		<form:form method="POST" id="addPos" modelAttribute="PosDTO"
			action="${submitRoleDetails}" autocomplete="off">


			<!-- First row -->

			<div class="row">
				<div class="col-sm-12">
					<form:hidden path="userId" id="userId" name="userId" value="${userId}" />
					<form:hidden path="merchant_id" id="merchant_id" name="merchant_id" value="${merchant_id}" />

					<div class="col-sm-6">
						<label>POS ID<span style="color: red">*</span></label>
						<c:if test="${not empty addMerchant}">
							<form:input path="pos_machine_id" id="pos_machine_id" name="pos_machine_id"
								maxlength="50" class="form-control medantory"  readonly="true"
								placeholder="Enter pos_machine_id" autofocus="autofocus" value="AUTO" />
						</c:if>
						<c:if test="${not empty editMerchant}">

							<form:input path="pos_machine_id" id="pos_machine_id" value="${posDTO.pos_machine_id}"
								name="pos_machine_id" readonly="true" maxlength="50"
								cssClass="form-control medantory" />

						</c:if>
						<div id="errPos_machine_id">
							<span for="pos_machine_id" class="error"><form:errors
									path="pos_machine_id" /></span>
						</div>
					</div>
					<div class="col-sm-6">
							<label>Serial Number<span style="color: red">*</span></label>
						<form:input path="serial_number" id="serial_number" name="serial_number"
							maxlength="50" class="form-control medantory"
							placeholder="Enter serial number" autofocus="autofocus"
							value="${posDTO.serial_number}" />
						<div id="errSerial_number">
							<span for="serial_number" class="error"> <form:errors
									path="serial_number" /></span>
						</div>
					</div>
				
			</div>
<!-- Second row -->

			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-6">
						<label>location<span style="color: red">*</span></label>
						<form:input path="location" id="location" name="location"
							maxlength="50" class="form-control medantory"
							placeholder="Enter location" autofocus="autofocus"
							value="${posDTO.location}" />
						<div id="errLocation">
							<span for="location" class="error"> <form:errors
									path="location" /></span>
						</div>
					</div>
					

					<div class="col-sm-6">
						<label>Machine Model<span style="color: red">*</span></label>
						<form:select class="input-field" path="machine_model" id="machine_model"
							name="machine_model" autofocus="autofocus" value=""
							placeholder="" maxlength="50">
							<option value="1">Clover Mini</option>
							<option value="2">Square Terminal</option>
							<option value="3">Verifone VX 820</option>
						</form:select>
						<div id="errmachine_model">
							<span for="machine_model" class="error"><form:errors
									path="machine_model" /></span>
						</div>
					</div>
				</div>
			</div>
			<!-- Third row -->
			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-6">
						<label>Install Date<span style="color: red">*</span></label>
						<c:if test="${requestType eq 'A'}">
							<input type="date" id="install_date" class="input-field"
								path="install_date" value="" maxlength="50" />
						</c:if>
						<c:if test="${requestType eq 'E'}">
							<input type="date" id="install_date" class="input-field"
								value="${posDTO.install_date}" maxlength="50"
								pattern="mm/dd/yyyy" />
						</c:if>
						<div id="errInstall_date">
							<span for="install_date" class="error"><form:errors
									path="install_date" /></span>
						</div>
					</div>
					<div class="col-sm-6">
						<label>Last Date<span style="color: red">*</span></label>
						<c:if test="${requestType eq 'A'}">
							<input type="date" id="last_maintenance_date" class="input-field"
								path="last_maintenance_date" value="" maxlength="50" />
						</c:if>
						<c:if test="${requestType eq 'E'}">
							<input type="date" id="last_maintenance_date" class="input-field"
								value="${posDTO.last_maintenance_date}" maxlength="50"
								pattern="mm/dd/yyyy" />
						</c:if>
						<div id="errLast_maintenance_date">
							<span for="updlast_maintenance_dateated_at" class="error"><form:errors
									path="last_maintenance_date" /></span>
						</div>
					</div>
					</div>
					</div>
			<!-- Fourth row -->
			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-6">
						<label>Created Date<span style="color: red">*</span></label>
						<c:if test="${requestType eq 'A'}">
							<input type="date" id="created_at" class="input-field"
								path="created_at" value="" maxlength="50" />
						</c:if>
						<c:if test="${requestType eq 'E'}">
							<input type="date" id="created_at" class="input-field"
								value="${merchantDTO.created_at}" maxlength="50"
								pattern="mm/dd/yyyy" />
						</c:if>
						<div id="errCreated_at">
							<span for="created_at" class="error"><form:errors
									path="created_at" /></span>
						</div>
					</div>
					<div class="col-sm-6">
						<label>Updated Date<span style="color: red">*</span></label>
						<c:if test="${requestType eq 'A'}">
							<input type="date" id="updated_at" class="input-field"
								path="updated_at" value="" maxlength="50" />
						</c:if>
						<c:if test="${requestType eq 'E'}">
							<input type="date" id="updated_at" class="input-field"
								value="${merchantDTO.updated_at}" maxlength="50"
								pattern="mm/dd/yyyy" />
						</c:if>
						<div id="errUpdated_at">
							<span for="updated_at" class="error"><form:errors
									path="updated_at" /></span>
						</div>
					</div>
					</div>
					</div>
		<!-- Fourth row -->
			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-6">
						<label>Ip Address<span style="color: red">*</span></label>
						<form:input path="ip_address" id="ip_address"
							name="ip_address" autofocus="autofocus" value=""
							placeholder="Enter ip address" maxlength="50"
							class="input-field" />

						<div id="errIp_address">
							<span for="ip_address" class="error"><form:errors
									path="ip_address" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>Status<span style="color: red">*</span></label>
						<form:select class="input-field" path="status" id="status"
							name="status" autofocus="autofocus" value=""
							placeholder="" maxlength="50">
							<option value="A">Active</option>
							<option value="I">Out of Order</option>
						</form:select>
						<div id="errStatus">
							<span for="status" class="error"><form:errors
									path="status" /></span>
						</div>
					</div>
				</div>
			</div>
			<div align="center">

				<c:if test="${requestType eq 'A'}">
					<c:if test="${not empty showbutton}">
						<button type="button" class="btn btn-success" id="roleSubmit"
							onclick="validateAddEditPos('${showbutton}');">Add</button>
					</c:if>
				</c:if>
				<c:if test="${requestType eq 'E'}">
					<c:if test="${not empty showbutton}">
						<button type="button" class="btn btn-success" id="roleSubmit"
							onclick="validateAddEditPos('${showbutton}');">Edit</button>
					</c:if>
				</c:if>
				<button type="button" id="cancelBtn" class="btn btn-primary"
					name="cancelBtn">Back</button>
			</div>
		</form:form>

	</div>
	<%@include file="footer.jsp"%>
</body>
</html>