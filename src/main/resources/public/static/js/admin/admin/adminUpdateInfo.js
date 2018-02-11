/**
 *管理员点击个人资料修改按钮触发进行ajax异步请求
 * @param {Object} '#admin_updateInfo'
 */

$(function () {
	
	
    $('#admin_updateInfo').click(function () {

    	
    	 if (!validUpdateAdminInfo()) {
    	        return;
    	    }

        $.ajax({
            type: 'POST',
            url: 'updateInfo',
            cache: false,
            data: {
                adminId:$.trim($("#adminId").val()),
                adminName: $.trim($("#username").val()),
                name:$.trim($("#name").val()),
                phone: $.trim($("#phone").val())
            },
            success: function (data) {
                $("#updateinfo").modal("hide");//关闭模糊框
                showInfo(data);

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


function validUpdateAdminInfo() {
    var flag = true;

    var username = $.trim($("#username").val());
    if (username == "") {
        $('#username').parent().addClass("has-error");
        $('#username').next().html("<i class='fa fa-times-circle-o'></i>    请输入用户名");
        $("#username").next().show();
        flag = false;
    } else if (username.length < 3 || username.length > 15) {
        $("#username").parent().addClass("has-error");
        $("#username").next().html("<i class='fa fa-times-circle-o'></i>    用户名长度必须在3~15之间");
        $("#username").next().show();
        flag = false;
    } else {
        $('#username').parent().removeClass("has-error");
        $('#username').next().html("");
        $("#username").next().hide();
    }

	
	
	var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");	
	var name = $.trim($("#name").val());
	if(name == ""){
		 $('#name').parent().addClass("has-error");
        $('#name').next().html("<i class='fa fa-times-circle-o'></i>    请输入真实姓名");
        $("#name").next().show();
        flag = false;
	}else if(!reg.test(name)){
		 $('#name').parent().addClass("has-error");
        $('#name').next().html("<i class='fa fa-times-circle-o'></i>    真实姓名必须为中文");
        $("#name").next().show();
		 flag = false;
	}else {
        $('#name').parent().removeClass("has-error");
        $('#name').next().html("");
        $("#name").next().hide();
    }
	
	var phone = $.trim($("#phone").val());
	if(phone == ""){
		 $('#phone').parent().addClass("has-error");
        $('#phone').next().html("<i class='fa fa-times-circle-o'></i>    请输入联系号码");
        $("#phone").next().show();
        flag = false;
	}else if(!(/^1[34578]\d{9}$/.test(phone))){ 
		//电话号码格式的校验
         $('#phone').parent().addClass("has-error");
        $('#phone').next().html("<i class='fa fa-times-circle-o'></i>    手机号码有误");
        $("#phone").next().show();  
        return false; 
    }else {
        $('#phone').parent().removeClass("has-error");
        $('#phone').next().html("");
        $("#phone").next().hide();
    } 

    return flag;
}


function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}