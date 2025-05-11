/**
 * 
 */
/**
 * 
 */
$(document).ready(function() {
	$("#pwdPolicyMsg").hide();
	$('#errMLid').hide();
	$('#errCaptcha').hide();
	document.getElementById("closepopup").addEventListener("click", function(event) {
		event.preventDefault();
	});
	
	$('#resetBtn').click(function() {
		$('#addEditUser').find('input:password').val("");
		$("#erroldPassword").text("");
		$("#errnewPassword").text("");
		$("#errconfirmPassword").text("");

	});
	$("#chnPassword").click(function() {
		var flag2 = validatePassword($("#errnewPassword"), $("#newPassword").val());
		var flag3 = validatePassword($("#errconfirmPassword"), $("#confirmPassword").val());
		var flag =  flag2 && flag3;
		if (flag && $("#newPassword").val().trim() != $("#confirmPassword").val().trim()) {
			$("#errconfirmPassword").text("New password and Confirm password must be same");
			flag = false;
		}
		var pwdRec = $('#firstLogin').val();
		if (pwdRec == 'firstlogin') {
			if ($('#pwdRecQues').val() == '--SELECT--') {
				$("#errpwdRecQues").text("Please select a password recovery question");
				flag = false;
			} else {
				$("#errpwdRecQues").text("");
			}

			if ($('#pwdRecAns').val() == "") {
				$("#errpwdRecAns").text("Please type a password recovery answer");
				flag = false;
			} else {
				$("#errpwdRecAns").text("");
			}

		}
		if (flag) {
			var pwdval = $('#oldPassword').val();
			var pwdval = $('#newPassword').val();
			var pwdval = $('#confirmPassword').val();
			var pwdRec = $('#firstLogin').val();
			if (pwdRec == 'firstlogin') {
				var pwdrecans = $('#pwdRecAns').val();
			}

			$('#password').val(pwdval);
			$('#addEditUser').attr('action', 'changeUserPassword');
			$('#addEditUser').submit();
		}
	});

	
	$("#recPassword").click(function() {
				var flag2 = validatePassword($("#errnewPassword"), $("#newPassword").val());
				var flag3 = validatePassword($("#errconfirmPassword"),$("#confirmPassword").val());
				var flag = flag2 && flag3;
				if (flag && $("#newPassword").val().trim() != $("#confirmPassword").val().trim()) {
					$("#errconfirmPassword").text("New password and Confirm password must be same");
					flag = false;
				}

				if (flag) {
					var pwdval = $('#newPassword').val();
					var pwdval = $('#confirmPassword').val();
					$('#password').val(pwdval);
					$('#addEditUser').attr('action', 'changeUserPassword');
					$('#addEditUser').submit();
				}
			});
 });	
function postData(action, data) {
	var loc = window.location;
	var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/'));
	linkurl = pathName + action;
	var form = document.createElement("form");
	form.method = "POST";
	var parameters = data.split(",");
	for ( var i = 0; i < parameters.length; ++i) {
		var dynInput = document.createElement("input");
		dynInput.setAttribute("type", "hidden");
		dynInput.setAttribute("id", parameters[i]);
		dynInput.setAttribute("name", parameters[i]);
		++i;
		dynInput.setAttribute("value", parameters[i]);

		form.appendChild(dynInput);
	}
	document.body.appendChild(form);
	form.action = linkurl;
	form.submit();
}	
function postAction(url) {
	if ($('#pwdRecAns').val() != "") {
		var loginId = $('#loginId').val();
		var pwdrecans = $('#pwdRecAns').val();
		var pwdQues=$('#pwdRecQues').val();
		var data = "loginId," + loginId + ",pwdRecQues," + pwdQues	+ ",pwdRecAns," + pwdrecans;
		postData(url, data);
	} else {
		$('#errMLid').show();
		$('#pwdRecAns').focus();
	}
}

function togglePopup() {
	document.getElementById("popup").classList.toggle("active");
}

function showPopup() {
	togglePopup();
}


function validatePassword(msgID, Id) {

	var password = Id;
	var errPassword = msgID;

	var flag = true;
	if (password == "") {
		flag = false;
		errPassword.className = 'error';
		errPassword.html("Please Enter " + $("#" + Id).prev().prev().text()); 
		return false;
	}

	if (password.length < 8 || password.length > 16) {

		flag = false;
		errPassword.className = 'error';
		errPassword.html('Password must contain at least eight characters and maximum 16 characters');
		return false;
	}

	var regEx = /[a-z]/; 

	var test_bool = regEx.test(password);
	if (test_bool == false) {
		flag = false;
		errPassword.className = 'error';
		errPassword.html('Password must contain at least one lowercase letter (a-z)');
		return false;
	}

	var regEx = /[A-Z]/;  

	var test_bool = regEx.test(password);

	if (test_bool == false) {
		flag = false;
		errPassword.className = 'error';
		errPassword.html('Password must contain at least one uppercase letter (A-Z)');
		return false;
	}

	var regEx = /[0-9]/;  //A-Z0-9!@#$%*()]+$/i;

	var test_bool = regEx.test(password);
	if (test_bool == false) {

		flag = false;
		errPassword.className = 'error';
		errPassword.html('Password must contain at least one number (0-9)!');
		return false;
	}

	var regEx = /[!@#$%*()]/; 

	var test_bool = regEx.test(password);
	if (test_bool == false) {

		flag = false;
		errPassword.className = 'error';
		errPassword.html('Password must contain at least one special charecter!');
		return false;
	}

	if (flag) {
		errPassword.className = 'error';
		errPassword.html("");
	}
	return true;
}


