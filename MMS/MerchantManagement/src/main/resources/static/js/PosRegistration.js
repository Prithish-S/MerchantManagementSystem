/**
 * 
 */
 $(document).ready(function(){
	$("#errType").hide();
		$("form :input").change(function() {
			$(this).closest('form').data('changed', true);
			});	
  $('#cancelBtn').click(function() {
			$('#addPos').attr('action','addEditMerchant');
			document.getElementById("addPos").submit();
});
});

function validateAddEditPos(id) {
	$('.jqueryError').text("");
	$('.jqueryError').hide();
		var check = false;

	if (!validatePos_machine_id('errPos_machine_id')) {
		check = true;
	}	

	if (!check) {
		if(!$('#addpos').data('changed')) {
			addEdit(id);
		}else{
			$('#jqueryError').text('no data changed');
			$('#jqueryError').show();
			return false;
		}

	} else {
		return false;
	}

}


function addEdit(status) {
	//alert(status);
	if (status== 'A') {
		url = '/addPos';
	} else if (status == 'E') {
		url = '/editPos';
	}	
	var userId = document.getElementById("userId").value;		
	var posId = document.getElementById("pos_machine_id").value;
	var merId = document.getElementById("merchant_id").value;
	var machineModel = document.getElementById("machine_model").value;
	var serNum = document.getElementById("serial_number").value;
	var installDate = document.getElementById("install_date").value;
	var status= document.getElementById("status").value;
	var ipAdd=document.getElementById("ip_address").value;
	var location=document.getElementById("location").value;
	var lastMainDate=document.getElementById("last_maintenance_date").value;
	var createDate=document.getElementById("created_at").value;
	var updateDate=document.getElementById("updated_at").value;


	var data = "userId,"+userId+",merchant_id,"+merId+",pos_machine_id," + posId  +",machine_model,"+machineModel+",serial_number," + serNum 
				+ ",install_date," + installDate +",status," +status+",ip_address,"+ipAdd +",location,"+location+",last_maintenance_date,"+lastMainDate
				 +",created_at,"+createDate+",updated_at,"+updateDate;
				 
	//alert(data)	;
	postData(url, data);
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
function validatePos_machine_id(msgID) {
	var posId = (document.getElementById("pos_machine_id").value).replace(/^\s*|\s*$/g, '');
	var errPosId = document.getElementById(msgID);
	var regEx = /^[a-zA-Z0-9 ]+$/i;

	if (posId == "") {

		errPosId.className = 'error';
		errPosId.innerHTML = "Field cannot be blank";

		return false;

	} else if (!regEx.test(posId)) {

		errPosId.className = 'error';
		errPosId.innerHTML = "Please Enter valid number";
		return false;

	} else {

		errPosId.className = 'error';
		errPosId.innerHTML = "";
	}

	return true;
}
