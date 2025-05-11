/**
 * 
 */
$(document).ready(function() {	
		$('#addUserBtn').click(function() {
			$('#adduser').attr('action','addUser');
			document.getElementById("adduser").submit();
	});		
});
	$(function () {$('#roleType').change(function(){
	
	  var roleType = document.getElementById("roleType").value;
	  //alert(roleType);
		if(roleType){
  		$.ajax({
		      type:"GET",
		      url:"geMappedRoles",
		      data: {
					roleType:roleType
					},
		      success:function(data){   
		      if(data){
     			$("#roleList").empty();
     			  $.each(data,function(index, option) {
				  $("#roleList").append('<option value="'+ option.roleId+ '">'+ option.roleName+'</option>');
				 $("#assRoleList").empty();
     			  });
    
    		}else{
      		$("#roleList").empty();
      		$("#assRoleList").empty();
    			}
    		}
  		});
  		}else{
  		 $("#roleList").empty();
      	 $("#assRoleList").empty();
			 }   
    });
});
	
$(function () {$('#country').change(function(){
  		var countryId = $("#country").val();  
  		  if(countryId){
    			$.ajax({
			      type:"GET",
			      url:"getState",
			      data: {
						countryid: countryId
						},
			      success:function(data){        
			      if(data){
			      $("#state").empty();
			      $("#state").html('<option>select</option>');
			      $("#city").html('<option>select State</option>');
			      $.each(data, function(index, option) {
				  $("#state").append('<option value="'+ option.stateid+ '">'+ option.statename+'</option>');
       			  });
      		      }else{
        			$("#state").empty();
     			   }
     			 }
   			 });
  		}else{
   			 $("#state").empty();
    		 $("#city").empty();
 			 }   
 	 });
  
});

 $(function () {$('#state').change(function(){ 
	var countryId = $("#country").val();  
 	var stateId = $("#state").val();  
  		if(stateId){
    		$.ajax({
		      type:"GET",
		      url:"getCity",
		      data: {
					countryid:countryId,
					stateid: stateId
					},
		      success:function(data){        
		      if(data){
       				$("#city").empty();
       				$("#city").html('<option value="0">--Select--</option>');
        			$.each(data,function(index, option) {
				  $("#city").append('<option value="'+ option.cityid+ '">'+ option.cityname+'</option>');
       			  });
      
      		}else{
        		$("#city").empty();
      			}
      		}
    		});
    		}else{
    		 $("#city").empty();
 			 }
      });
      });
 
 $(function () {  
            $("#ShiftRight,#ShiftLeft").click(function (event) {  
                var ID = $(event.target).attr("ID");  
                var ChooseFrom = ID == "ShiftRight" ? "#roleList" : "#assRoleList";  
                var moveTo = ID == "ShiftRight" ? "#assRoleList" : "#roleList";  
                var SelectData = $(ChooseFrom + " :selected").toArray();  
                $(moveTo).append(SelectData);  
                SelectData.remove;  
            });
 });
 