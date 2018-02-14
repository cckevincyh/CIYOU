/**
 * 点击修改按钮之后ajax提交数据修改密码
 * @param {Object} '#update_adminPwd'
 */
$(function () {
	
	
    $('#update_adminPwd').click(function () {

    	
    	 if (!validUpdateAdminPwd()) {
    	        return;
    	    }


        $.ajax({
            type: 'POST',
            url: 'updatePassword',
            cache: false,
            dataType:'json',
            data: {
                oldPwd: $.trim($("#oldPwd").val()),
                newPwd:$.trim($("#newPwd").val()),
                confirmPwd: $.trim($("#confirmPwd").val())
            },
            success: function (data) {
                if(data.stateCode == "403"){
                    showInfo(data.message);
                    window.location.href = "/403";
                }else if(data.isSuccess){
                    $("#updatepwd").modal("hide");//关闭模糊框
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



function validUpdateAdminPwd() {
    var flag = true;


    var oldPwd = $.trim($("#oldPwd").val());
    if (oldPwd == "") {
        $('#oldPwd').parent().addClass("has-error");
        $('#oldPwd').next().html("<i class='fa fa-times-circle-o'></i>    请输入密码");
        $("#oldPwd").next().show();
        flag = false;
    } else if (oldPwd.length<3 || oldPwd.length > 15) {
        $("#oldPwd").parent().addClass("has-error");
        $("#oldPwd").next().html("<i class='fa fa-times-circle-o'></i>    密码长度必须在3~15之间");
        $("#oldPwd").next().show();
        flag = false;
    } else {
        $('#oldPwd').parent().removeClass("has-error");
        $('#oldPwd').next().html("");
        $("#oldPwd").next().hide();
    }
	
    
    var newPwd = $.trim($("#newPwd").val());
    if (newPwd == "") {
        $('#newPwd').parent().addClass("has-error");
        $('#newPwd').next().html("<i class='fa fa-times-circle-o'></i>    请输入新密码");
        $("#newPwd").next().show();
        flag = false;
    } else if (newPwd.length<3 || newPwd.length > 15) {
        $("#newPwd").parent().addClass("has-error");
        $("#newPwd").next().html("<i class='fa fa-times-circle-o'></i>    新密码长度必须在3~15之间");
        $("#newPwd").next().show();
        flag = false;
    } else {
        $('#newPwd').parent().removeClass("has-error");
        $('#newPwd').next().html("");
        $("#newPwd").next().hide();
    }
    
    
    var confirmPwd = $.trim($("#confirmPwd").val());
    if (confirmPwd == "") {
        $('#confirmPwd').parent().addClass("has-error");
        $('#confirmPwd').next().html("<i class='fa fa-times-circle-o'></i>    请输入密码");
        $("#confirmPwd").next().show();
        flag = false;
    } else if (confirmPwd.length<3 || confirmPwd.length > 15) {
        $("#confirmPwd").parent().addClass("has-error");
        $("#confirmPwd").next().html("<i class='fa fa-times-circle-o'></i>    密码长度必须在3~15之间");
        $("#confirmPwd").next().show();
        flag = false;
    }else if (confirmPwd!=newPwd) {
        $("#confirmPwd").parent().addClass("has-error");
        $("#confirmPwd").next().html("<i class='fa fa-times-circle-o'></i>    确认密码不一致");
        $("#confirmPwd").next().show();
        flag = false;
    } else {
        $('#confirmPwd').parent().removeClass("has-error");
        $('#confirmPwd').next().html("");
        $("#confirmPwd").next().hide();
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

