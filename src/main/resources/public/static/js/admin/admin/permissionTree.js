/**
 * bootstrap-Treeview实现级联勾选
 * Created by c on 2018/2/11.
 */
var nodeCheckedSilent = false;
function nodeChecked (event, node){
    if(nodeCheckedSilent){
        return;
    }
    nodeCheckedSilent = true;
    checkAllParent(node);
    checkAllSon(node);
    nodeCheckedSilent = false;
}

var nodeUncheckedSilent = false;
function nodeUnchecked  (event, node){
    if(nodeUncheckedSilent)
        return;
    nodeUncheckedSilent = true;
    uncheckAllParent(node);
    uncheckAllSon(node);
    nodeUncheckedSilent = false;
}

//选中全部父节点
function checkAllParent(node){
    $('#tree').treeview('checkNode',node.nodeId,{silent:true});
    var parentNode = $('#tree').treeview('getParent',node.nodeId);
    if(!("nodeId" in parentNode)){
        return;
    }else{
        checkAllParent(parentNode);
    }
}
//取消全部父节点
function uncheckAllParent(node){
    $('#tree').treeview('uncheckNode',node.nodeId,{silent:true});
    var siblings = $('#tree').treeview('getSiblings', node.nodeId);
    var parentNode = $('#tree').treeview('getParent',node.nodeId);
    if(!("nodeId" in parentNode)) {
        return;
    }
    var isAllUnchecked = true;  //是否全部没选中
    for(var i in siblings){
        if(siblings[i].state.checked){
            isAllUnchecked=false;
            break;
        }
    }
    if(isAllUnchecked){
        uncheckAllParent(parentNode);
    }

}

//级联选中所有子节点
function checkAllSon(node){
    $('#tree').treeview('checkNode',node.nodeId,{silent:true});
    if(node.nodes!=null&&node.nodes.length>0){
        for(var i in node.nodes){
            checkAllSon(node.nodes[i]);
        }
    }
}
//级联取消所有子节点
function uncheckAllSon(node){
    $('#tree').treeview('uncheckNode',node.nodeId,{silent:true});
    if(node.nodes!=null&&node.nodes.length>0){
        for(var i in node.nodes){
            uncheckAllSon(node.nodes[i]);
        }
    }
}


function updatePermission(adminId) {
    $("#pAdminId").val(adminId);
    $.ajax({
        type: "POST",
        url: "getAdminPermission",
        dataType: "json",
        //async: false,
        data: {
            adminId : adminId
        },
        success: function (result) {
            if(result.stateCode == "403"){
                showInfo1(result.message);
                window.location.href = "/403";
            }else{
                $('#tree').treeview({
                    data: result.entity,         // 数据源
                    showCheckbox: true,   //是否显示复选框
                    highlightSelected: true,    //是否高亮选中
                    //nodeIcon: 'glyphicon glyphicon-user',    //节点上的图标
                    //nodeIcon: 'glyphicon glyphicon-globe',
                    //emptyIcon: '',    //没有子节点的节点图标
                    //multiSelect: false,    //多选
                    onNodeChecked:nodeChecked ,
                    onNodeUnchecked:nodeUnchecked
                });
            }
        },
        error: function () {
            showInfo1("树形权限结构加载失败！")
        }
    });
}



function showInfo1(msg) {
    $("#div_info1").text(msg);
    $("#modal_info1").modal('show');
}

