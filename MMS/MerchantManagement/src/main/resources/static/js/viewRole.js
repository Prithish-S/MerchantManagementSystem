/**
 * 
 */

  $(document).ready(function() {	
	$("#errType").hide();
	$("#role-table").dataTable();
	$('[data-toggle="tooltip"]').tooltip(); 
	});	
	
	
	$(function () {$('#cancelBtn,#addRoleBtn').click(function() {
			$('#rolelandingpage').attr('action','AddEditRole');
			document.getElementById("rolelandingpage").submit();
});

/* $(function () {$('#addRoleBtn').click(function() {
			$('#rolelandingpage').attr('action','AddEditRole');
			document.getElementById("rolelandingpage").submit();
});*/
});	
	

function urlPostAction(type, action) {
	var roleId = $('#roleId').val();
	var data = "roleId," + roleId;
	postData(action, data);
}

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
function viewRole(roleId) {
		action = '/viewEachRole';
		var data = "roleId," + roleId;
		//alert(data);
		postData(action, data);
}


