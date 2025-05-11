/**
 * 
 */
$(document).ready(function() {

	$('#loginBtn').click(function() {
		var loginId = $('#loginId').val();
		var password = $('#password').val();
		//alert(loginId + password)
		if (loginId != "" && password != "") {
			$('#doLogin').attr('action', 'doLogin');
			$('#doLogin').submit();
		}
	});
	
	
	
});


function pwdView() {
	var passwordInput = document.getElementById("password");
	passwordInput.type = (passwordInput.type === "password") ? "text" : "password";
}


function postData(action, data) {
	var loc = window.location;
	var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/'));
	linkurl = pathName + action;

	var form = document.createElement("form");
	form.method = "POST";

	var parameters = data.split(",");

	for (var i = 0; i < parameters.length; ++i) {
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



function urlPostAction(action) {
	//var loginId = $('#loginId').val();
	var userId = $('#userId').val();
	var data = "userId," + userId ;
	//alert(userId);
	postData(action, data);
}


