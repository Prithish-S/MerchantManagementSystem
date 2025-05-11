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
<script type="text/javascript">
$(document).ready(function() {
	$("#errType").hide();
	$("form :input").change(function() {
		$(this).closest('form').data('changed', true);
	
	});
});
$(function () {$('#searchBtn').click(function(){
		var onDate = document.getElementById("onDate").value;
		var processName = document.getElementById("processName").value;
		var userId = document.getElementById("userId").value;		
		//alert(onDate);
		url = '/generateReport';
		var data= "processName,"+processName +",onDate,"+onDate+",userId,"+userId;
		//alert(data);
		postData(url, data);
				
	});
});		
	function viewDownloadDetails(){
		var onDate = document.getElementById("onDate").value;
		var processName = document.getElementById("processName").value;
		var userId = document.getElementById("userId").value;		
		//alert(onDate);
		url = '/downloadFile';
		var data= "processName,"+processName +",onDate,"+onDate+",userId,"+userId;
		//alert(data);
		postData(url, data);
	}

	
</script>
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
		style="display: hidden"></div>

<form:form method="post" id="reportDownload" class="form-group"
		modelAttribute="FileMasterDTO" autocomplete="off" width="75%">
		<form:hidden path="userId" id="userId" name="${userId} }" />

	<h3>Report Download</h3>
		<div>
				<div>
					<label>Process Name<span
						style="color: red">*</span></label>
					<div>
					<form:select path="processName"
						id="processName" name="processName" autofocus="autofocus" class="input-field"
						value="${processName}" placeholder="select" maxlength="10">
						<option value="D">Detailed Report</option>
						<option value="S">Summary Report</option>
					</form:select>
					<div id="errProcessName">
						<span for="processName" class="error"><form:errors
								path="processName" /></span>
					</div>
					</div>
				</div>
			
		
			<div>
					<label>Report Date<span
						style="color: red">*</span></label>
						<div>
					<form:input type="date" placeholder="yyyy/mm/dd" path="onDate" id="onDate"
						name="onDate" value="" class="input-field" />
					<div id="errOnDate">
						<span for="onDate" class="error"><form:errors path="onDate" /></span>
					</div>
				</div>
				</div>
	
			<div>
				<button type="button" id="searchBtn" name="searchBtn" class="btn btn-primary">Generate</button>
				<button type="button" id="backBtn" onclick="viewDownloadDetails()" name="backBtn" class="btn btn-primary">Download</button>
				
			</div>
		</div>

	</form:form>

				<%@include file="footer.jsp"%>
			
</body>
</html>