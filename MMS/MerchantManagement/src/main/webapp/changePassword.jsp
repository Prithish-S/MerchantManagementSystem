<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MERCHANT MANAGEMENT</title>

<jsp:include page="Common/CommonImports.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/newCustom.css" />

<style>
.contain {
	height: auto;
	width: auto;
	margin: 20px 250px 0px 250px;
	box-shadow: 0px 0px 40px -25px rgb(110, 110, 110);
}

.sub-container {
	width: auto;
	padding: 50px 20px 10px 0px;
	align-items: center;
	margin: 0% auto;
}

.row-element {
	display: grid;
	grid-template-columns: 35% 65%;
	white-space: nowrap;
	width: auto;
	margin: 0px 0px 0px 100px;
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

.heading {
	display: flex;
	justify-content: space-between;
	background-color: #8093ad;
	height: 30px;
	align-items: center;
	color: white;
	margin-bottom: 10px;
	letter-spacing: 0.5px;
	padding: 0px 10px 0px 15px;
}

.close-btn {
	cursor: pointer;
	font-size: 25px;
	color: white;
}

.close-btn:hover {
	color: white;
}

#popupMessage {
	text-align: justify;
	margin: 25px;
}

#closepopup {
	padding: 10px 20px;
	margin-bottom: 15px;
	background-color: #8093ad;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

#closepopup:hover {
	background-color: #b3bfcf;
}

.overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100vw;
	 background: rgba(0, 0, 0, 0.7);
	height: 100vh;
	z-index: 1;
	display: none;
}

.content {
	box-shadow: 0px 0px 15px rgb(143, 159, 188);
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%) scale(0);
	background: #fff;
	width: 450px;
	height: auto;
	z-index: 2;
	text-align: center;
	box-sizing: border-box;
	transition: opacity 0.3s ease-in-out;
}

.popup.active .overlay {
	display: block;
}

.popup.active .content {
	transition: all 250ms ease-in-out;
	transform: translate(-50%, -50%) scale(1);
}

.info {
	position: fixed;
	top: 30%;
	right: 18.5%;
	margin: 10px;
	height: 40px;
}
</style>
</head>
<body onload="noBack();">
	<c:if test="${empty firstlogin && empty hideOld}"> 
	<%@include file="header.jsp"%>
	</c:if>
	<div class="row">

		<c:if test="${not empty successStatus}">
			<div class="alert alert-success" role="alert">${successStatus}</div>
		</c:if>
		<c:if test="${not empty errorStatus}">
			<div class="alert alert-danger" role="alert">${errorStatus}</div>
		</c:if>
	</div>

	<h3 class="title">
		CHANGE PASSWORD
	</h3>
	<div class="contain">
		<div class="sub-container">

			<c:url value="changeUserPassword" var="changeUserPassword" />

			<form:form method="POST" id="addEditUser" modelAttribute="loginDTO"
				action="${changeUserPassword}" autocomplete="off">
				<form:hidden path="userId" id="userId" name="${userId}" />
				
				<div class="container">
					<a href="#" onclick="showPopup()"> <img
						src="images/info.png" class="info" alt="Password Policy"></a>
				</div>

				<div class="popup" id="popup" >
					<div class="overlay" onclick="togglePopup()"></div>
					<div class="content">
						<div class="heading">
							<h4>Password Policy</h4>
							<div class="close-btn" onclick="togglePopup()">&times;</div>
						</div>
						<p id="popupMessage">Your desired password must be at least 8
							- 16 character long. And should have at least 1 numeric, 1
							Capital letter and 1 Special character. Passwords are case
							sensitive.</p>
						<button onclick="togglePopup()" id="closepopup">OK</button>
					</div>
				</div>
				<form:hidden path="userId" id="userId" />
				<form:hidden path="loginId" id="loginId" value="${loginId}" />
				<form:hidden path="firstLogin" id="firstLogin" value="${loginDTO.firstLogin}" />
				<c:if test="${empty hideOld }">
					<div class="row-element">
						<label>Old Password<span
							style="color: red">*</span></label>
						<div>
							<form:password autofocus="autofocus" path="oldPassword"
								id="oldPassword" name="oldPassword" placeholder=""
								cssClass="form-control medantory" />
							<div id="erroldPassword" class="error">
								<span for="oldPassword" class="error"><form:errors
										path="oldPassword" /></span>
							</div>
						</div>
					</div>

				</c:if>
				<!-- Second row -->
				<div class="row-element">
					<label>New Password<span
						style="color: red">*</span></label>
					<div>
						<form:password path="newPassword" id="newPassword" name="password"
							placeholder="" cssClass="form-control medantory" />
						<div id="errnewPassword" class="error">
							<span for="newPassword" class="error"><form:errors
									path="newPassword" class="error" /></span>
						</div>
					</div>
				</div>

				<!--Third row  -->
				<div class="row-element">
					<label>Confirm Password<span
						style="color: red">*</span></label>
					<div>
						<form:password path="confirmPassword" id="confirmPassword"
							name="confirmPassword" placeholder=""
							cssClass="form-control medantory" />
						<div id="errconfirmPassword" class="error">
							<span for="confirmPassword" class="error"><form:errors
									path="confirmPassword" /></span>
						</div>
					</div>
				</div>

				<c:if test="${not empty firstlogin}">
					<!--Fourth row  -->
					<div class="row-element">
						<label>Password Recovery Question<span
							style="color: red">*</span></label>
						<div>
							<form:select path="pwdRecQues" id="pwdRecQues"
								class="form-control">
								<form:option value="--SELECT--" style="display:none" />
								<form:options itemLabel="lkpvalue" itemValue="lkpdesc"
									items="${psswdRecryQues}" />
							</form:select>
							<div id="errpwdRecQues" class="error">
								<span for="pwdRecQues" class="error"><form:errors
										path="pwdRecQues" /></span>
							</div>
						</div>
					</div>

					<!--Fifth row  -->
					<div class="row-element">
						<label>Password Recovery Answer<span style="color: red">*</span></label>
						<div>
							<form:input path="pwdRecAns" id="pwdRecAns" name="pwdRecAns"
								placeholder="" cssClass="form-control medantory" />

							<div id="errpwdRecAns" class="error">
								<span for="pwdRecAns" class="error"><form:errors
										path="pwdRecAns" /></span>
							</div>
						</div>
					</div>
				</c:if>

				<div class="user-btn">
					<c:if test="${not empty showButton}">
						<form:button type="button" id="chnPassword" value="Submit"
							class="btn btn-success">
							SUBMIT
						</form:button>
						<form:button id="resetBtn" type="button" value="Reset"
							class="btn btn-danger">
							RESET
						</form:button>
					</c:if>

					<c:if test="${not empty recButton}">
						<button type="button" id="recPassword" value="Submit"
							class="btn btn-success">
							SUBMIT
						</button>
						<button id="resetBtn" type="button" value="Reset"
							class="btn btn-danger">
							RESET
						</button>
					</c:if>
				</div>

				<form:hidden path="password" id="password"
					name="password" />
			</form:form>
		</div>
	</div>

	<!-- /container -->
<c:if test="${empty firstlogin && empty hideOld}"> 
	
	<div id="footer" class="footer">
		<div class="container-fluid">
			<p class="muted credit">
				<%@include file="footer.jsp"%>
			</p>
		</div>
	</div>
</c:if>
</body>
</html>
