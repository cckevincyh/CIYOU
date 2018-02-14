/**
 * Created by c on 2018/2/13.
 */
$(function () {
    $('#addButton').click(function () {
        if($("#tree").data("treeview").getSelected()[0] == undefined) {
            showInfo1("请选择权限");
            return ;
        }
        //首先校验是否是根权限下添加子权限，获取当前的根权限是否为空，为空则说明是子权限，不能添加
        if($.trim($("#addPid").val()) == ""){
            showInfo1("不能对子权限进行添加，请选择根权限");
            return ;
        }
        $("#dict-form-add").bootstrapValidator('validate');//提交验证
        if ($("#dict-form-add").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            //验证成功后的操作，如ajax
            $.ajax({
                type: 'POST',
                url: 'addPermission',
                cache: false,
                //async: false,
                dataType:'json',
                data: {
                    parentId :$.trim($("#addPid").val()),
                    permissionName :  $.trim($("#addName").val()),
                    permission : $.trim($("#addPermission").val()),
                    url : $.trim($("#addURL").val())
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

    $('#addCancel').click(function () {
        $("#dict-form-add").data("bootstrapValidator").resetForm();
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