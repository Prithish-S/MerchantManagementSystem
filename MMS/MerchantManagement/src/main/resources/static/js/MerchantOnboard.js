/**
 * 
 */

 $(document).ready(function(){
	$("#errType").hide();
		$("form :input").change(function() {
			$(this).closest('form').data('changed', true);
			});	

        $('#cancelBtn').click(function() {
			$('#addmerchant').attr('action','addEditMerchant');
			document.getElementById("addmerchant").submit();
});


});
function validateAddEditMerchant(id) {
	$('.jqueryError').text("");
	$('.jqueryError').hide();
		var check = false;

	
	if (!validateMerchant_id('errMerchant_id')) {
		check = true;
	}	
	if (!validateCity('errCity')) {
		check = true;
	}

	if (!check) {
		if($('#addmerchant').data('changed')) {
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
		url = '/addMerchant';
	} else if (status == 'E') {
		url = '/editMerchant';
	}	
	var userId = document.getElementById("userId").value;		
	var merId = document.getElementById("merchant_id").value;
	var busName = document.getElementById("business_name").value;
	var busAddress = document.getElementById("business_address").value;
	var country = document.getElementById("country").value;
	var state = document.getElementById("state").value;
	var city= document.getElementById("city").value;
	var conNumber=document.getElementById("business_phone").value;
	var zipCode=document.getElementById("zip_code").value;
	var status=document.getElementById("merchant_status").value;
	var webUrl=document.getElementById("website_url").value;
	var ownerName=document.getElementById("owner_name").value;
	var ownerEmail=document.getElementById("owner_email").value;
	var busType=document.getElementById("business_type").value;
	var regNumber=document.getElementById("registration_number").value;
	var taxId=document.getElementById("tax_id").value;
	var CreateDate=document.getElementById("created_at").value;
	var UpdateDate=document.getElementById("updated_at").value;
	var busEmail=document.getElementById("business_email").value;

	var data = "userId,"+userId+",merchant_id,"+merId+",business_name," + busName  +",business_address,"+busAddress+",country," + country 
				+ ",state," + state +",city," +city+",business_phone,"+conNumber +",zip_code,"+zipCode+",merchant_status,"+status
				 +",website_url,"+webUrl+",owner_name,"+ownerName+",owner_email,"+ownerEmail+",business_type,"+busType+",registration_number,"+regNumber
				 +",tax_id,"+taxId+",created_at,"+CreateDate+",updated_at,"+UpdateDate+",business_email,"+busEmail;
				 
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
function validateMerchant_id(msgID) {
	var merchant_id = (document.getElementById("merchant_id").value).replace(/^\s*|\s*$/g, '');
	var errMerchant_id = document.getElementById(msgID);
	var regEx = /^[a-zA-Z0-9 ]+$/i;

	if (merchant_id == "") {

		errMerchant_id.className = 'error';
		errMerchant_id.innerHTML = "Field cannot be blank";

		return false;

	} else if (!regEx.test(merchant_id)) {

		errMerchant_id.className = 'error';
		errMerchant_id.innerHTML = "Please Enter valid number";
		return false;

	} else {

		errMerchant_id.className = 'error';
		errMerchant_id.innerHTML = "";
	}

	return true;
}

function validateCity(msgID) {

	var city = (document.getElementById("city").value).replace(/^\s*|\s*$/g, '');
	var errCity = document.getElementById(msgID);
	var regEx = /^[0-9]*$/i;

	if (city == 0) {

		errCity.className = 'error';
		errCity.innerHTML = "Select a valid Module";

		return false;

	} else if (!regEx.test(city)) {

		errCity.className = 'error';
		errCity.innerHTML = "Select a Module from list";
		return false;

	} else {

		errCity.className = 'error';
		errCity.innerHTML = "";
	}

	return true;
}

