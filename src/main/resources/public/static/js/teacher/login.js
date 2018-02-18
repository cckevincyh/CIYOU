$(function () {
	
	
    $('#login_submit').click(function () {
        if (!validLogin()) {
            return;
        }
		
        $.ajax({
            type: 'POST',
            url: 'teacherLogin',
            cache: false,
            dataType:'json',
            data: {
                teacherId: $.trim($("#teacherId").val()),
                password:$.trim($("#password").val())

            },
            success: function (data) {
                $("#modal_info").modal("hide");//关闭模糊框
                if(data.isSuccess){
                    window.location.href = "/teacher/index";
                }else{
                    showInfo(data.message);
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("提交失败，请重试");
            }
        });
	});
	

});

function validLogin() {
    var flag = true;

    var teacherId = $.trim($("#teacherId").val());
    if (teacherId == "") {
        $('#teacherId').parent().addClass("has-error");
        $('#teacherId').next().next().html("<i class='fa fa-times-circle-o'></i>    请输入账号");
        $("#teacherId").next().next().show();
        flag = false;
    } else {
        $('#teacherId').parent().removeClass("has-error");
        $('#teacherId').next().next().html("");
        $("#teacherId").next().next().hide();
    }

    var password = $.trim($("#password").val());
    if (password == "") {
        $('#password').parent().addClass("has-error");
        $('#password').next().next().html("<i class='fa fa-times-circle-o'></i>    请输入密码");
        $("#password").next().next().show();
        flag = false;
    } else if (password.length<3 || password.length > 15) {
        $("#password").parent().addClass("has-error");
        $("#password").next().next().html("<i class='fa fa-times-circle-o'></i>    密码长度必须在3~15之间");
        $("#password").next().next().show();
        flag = false;
    } else {
        $('#password').parent().removeClass("has-error");
        $('#password').next().next().html("");
        $("#password").next().next().hide();
    }
    return flag;
}

function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}