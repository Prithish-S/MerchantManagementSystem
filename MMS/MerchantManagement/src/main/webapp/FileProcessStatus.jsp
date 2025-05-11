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

<style type="text/css">
#file-table {
	margin: 10px;
	width: 99%;
}

#file-table thead tr th {
	font-size: 12px;
	font-weight: 200;
	padding: 2px;
	padding-left: 5px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {	
	$("#errType").hide();
	$("#file-table").dataTable();
	
	$('[data-toggle="tooltip"]').tooltip();
	});	
$(function() {$("#refreshBtn").click(function() {
	location.reload();
});
});
 function urlPostAction(action) {
		var userId = $('#userId').val();
		var data = "userId," + userId ;
		alert(userId);
		postData(action, data);
	}

</script>

</head>
<body>
	<%@include file="header.jsp"%>
	<form:form method="post" id="fileProcessStatus" class="form-group"
		modelAttribute="FileMasterDTO" autocomplete="off">
		<div class="table-responsive m-b-20">
		<form:hidden path="userId" id="userId" value="${userId}" />
		
			<table id="file-table" class="table table-borderless table-data">
				<thead>
					<tr>
						<th>File Name</th>
						<th>File Type</th>
						<th>File Status</th>
					</tr>
				</thead>
				<tbody>
				
				<c:forEach var="file" items="${process}">
						<tr>
							<td>${file.filename}</td>
							<td>LIST</td>	
							<c:choose>
								<c:when test="${file.status eq 'C'}">
									<td>COMPLETED</td>
								</c:when>
								<c:when test="${file.status eq 'P'}">
									<td>PENDING</td>
								</c:when>
								<c:otherwise>
									<td>IN PROGRESS</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br />
		<div align="center">
			<button type="button" id="refreshBtn" name="refreshBtn"
				class="btn btn-primary">
				Refresh
			</button>

			<button type="button" id="backBtn"
				onclick="urlPostAction('/fileExtraction');" name="backBtn"
				class="btn btn-primary">
				Back
			</button>

		</div>


	</form:form>
	<div id="footer" class="footer">
		<div class="container-fluid">
			<p class="muted credit">
				<%@include file="footer.jsp"%>
			</p>
		</div>
	</div>
</body>
</html>