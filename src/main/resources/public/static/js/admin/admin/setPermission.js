/**
 * Created by c on 2018/2/13.
 */
/**
 * 设置Admin权限
 */
$(function () {


    $('#setPermission').click(function () {
        var checkNode = $("#tree").data("treeview").getChecked();
        var permission = "";
        for(var i in checkNode){
            var node = checkNode[i];
            if(node.parentId != undefined){
                //拼接所有的子权限
                permission += node.id + ","
            }
        }
        $.ajax({
            type: 'POST',
            url: 'setAdminPermission',
            cache: false,
            //async: false,
            data: {
                adminId :$.trim($("#pAdminId").val()),
                allPermission: permission
            },
            success: function (result) {
                showInfo(result);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("提交失败，请重试");
            }
        });
    });

});