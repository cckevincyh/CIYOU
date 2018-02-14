/**
 * Created by c on 2018/2/13.
 */
$(function () {
    $('#editButton').click(function () {
        if($("#tree").data("treeview").getSelected()[0] == undefined) {
            showInfo1("请选择权限");
            return ;
        }
        $("#dict-form-edit").bootstrapValidator('validate');//提交验证
        if ($("#dict-form-edit").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            //验证成功后的操作，如ajax
            $.ajax({
                type: 'POST',
                url: 'updatePermission',
                cache: false,
                //async: false,
                dataType:'json',
                data: {
                    permissionId:$.trim($("#editId").val()),
                    permissionName :  $.trim($("#editName").val()),
                    permission : $.trim($("#editPermission").val()),
                    url : $.trim($("#editURL").val())
                },
                success: function (result) {
                    if(result.stateCode == "403"){
                        showInfo(result.message);
                        window.location.href = "/403";
                    }else if(result.isSuccess){
                        showInfo(result.message)
                    }else{
                        showInfo1(result.message)
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    showInfo("提交失败，请重试");
                }
            });
        }
    });

    $('#editCancel').click(function () {
        $("#dict-form-edit").data("bootstrapValidator").resetForm();
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