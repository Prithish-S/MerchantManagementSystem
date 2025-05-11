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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/newCustom.css" />
<script type="text/javascript" src="/js/login.js"></script>
<style>
    body {
        background-color: #008080; /* Light pink background */
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .login-container {
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        max-width: 400px;
        width: 100%;
    }
    .login-logo {
        text-align: center;
        margin-bottom: 20px;
    }
    .login-logo img {
        width: 100px;
		border-radius:50%;
    }
    .form-control {
        border-radius: 20px;
        margin-bottom: 15px;
    }
    .btn-primary {
        background-color: #f06292; /* Pink button */
        border: none;
        border-radius: 20px;
    }
    .btn-primary:hover {
        background-color: #e91e63;
    }
    .showpwd {
        margin-bottom: 10px;
    }
</style>
<script type="text/javascript">
    $(function () {
        $('#forgtpwd').click(function() {
            alert("hello");
            if (($('#loginId').val() != "")) {
                var loginId = $('#loginId').val();
                alert(loginId);
                $('#loginId').val(loginId);
                $('#doLogin').attr('action', 'forgotpsswd');
                document.getElementById("doLogin").submit();
            } else if ($('#loginId').val() == "") {
                $('#errMsg').hide();
                $('#errMLid').show();
                $('#loginId').focus();
            }
        });
    });
</script>
</head>
<body>
    <div class="login-container">
        <div class="login-logo">
			<img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo">
        </div>
        <form:form method="post" id="doLogin" name="doLogin" modelAttribute="loginDTO" autocomplete="off">
            <form:hidden path="userId" id="userId" name="${userId}" />
            <div class="form-group">
                <label for="loginId">Enter UserName:</label>
                <form:input class="form-control" path="loginId" id="loginId" name="loginId" autofocus="autofocus"
                    placeholder="Enter Login ID" maxlength="50" />
            </div>
            <div class="form-group">
                <label for="password">Enter Password:</label>
                <form:input class="form-control" path="password" type="password" id="password" name="password"
                    placeholder="Enter Password" maxlength="25" />
            </div>
            <div class="form-group">
                <input type="checkbox" onclick="pwdView()" class="showpwd" /> Show Password
            </div>
            <div class="form-group">
                <form:button class="btn btn-primary btn-block" id="loginBtn" name="loginBtn" value="doLogin">
                    Login
                </form:button>
                <form:button class="btn btn-primary btn-block" id="forgtpwd" name="forgtpwd" value="forgotpsswd">
                    Forgot Password
                </form:button>
            </div>
        </form:form>
    </div>
</body>
</html>
