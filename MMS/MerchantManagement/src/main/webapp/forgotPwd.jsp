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

<link rel="stylesheet" type="text/css" href="/css/newCustom.css" />

<jsp:include page="Common/CommonImports.jsp"></jsp:include>
<script type="text/javascript" src="/js/changePasword.js"></script>
<style>
.title {
	display: grid;
	text-align: center;
}

.contain {
	height: auto;
	width: auto;
	margin: 20px 250px;
    box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.2), -2px -2px 8px rgba(255, 255, 255, 0.2);
}

.sub-container {
	width: fit-content;
	padding: 10px 20px;
	margin: 0% 14.5%;
}

.row-element {
	display: grid;
	grid-template-columns: 50% 50%;
	white-space: nowrap;
	width: auto;
	margin: 0px 50px;
	padding: 10px 5px;
	height: 50px;
}

.row-element label {
	height: 22px;
	width: 200px;
}

.row-element input {
	width: 200px;
	height: 22px;
	border: solid 1px #ADD8E6;
	padding: 3px;
}

.user-btn {
	margin-top: 10px;
	text-align: center;
}

.row-element input:focus {
	outline: none;
	box-shadow: 0 0 5px rgba(21, 203, 238, 1);
}
</style>

</head>
<body>

	<div class="row">

		<c:if test="${not empty successStatus}">
			<div class="alert alert-success" role="alert">${successStatus}</div>
		</c:if>
		<c:if test="${not empty errorStatus}">
			<div class="alert alert-danger" role="alert">${errorStatus}</div>
			<input type="hidden" id="error" value="errorStatus"></input>
		</c:if>
	</div>

	<div id="errMLid" class="alert alert-danger" role="alert"
		style="display: none; width: 30%; align: center;">
		Provide a Answer
	</div>

	<h3>Forgot Password</h3>


	<div class="contain">
		<div class="sub-container">
<%-- "
 --%>			<form:form method="get" id="verifyPwd" name="verifyPwd" class="form-signin" modelAttribute="loginDTO" action="verifyRecAns" autocomplete="off">

				<form:hidden path="loginId" id="loginId" value="${loginDTO.loginId}" />
				<form:hidden path="userId" id="userId" name="${userId}" />
				
				<div class="row-element">
					<label>Password Recovery Question<span
						style="color: red">*</span></label>
					<div>
						<form:input path="pwdRecQues" id="pwdRecQues" name="pwdRecQues"
							value="${loginDTO.pwdRecQues}" placeholder=""
							cssClass="form-control medantory" readonly="true" />

						<div id="errpwdRecQues" class="error">
							<span for="pwdRecQues" class="error"><form:errors
									path="pwdRecQues" /></span>
						</div>
					</div>
				</div>

				<!--Fifth row  -->
				<div class="row-element">
					<label>Password Recovery Answer<span
						style="color: red">*</span></label>
					<div>
						<input path="pwdRecAns" id="pwdRecAns" name="pwdRecAns"
							placeholder="" value="" cssClass="form-control medantory" />

						<div id="errpwdRecAns" class="error">
							<span for="pwdRecAns" class="error"><form:errors
									path="pwdRecAns" /></span>
						</div>
					</div>
				</div>

	
					<c:if test="${not empty showButton}">
					<div class="user-btn">
						<button type="button" id="verifyAns" value="verify"
							class="btn btn-success" onclick="postAction('/verifyRecAns');">VERIFY</button>
						<button id="resetBtn1" type="button" value="reset1"
							class="btn btn-danger">RESET</button>
						</div>
					</c:if>

	</form:form>
	</div>
	</div>

	<!-- /container -->

	<!-- /content -->
	<div id="footer" class="footer">
		<div class="container-fluid">
			<p class="muted credit">
				<%@include file="footer.jsp"%>
			</p>
		</div>
	</div>
</body>
</html>
