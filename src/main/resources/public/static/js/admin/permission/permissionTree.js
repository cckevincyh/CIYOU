/**
 * Created by c on 2018/2/11.
 */
$(function () {

    $.ajax({
        type: "POST",
        url: "getAllPermission",
        dataType: "json",
        //async: false,
        success: function (result) {
            $('#tree').treeview({
                data: result,         // 数据源
                //showCheckbox: true,   //是否显示复选框
                //highlightSelected: true,    //是否高亮选中
                //nodeIcon: 'glyphicon glyphicon-user',    //节点上的图标
                //nodeIcon: 'glyphicon glyphicon-globe',
                //emptyIcon: '',    //没有子节点的节点图标
                //multiSelect: false,    //多选
                onNodeChecked: function (event,data) {

                },
                onNodeSelected: function (event, data) {
                    //获取当前Tab标签所选
                    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
                        ajaxLoad(e,data);
                    });

                }
            });
            //通过这两段代码才可以分别触发到事件，silent要设置为false，Tab标签页面不要设置active
            //设置选中
            $('#tree').treeview('selectNode', [0, { silent: false }]);
            // 通过名称选取标签页
            $('#myTab a[href="#view"]').tab('show');
        },
        error: function () {
            showInfo1("树形权限结构加载失败！")
        }
    });

    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
        //获取当前选中的结点，getSelected出来的可能是多个，所以我们选第一个
        //alert($('#tree').treeview('getSelected')[0].text);
        //下面这种方式也可以获取到选中的结点
        //alert($("#tree").data("treeview").getSelected()[0].text);
        $('#tree').on('nodeSelected',function(event, data) {
            ajaxLoad(e,data);
        });
    });

});

/**
 *  异步加载权限的公用方法
 * @param e 标签页触发的事件
 * @param data TreeView选中结点
 */
function ajaxLoad(e,data){
    //得到当前激活的Tab标签页
    var tartgetStr = e.target.toString();
    //如果尾部以#view结尾的
    if(tartgetStr.lastIndexOf("#view") != -1){
        // showInfo1(data.parentId);
        //这种方式也可以获取到父结点，但是当没有父结点的时候会有问题，最好用data.parentId在判断一下
        // if(data.parentId != undefined){
        //     alert( $("#tree").data("treeview").getParent(data).text);
        // }
        //异步查询权限，显示在权限详情tab页面
        $.ajax({
            type: 'POST',
            url: 'getPermission',
            cache: false,
            //async: false,
            dataType:'json',
            data: {
                permissionId: data.id
            },
            success: function (result) {
                if(""+data.parentId != "undefined"){
                    $("#viewParentId").val($('#tree').treeview('getNode', ""+data.parentId).text);
                }else{
                    $("#viewParentId").val("系统权限");
                }
                $("#viewName").val(result.permissionName);
                $("#viewPermission").val(result.permission);
                $("#viewURL").val(result.url);
            }
        });
    }
    //如果尾部以#edit结尾的
    if(tartgetStr.lastIndexOf("#edit") != -1){
        //showInfo1("edit")
    }
    //如果尾部以#add结尾的
    if(tartgetStr.lastIndexOf("#add") != -1){
        //showInfo1("add")
    }
}


function showInfo1(msg) {
    $("#div_info1").text(msg);
    $("#modal_info1").modal('show');
}
