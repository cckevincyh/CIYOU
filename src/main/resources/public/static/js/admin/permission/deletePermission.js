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
        dataType:'json',
        data: {
            permissionId :  $("#tree").data("treeview").getSelected()[0].id
        },
        success: function (result) {
            if(result.stateCode == "403"){
                showInfo(result.message);
                window.location.href = "/403";
            }else{
                showInfo(result.message)
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("提交失败，请重试");
        }
    });
}

function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


function showInfo1(msg) {
    $("#div_info1").text(msg);
    $("#modal_info1").modal('show');
}