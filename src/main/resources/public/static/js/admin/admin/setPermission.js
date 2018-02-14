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
            //设置的权限
            permission += node.id + ","
        }
        $.ajax({
            type: 'POST',
            url: 'setAdminPermission',
            cache: false,
            //async: false,
            dataType: "json",
            data: {
                adminId :$.trim($("#pAdminId").val()),
                allPermission: permission
            },
            success: function (result) {
                if(result.stateCode == "403"){
                    showInfo(result.message);
                    window.location.href = "/403";
                }else {
                    showInfo(result.message);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("提交失败，请重试");
            }
        });
    });

});


function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}
