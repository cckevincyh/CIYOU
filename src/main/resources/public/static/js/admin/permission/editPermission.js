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
                data: {
                    permissionId:$.trim($("#editId").val()),
                    permissionName :  $.trim($("#editName").val()),
                    permission : $.trim($("#editPermission").val()),
                    url : $.trim($("#editURL").val())
                },
                success: function (result) {
                    showInfo(result)
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