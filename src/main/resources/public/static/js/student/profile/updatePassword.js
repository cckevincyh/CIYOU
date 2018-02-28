/**
 * Created by c on 2018/2/19.
 */
$(function () {
    $('#passwordButton').click(function () {
        $("#dict-form-password").bootstrapValidator('validate');//提交验证
        if ($("#dict-form-password").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            $.ajax({
                type: 'POST',
                url: 'updatePassword',
                cache: false,
                dataType:'json',
                data: {
                    oldPwd: $.trim($("#oldPwd").val()),
                    newPwd: $.trim($("#newPwd").val()),
                    confirmPwd:$.trim($("#confirmPwd").val())
                },
                success: function (data) {
                    if(data.stateCode == "403"){
                        showInfo(data.message);
                        window.location.href = "/403";
                    }else if(data.isSuccess){
                        showInfo(data.message);
                    }else{
                        showInfo1(data.message);
                    }
                }
            });
        }
    });

    $('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
        location.reload();  	//刷新当前页面
    });
});


function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


function showInfo1(msg) {
    $("#div_info1").text(msg);
    $("#modal_info1").modal('show');
}
