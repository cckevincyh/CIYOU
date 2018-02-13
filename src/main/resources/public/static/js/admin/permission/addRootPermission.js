/**
 * Created by c on 2018/2/12.
 */
$(function () {
    $('#rootButton').click(function () {
        $("#dict-form-root").bootstrapValidator('validate');//提交验证
        if ($("#dict-form-root").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            //验证成功后的操作，如ajax
            $.ajax({
                type: 'POST',
                url: 'addRootPermission',
                cache: false,
                //async: false,
                data: {
                    permissionName :  $.trim($("#addRootName").val()),
                    permission : $.trim($("#addRootPermission").val()),
                    url : $.trim($("#addRootURL").val())
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

    $('#rootCancel').click(function () {
        $("#dict-form-root").data("bootstrapValidator").resetForm();
    });
});