$(document).ready(function(){
	$("#errType").hide();
		$("form :input").change(function() {
			$(this).closest('form').data('changed', true);
			});	
});

$(function () {  
             $("#resetBtn").click(function (event) {
				$("#addrole")[0].reset();
				$("select").prop('value','');
				$("#availableFunList").empty();
				$('#assignFunList').empty();			
				});
 		 		  

 $(function () {$('#cancelBtn').click(function() {
			$('#addrole').attr('action','viewRole');
			document.getElementById("addrole").submit();
});
});
 });
$(function () {  
$('#moduleId,#roleType').click(function(event){ 

		var roleType = $("#roleType").val();  
	 	var moduleId = $(this).val();  
	  		if(moduleId){
	    		$.ajax({
			      type:"GET",
			      url:"getFunction",
			      data:{roleType:roleType,
						moduleId:moduleId
						},
			      success:function(data){  
			      if(data){
	       				$("#availableFunList").empty();
	        			$.each(data,function(index, option) {
					  $("#availableFunList").append('<option value="'+ option.funcId+ '">'+ option.funcName+'</option>');
	       			  });
	      
	      		}else{
	        		$("#availableFunList").empty();
	      			}
	      		}
	    		});
	    		}else{
	        		$("#availableFunList").empty();
	      			}   
	      });
	     }); 


$(function () {  
            $("#ShiftRight,#ShiftLeft").click(function (event) {  
                var ID = $(event.target).attr("ID");  
                var ChooseFrom = ID == "ShiftRight" ? "#availableFunList" : "#assignFunList";  
                var moveTo = ID == "ShiftRight" ? "#assignFunList" : "#availableFunList";  
                var SelectData = $(ChooseFrom + " :selected").toArray();  
                $(moveTo).append(SelectData);  
                SelectData.remove;  
				$("#assignFunList option").each(function(){
					$(this).siblings("[value="+this.value+"]").remove();
	});
});
});
$(function () {$('#roleType').change(function(){
				$("#moduleId").prop('value',0);
				$("#availableFunList").empty();
				$('#assignFunList').empty();
});
});




