<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MERCHANT MANAGEMENT</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script>
$(document).ready(function() {	
	$("#errType").hide();
	$("#merchant-table").dataTable();
	$('[data-toggle="tooltip"]').tooltip(); 
	});	
	
	
	$(function () {$('#cancelBtn,#addMerBtn').click(function() {
			$('#merlandingpage').attr('action','viewMerchant');
			document.getElementById("merlandingpage").submit();
});

});
	function viewPos(merchantId) {
		action = '/getPosRegForm';
		var userId = $('#userId').val();
		var data = "merchant_id," + merchantId+",userId," + userId;

		alert(data);
		postData(action, data);
}

javascript:viewRole('${role.roleId}')
</script>
</head>
<body>
<%@include file="header.jsp"%>

<form:form method="post" id="merlandingpage" name ="merlandingpage" class="form-signin"
			modelAttribute="MerchantDTO" autocomplete="off">
		<form:hidden path="userId" id="userId" name="userId" value="${userId}" />
		
		 <div class="table-title">
                		<div class="row">
                    		<div class="col-sm-5">
                        		<h3>Merchant Landing Page</h3>
                   			 </div>
                   		<div class="col-sm-7">
		                    <button type="button" id="addMerBtn" name="addMerBtn"	class="btn btn-secondary"> 
		                    <strong><span class="glyphicon glyphicon-th"></span>Add Merchant</strong></button>
                         </div>
                	</div>
                </div>
		
		<br/>
		<!-- <div class="panel-body" >
			<table id="role-table" class="table table-striped" width="100%"> -->
			 <div class="table-responsive m-b-40">
          
                <table id="merchant-table" class="table table-borderless table-data">
	
		<thead>
			<tr>
				<th>Add POS</th>
				<th>Merchant id</th>
				<th>Name</th>
				<th>Contact No</th>
				<th>Email</th>
				<th>Website URL</th>
				<th>Owner Name</th>
				<th>Reg No</th>
				<th>Business Type</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="merchant" items="${merchantList}">
				<tr>
				<td><button type="button" class="btn btn-primary" id="addPos" onclick="javascript:viewPos('${merchant.merchant_id}')">ADD POS</button></td>
					<td>${merchant.merchant_id}</td>
					<td>${merchant.business_name}</td>
					<td>${merchant.business_phone}</td>
					<td>${merchant.business_email}</td>
					<td>${merchant.website_url}</td>
					<td>${merchant.owner_name}</td>
					<td>${merchant.registration_number}</td>
					<td>${merchant.business_type}</td>
					<td>${merchant.merchant_status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div align="center">
		<button type="button" id="cancelBtn" class="btn btn-primary btn-lg" name="cancelBtn">Back</button>
	</div>	
</form:form>
	<%@include file="footer.jsp"%>
</body>
</html>