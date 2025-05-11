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
<script type="text/javascript" src="/js/MerchantOnboard.js"></script>

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
			data-i18n="Data">Merchant On-board</span></strong>
	</div>
	<div class="panel panel-default">
		<c:if test="${not empty addMerchant}">
			<c:url value="merchantOnboard" var="submitRoleDetails" />
		</c:if>
		<c:if test="${not empty editMerchant}">
			<c:url value="editMerchant" var="submitRoleDetails" />
		</c:if>
		<form:form method="POST" id="addmerchant" modelAttribute="MerchantDTO"
			action="${submitRoleDetails}" autocomplete="off">


			<!-- First row -->

			<div class="row">
				<div class="col-sm-12">
					<form:hidden path="userId" id="userId" name="userId" value="${userId}" />

					<div class="col-sm-4">
						<label>Merchant Id<span style="color: red">*</span></label>
						<c:if test="${not empty addMerchant}">
							<form:input path="merchant_id" id="merchant_id" name="merchant_id"
								maxlength="50" class="form-control medantory"  readonly="true"
								placeholder="Enter merchant id" autofocus="autofocus" value="AUTO" />
						</c:if>
						<c:if test="${not empty editMerchant}">

							<form:input path="merchant_id" id="merchant_id" value="${merchantDTO.merchant_id}"
								name="merchant_id" readonly="true" maxlength="50"
								cssClass="form-control medantory" />

						</c:if>
						<div id="errMerchant_id">
							<span for="merchant_id" class="error"><form:errors
									path="merchant_id" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>Business Name<span style="color: red">*</span></label>
						<form:input path="business_name" id="business_name" name="business_name"
							maxlength="50" class="form-control medantory"
							placeholder="Enter Business Name" autofocus="autofocus"
							value="${merchantDTO.business_name}" />
						<div id="errBusiness_name">
							<span for="business_name" class="error"> <form:errors
									path="business_name" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>Address<span style="color: red">*</span></label>
						<form:input path="business_address" id="business_address" name="business_address"
							maxlength="50" class="form-control medantory"
							placeholder="Enter Business Address" autofocus="autofocus"
							value="${merchantDTO.business_address}" />
						<div id="errBusiness_address">
							<span for="business_address" class="error"> <form:errors
									path="business_address" /></span>
						</div>
					</div>
				</div>
			</div>
			<!-- Second row -->

			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-4">
						<label>Country<span style="color: red">*</span></label>
						<form:select class="select-field" path="country"
							multiple="country" id="country" name="country"
							autofocus="autofocus" value="" placeholder="--select--"
							maxlength="50" size="1">
							<c:forEach items="${countryNames}" var="country">
								<option value=0>select</option>
								<option value="${country.countryid}">${country.countryname}</option>
							</c:forEach>
						</form:select>
						<div id="errcountry">
							<span for="country" class="error"><form:errors
									path="country" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>State<span style="color: red">*</span></label>
						<form:select class="input-field" path="state" id="state"
							name="state" autofocus="autofocus" value=""
							placeholder="Enter state" maxlength="50">
							<form:options itemLabel="stateid" itemValue="statename"
								items="${stateList}" />`
			</form:select>
						<div id="errState">
							<span for="state" class="error"><form:errors path="state" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>City<span style="color: red">*</span></label>
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
				</div>
			</div>
<!-- Third row -->

			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-4">
						<label>Contact Number<span style="color: red">*</span></label>
						<form:input path="business_phone" id="business_phone"
							name="business_phone" autofocus="autofocus" value=""
							placeholder="Enter contact No" maxlength="50" class="input-field" />

						<div id="errBusiness_phone">
							<span for="business_phone" class="error"><form:errors
									path="business_phone" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>Zip Code<span style="color: red">*</span></label>
						<form:input path="zip_code" id="zip_code"
							name="zip_code" autofocus="autofocus" value=""
							placeholder="Enter Zip code" maxlength="50"
							class="input-field" />

						<div id="errZip_code">
							<span for="zip_code" class="error"><form:errors
									path="zip_code" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>Status<span style="color: red">*</span></label>
						<form:select class="input-field" path="merchant_status" id="merchant_status"
							name="merchant_status" autofocus="autofocus" value=""
							placeholder="" maxlength="50">
							<option value="A">Active</option>
							<option value="P">Pending</option>
							<option value="I">Out of Order</option>
						</form:select>
						<div id="errMerchant_status">
							<span for="merchant_status" class="error"><form:errors
									path="merchant_status" /></span>
						</div>
					</div>
				</div>
			</div>
			<!-- Fourth row -->

			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-4">
						<label>Website Address<span style="color: red">*</span></label>
						<form:input path="website_url" id="website_url"
							name="website_url" autofocus="autofocus" value=""
							placeholder="Enter websiteurl" maxlength="50" class="input-field" />

						<div id="errWebsite_url">
							<span for="website_url" class="error"><form:errors
									path="website_url" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>Owner Name<span style="color: red">*</span></label>
						<form:input path="owner_name" id="owner_name"
							name="owner_name" autofocus="autofocus" value=""
							placeholder="Enter owner name" maxlength="50"
							class="input-field" />

						<div id="errOwner_name">
							<span for="owner_name" class="error"><form:errors
									path="owner_name" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>Owner Email<span style="color: red">*</span></label>
						<form:input path="owner_email" id="owner_email"
							name="owner_email" autofocus="autofocus" value=""
							placeholder="Enter owner email" maxlength="50"
							class="input-field" />

						<div id="errOwner_email">
							<span for="owner_email" class="error"><form:errors
									path="owner_email" /></span>
						</div>
					</div>
				</div>
			</div>
			
		
			<!-- Fifth row -->

			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-4">
						<label>Business Type<span style="color: red">*</span></label>
						<form:select class="input-field" path="business_type" id="business_type" name="business_type"
							autofocus="autofocus" value="" placeholder="Enter business type"
							maxlength="50">
							<option value="1">Proprietor-ship</option>
							<option value="2">Partnership</option>
						</form:select>
						<div id="errBusiness_type">
							<span for="business_type" class="error"><form:errors path="business_type" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>Reg. Number<span style="color: red">*</span></label>
						<form:input path="registration_number" id="registration_number"
							name="registration_number" autofocus="autofocus" value=""
							placeholder="Enter registration number" maxlength="50"
							class="input-field" />

						<div id="errRegistration_number">
							<span for="registration_number" class="error"><form:errors
									path="registration_number" /></span>
						</div>
					</div>
					<div class="col-sm-4">
						<label>Tax Id<span style="color: red">*</span></label>
						<form:input path="tax_id" id="tax_id"
							name="tax_id" autofocus="autofocus" value=""
							placeholder="Enter tax id" maxlength="50"
							class="input-field" />

						<div id="errTax_id">
							<span for="tax_id" class="error"><form:errors
									path="tax_id" /></span>
						</div>
					</div>
				</div>
			</div>
				<!-- Sixth row -->

			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-4">
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
					<div class="col-sm-4">
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
					<div class="col-sm-4">
						<label>Business Email<span style="color: red">*</span></label>
						<form:input path="business_email" id="business_email"
							name="business_email" autofocus="autofocus" value=""
							placeholder="Enter business email" maxlength="50"
							class="input-field" />
						<div id="errBusiness_email">
							<span for="business_email" class="error"><form:errors path="business_email" /></span>
						</div>
					</div>
				</div>
			</div>
			
			<div align="center">

				<c:if test="${requestType eq 'A'}">
					<c:if test="${not empty showbutton}">
						<button type="button" class="btn btn-success" id="roleSubmit"
							onclick="validateAddEditMerchant('${showbutton}');">Add</button>
					</c:if>
				<!-- 	<button type="button" id="resetBtn" name="resetBtn"
						class="btn btn-primary">RESET</button> -->
				</c:if>
				<c:if test="${requestType eq 'E'}">
					<c:if test="${not empty showbutton}">
						<button type="button" class="btn btn-success" id="roleSubmit"
							onclick="validateAddEditMerchant('${showbutton}');">Edit</button>
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