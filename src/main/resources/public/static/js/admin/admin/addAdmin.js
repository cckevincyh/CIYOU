
/**
 * ajax提交添加管理员的信息
 * @param {Object} '#addAdmin'
 */
$(function () {
	
	
    $('#addAdmin').click(function () {

         if (!validAddAdmin()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'addAdmin',
            cache: false,
            dataType:'json',
            data: {
                adminName: $.trim($("#addUsername").val()),
                name:$.trim($("#addName").val()),
                phone: $.trim($("#addPhone").val())
            },
            success: function (data) {
                if(data.stateCode == "403"){
                    showInfo(data.message);
                    window.location.href = "/403";
                }else if(data.isSuccess){
                    $("#addModal").modal("hide");//关闭模糊框
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



function validAddAdmin() {
    var flag = true;

    var username = $.trim($("#addUsername").val());
    if (username == "") {
        $('#addUsername').parent().addClass("has-error");
        $('#addUsername').next().html("<i class='fa fa-times-circle-o'></i>    请输入用户名");
        $("#addUsername").next().show();
        flag = false;
    } else if (username.length < 3 || username.length > 15) {
        $("#addUsername").parent().addClass("has-error");
        $("#addUsername").next().html("<i class='fa fa-times-circle-o'></i>    用户名长度必须在3~15之间");
        $("#addUsername").next().show();
        flag = false;
    } else {
        $('#addUsername').parent().removeClass("has-error");
        $('#addUsername').next().text("");
        $("#addUsername").next().hide();
    }

	
	
	var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");	
	var name = $.trim($("#addName").val());
	if(name == ""){
		 $('#addName').parent().addClass("has-error");
        $('#addName').next().html("<i class='fa fa-times-circle-o'></i>    请输入真实姓名");
        $("#addName").next().show();
        flag = false;
	}else if(!reg.test(name)){
		 $('#addName').parent().addClass("has-error");
        $('#addName').next().html("<i class='fa fa-times-circle-o'></i>    真实姓名必须为中文");
        $("#addName").next().show();
		 flag = false;
	}else {
        $('#addName').parent().removeClass("has-error");
        $('#addName').next().html("");
        $("#addName").next().hide();
    }
	
	var phone = $.trim($("#addPhone").val());
	if(phone == ""){
		 $('#addPhone').parent().addClass("has-error");
        $('#addPhone').next().html("<i class='fa fa-times-circle-o'></i>    请输入联系号码");
        $("#addPhone").next().show();
        flag = false;
	}else if(!(/^1[34578]\d{9}$/.test(phone))){ 
		//电话号码格式的校验
         $('#addPhone').parent().addClass("has-error");
        $('#addPhone').next().html("<i class='fa fa-times-circle-o'></i>    手机号码有误");
        $("#addPhone").next().show();  
        return false; 
    }else {
        $('#addPhone').parent().removeClass("has-error");
        $('#addPhone').next().html("");
        $("#addPhone").next().hide();
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