function validateAddEditRole(id) {

	$('.jqueryError').text("");
	$('.jqueryError').hide();
	var check = false;

	if (!validateRoleName('errRoleName')) {
		check = true;
	}
	
	if (!validateRoleDesc('errRoleDesc')) {
		check = true;
	}
	
	if (!validateSessionTime('errSessionTime')) {
		check = true;
	}
	
	if (!validateInstId('errInstId')) {
		check = true;
	}
	
	if (!validateModuleId('errModuleId')) {
		check = true;
	}
	if (!validateFunctionList('errAssignFunList')) {
		check = true;
	}
	
	if (!check) {
		if($('#addrole').data('changed')) {
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
	document.body.appendChild(form); // added this	for firefox Browser
	//encodeForm(form);	//Added by piyush for form encode
	
	form.action = linkurl;
	form.submit();
}
function addEdit(status) {
	//alert(status);
	var loc = window.location;
	var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
	if (status== 'A') {
		url = '/addRole';
	} else if (status == 'E') {
		url = '/editRole';
	}
	var userId = document.getElementById("userId").value;		
	var roleName = document.getElementById("roleName").value;
	var roleDesc = document.getElementById("roleDesc").value;
	var sessionTime = document.getElementById("sessionTime").value;
	var instId = document.getElementById("instId").value;
	var roleType= document.getElementById("roleType").value;
	var moduleId=document.getElementById("moduleId").value;
	var assignFunList=$("#assignFunList").val();
	var roleList="";
	for ( var i = 0; i < assignFunList.length; ++i) {
			 roleList=roleList+assignFunList[i]+"#";
						
		}
	var data = "status,"+status+",RoleId,"+$("#roleId").val()+",RoleName," + roleName  +",RoleDesc," + roleDesc 
				 +",SessionTime," + sessionTime +",InstId," + instId 
				 +",assignFunList,"+roleList +",roleType,"+roleType+",moduleId,"+moduleId+",userId,"+userId;
		//alert(data)	;
	postData(url, data);
}


function validateRoleName(msgID) {

	var roleName = (document.getElementById("roleName").value).replace(
			/^\s*|\s*$/g, '');
	var errRoleName = document.getElementById(msgID);
	var regEx = /^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/;
	var minLn = 5;
	var maxLn = 20;

	if (roleName == "") {

		errRoleName.className = 'error';
		errRoleName.innerHTML = "Field cannot be blank";
		return false;

	}
	 else if (!regEx.test(roleName)) {
		var regEx = /^[a-zA-Z]+$/i;

		if (!regEx.test(roleName)) {
		errRoleName.className = 'error';
		errRoleName.innerHTML = "Special Characters are not allowed";
		return false;

	}
	}
	if (roleName.length > maxLn) {
		flag = false;
		errLoginId.className = 'error';
		errLoginId.innerHTML = "RoleName should be maximum 50 characters";
		return flag;

	}
	if (roleName.length < minLn) {
		flag = false;
		errLoginId.className = 'error';
		errLoginId.innerHTML = "RoleName should be minimum 5 characters";
		return flag;

	} 
	 else {

		errRoleName.className = 'error';
		errRoleName.innerHTML = "";

	}

	return true;
}

function validateRoleDesc(msgID) {

	var roleDesc = (document.getElementById("roleDesc").value).replace(
			/^\s*|\s*$/g, '');
	var errRoleDesc = document.getElementById(msgID);
	var regEx = /^[a-zA-Z ]+$/i;

	if (roleDesc == "") {

		errRoleDesc.className = 'error';
		errRoleDesc.innerHTML = "Field cannot be blank";
		return false;

	} else if (!regEx.test(roleDesc)) {

		errRoleDesc.className = 'error';
		errRoleDesc.innerHTML = "Special Characters are not allowed";
		return false;

	} else {

		errRoleDesc.className = 'error';
		errRoleDesc.innerHTML = "";

	}

	return true;
}


function validateSessionTime(msgID) {
	var sessionTime = (document.getElementById("sessionTime").value).replace(/^\s*|\s*$/g, '');
	var errSessionTime = document.getElementById(msgID);
	var regEx = /^([0-9][0-9]{1})$/i;

	if (sessionTime == 0) {

		errSessionTime.className = 'error';
		errSessionTime.innerHTML = "Field cannot be blank";

		return false;

	} else if (!regEx.test(sessionTime)) {

		errSessionTime.className = 'error';
		errSessionTime.innerHTML = "Please Enter valid number";
		return false;

	} else {

		errSessionTime.className = 'error';
		errSessionTime.innerHTML = "";
	}

	return true;
}


function validateInstId(msgID) {

	var instId = (document.getElementById("instId").value).replace(/^\s*|\s*$/g, '');
	var errInstId = document.getElementById(msgID);
	var regEx = /^[0-9]*$/i;

	if (instId == 0) {

		errInstId.className = 'error';
		errInstId.innerHTML = "Select a valid Institution";

		return false;

	} else if (!regEx.test(instId)) {

		errInstId.className = 'error';
		errInstId.innerHTML = "Select a Institution";
		return false;

	} else {

		errInstId.className = 'error';
		errInstId.innerHTML = "";
	}

	return true;
}


function validateModuleId(msgID) {

	var moduleId = (document.getElementById("moduleId").value).replace(/^\s*|\s*$/g, '');
	var errModuleId = document.getElementById(msgID);
	var regEx = /^[0-9]*$/i;

	if (moduleId == 0) {

		errModuleId.className = 'error';
		errModuleId.innerHTML = "Select a valid Module";

		return false;

	} else if (!regEx.test(moduleId)) {

		errModuleId.className = 'error';
		errModuleId.innerHTML = "Select a Module from list";
		return false;

	} else {

		errModuleId.className = 'error';
		errModuleId.innerHTML = "";
	}

	return true;
}


function validateFunctionList(msgID) {

	var assignFunList = (document.getElementById("assignFunList").value).replace(/^\s*|\s*$/g, '');
	var errAssignFunList = document.getElementById(msgID);
	var regEx = /^[0-9]*$/i;

	if (assignFunList == 0) {

		errAssignFunList.className = 'error';
		errAssignFunList.innerHTML = "This field is mandatory";

		return false;

	} else if (!regEx.test(assignFunList)) {

		errAssignFunList.className = 'error';
		errAssignFunList.innerHTML = "Select the Functions";
		return false;

	} else {

		errAssignFunList.className = 'error';
		errAssignFunList.innerHTML = "";
	}

	return true;
}

