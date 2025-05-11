<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MERCHANT MANAGEMENT</title>
<link rel="stylesheet" href="/css/newCustom.css">
<jsp:include page="Common/CommonImports.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$('#jqueryError').hide();
		$("form :input").change(function() {

			$(this).closest('form').data('changed', true);
			
		});
		
	});
	
	$(function() {
		$("#ShiftRight,#ShiftLeft").click(
				function(event) {
					var ID = $(event.target).attr("ID");
					var ChooseFrom = ID == "ShiftRight" ? "#availFileList"
							: "#selectFileList";
					var moveTo = ID == "ShiftRight" ? "#selectFileList"
							: "#availFileList";
					var SelectData = $(ChooseFrom + " :selected").toArray();
					$(moveTo).append(SelectData);
					SelectData.remove;
				});
	});
	

	function validateSelectFileList(msgID) {

		var selectFileList = (document.getElementById("selectFileList").value)
				.replace(/^\s*|\s*$/g, '');
		var errSelectFileList = document.getElementById(msgID);
		var errorMessage = "";
		if (selectFileList == "") {
			errorMessage = document.getElementById("errMsgSelectLst").textContent;
		} else {
			errSelectFileList.innerHTML = "";
			return true;
		}
		errSelectFileList.className = 'error';
		errSelectFileList.innerHTML = errorMessage;
		return false;
	}

	function addEdit(status) {
		var loc = window.location;
		var pathName = loc.pathname.substring(0,
				loc.pathname.lastIndexOf('/') + 1);
		if (status == 'Y') {
			url = '/fileProcess';
		}
		var fileDestPath = document.getElementById("file_dest_path").value;
		var selectFileList = "";
		var fileList = $("#selectFileList").val();
		for (var i = 0; i < fileList.length; ++i) {
			selectFileList = selectFileList + fileList[i] + "#";
		}
		var data = "status," + status + ",file_dest_path," + fileDestPath + ",selectFileList,"
				+ selectFileList;
		postData(url, data);
	}

	function validateAddEditForm(id) {
		$('.jqueryError').text("");
		$('.jqueryError').hide();
		var check = false;
		if (!validateSelectFileList('errSelectFileList')) {

			check = true;
		}
		if (!check) {
			if ($('#extraction').data('changed')
					&& $('#selectFileList').val() != '') {
				addEdit(id);
			} else {
				$('#jqueryError')
						.text(
								'Move atleast one file to the right for extraction process!!!');
				$('#jqueryError').show();
				return false;
			}

		} else {
			return false;
		}

	}
	
</script>

</head>
<body>
	<%@include file="header.jsp"%>

	<div class="alert alert-danger jqueryError" role="alert"
		style="display: none; width: 30%; align: center;" id="jqueryError"></div>
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

	<form:form method="post" id="extraction" class="form-group"
		modelAttribute="FileMasterDTO" autocomplete="off"
		enctype="multipart/form-data">
					<form:hidden path="userId" id="userId" value="${userId}" />
		
		<h3 style="align:center;">EXTRACTION</h3>

		<div class="outercontainer">
			<c:if test="${requestType eq 'H'}">
				<div class="single-column-element">
					<label><h5>
							File Path <span style="color: red">*</span>
						</h5></label>
					<div>
						<form:input path="file_dest_path" id="file_dest_path"
							class="input-field" for="file_dest_path" value="${filePath}"
							style="width:300px" />
						<div id="errFileDestPath">
							<span for="file_dest_path" class="error"> <form:errors
									path="file_dest_path" /></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="container-fluid" style="margin-left: 20px">
							<div class="col-sm-5">
								<form:select class="select-field" path="availFileList"
									multiple="multiple" id="availFileList" name="availFileList"
									autofocus="autofocus" value="${availFiles}" size="3"
									placeholder="select" maxlength="40">

									<c:forTokens var="token" items="${availFiles}" delims=",">
										<option>${token}</option>
									</c:forTokens>
								</form:select>
								<div id="errAvailFileList">
									<span for="availFileList" class="error"><form:errors
											path="availFileList" /></span>
								</div>

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
								<form:select id="selectFileList" path="selectFileList"
									name="selectFileList" multiple="multiple" class="select-field"
									autofocus="autofocus" value="" placeholder="select" size="3"
									maxlength="40">
								</form:select>
								<div id="errSelectFileList">
									<span for="selectFileList" class="error"><form:errors
											path="selectFileList" /></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div align="center">
				<br> <br>
					<button type="button" id="extnBtn" name="extnBtn" class="btn btn-success"
						onclick="validateAddEditForm('Y');">Extract File</button>

				</div>
			</c:if>
		</div>
	</form:form>
	<%@include file="footer.jsp"%>
</body>
</html>