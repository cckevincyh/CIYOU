/**
 * Created by c on 2018/2/13.
 */
function deletePermission(){
    if($("#tree").data("treeview").getSelected()[0] == undefined) {
        showInfo1("请选择权限");
        return ;
    }
    $.ajax({
        type: 'POST',
        url: 'deletePermission',
        cache: false,
        //async: false,
        data: {
            permissionId :  $("#tree").data("treeview").getSelected()[0].id
        },
        success: function (result) {
            showInfo(result)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("提交失败，请重试");
        }
    });
}