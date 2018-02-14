
/**
 * ajax提交修改管理员的信息
 * @param {Object} '#updateAdmin'
 */
$(function () {
	

    $('#updateAdmin').click(function () {

    	
   	 if (!validUpdateAdmin()) {
         return;
     }


		$.ajax({
			type: 'POST',
			url: 'updateAdmin',
			cache: false,
			dataType:'json',
			data: {
				adminId : $.trim($("#updateId").val()),
				adminName :$.trim($("#updateUsername").val()),
				name : $.trim($("#updateName").val()),
				phone : $.trim($("#updatePhone").val())
			},
			success: function (data) {
				if(data.stateCode == "403"){
					showInfo(data.message);
					window.location.href = "/403";
				}else if(data.isSuccess){
					$("#updateModal").modal("hide");//关闭模糊框
					showInfo(data.message);
				}else{
					showInfo1(data.message);
				}

			},
			error: function (jqXHR, textStatus, errorThrown) {
				showInfo("提交失败，请重试");
			}
		});
    	
	});
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});







function updateAdmin(id){
	$.ajax({
		type: 'POST',
		url: 'getAdmin',
		cache: false,
		dataType:'json',
		data: {
			adminId: id
		},
		success: function (data) {
			$("#updateId").val(data.entity.adminId);
			$("#updateUsername").val(data.entity.adminName);
			$("#updateName").val(data.entity.name);
			$("#updatePhone").val(data.entity.phone);
		}
	});
			

}


function validUpdateAdmin() {
    var flag = true;

    var username = $.trim($("#updateUsername").val());
    if (username == "") {
        $('#updateUsername').parent().addClass("has-error");
        $('#updateUsername').next().html("<i class='fa fa-times-circle-o'></i>    请输入用户名");
        $("#updateUsername").next().show();
        flag = false;
    } else if (username.length <3 || username.length > 15) {
        $("#updateUsername").parent().addClass("has-error");
        $("#updateUsername").next().html("<i class='fa fa-times-circle-o'></i>    用户名长度必须在3~15之间");
        $("#updateUsername").next().show();
        flag = false;
    } else {
        $('#updateUsername').parent().removeClass("has-error");
        $('#updateUsername').next().html("");
        $("#updateUsername").next().hide();
    }

	
	var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");	
	var name = $.trim($("#updateName").val());
	if(name == ""){
		 $('#updateName').parent().addClass("has-error");
        $('#updateName').next().html("<i class='fa fa-times-circle-o'></i>    请输入真实姓名");
        $("#updateName").next().show();
        flag = false;
	}else if(!reg.test(name)){
		 $('#updateName').parent().addClass("has-error");
        $('#updateName').next().html("<i class='fa fa-times-circle-o'></i>    真实姓名必须为中文");
        $("#updateName").next().show();
		 flag = false;
	}else {
        $('#updateName').parent().removeClass("has-error");
        $('#updateName').next().html("");
        $("#updateName").next().hide();
    }
	
	var phone = $.trim($("#updatePhone").val());
	if(phone == ""){
		 $('#updatePhone').parent().addClass("has-error");
        $('#updatePhone').next().html("<i class='fa fa-times-circle-o'></i>    请输入联系号码");
        $("#updatePhone").next().show();
        flag = false;
	}else if(!(/^1[34578]\d{9}$/.test(phone))){ 
		//电话号码格式的校验
         $('#updatePhone').parent().addClass("has-error");
        $('#updatePhone').next().html("<i class='fa fa-times-circle-o'></i>    手机号码有误");
        $("#updatePhone").next().show();  
        return false; 
    }else {
        $('#updatePhone').parent().removeClass("has-error");
        $('#updatePhone').next().html("");
        $("#updatePhone").next().hide();
    } 

	
    return flag;
}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


function showInfo1(msg) {
	$("#div_info1").text(msg);
	$("#modal_info1").modal('show');
}
