/**
 * Created by c on 2018/2/15.
 */
$(function () {


    /**
     * 年级下拉框改变时调用
     */
    $("#addGrade").change(function() {
        //获得选中的年级ID
        var $gradeValue = $("#addGrade").val();
        $.ajax({
            type: 'POST',
            url: 'getClassesByGrade',
            cache: false,
            dataType:'json',
            data:{
                gradeId : $gradeValue
            },
            success: function (result) {
                if(result.stateCode == "403"){
                    showInfo(result.message);
                    window.location.href = "/403";
                }else if(result.isSuccess){
                    //清楚班级下拉框之前的项目
                    $("#addClasses option[value!='0']").remove();
                    //设置年级下拉菜单
                    /*
                     * <select id="addClasses" name="addClasses">
                     <option value="">请选择....</option>
                     </select>
                     */
                    var data = result.entity;
                    for(var index in data) {
                        var $option = $("<option></option>");
                        $option.attr("value",data[index].classesId);
                        $option.text(data[index].classes);
                        var $classesElement = $("#addClasses");
                        $classesElement.append($option);
                    }
                }else{
                    showInfo1(result.message)
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("提交失败，请重试");
            }
        });
    });

});

function addStudent() {

    $.ajax({
        type: 'POST',
        url: 'getAllGrade',
        cache: false,
        dataType:'json',
        success: function (result) {
            if(result.stateCode == "403"){
                showInfo(result.message);
                window.location.href = "/403";
            }else{
                //清空之前的
                $("#addGrade option[value!='0']").remove();
                //设置年级下拉菜单
                /*
                 * <select id="addGrade" name="addGrade">
                 <option value="">请选择....</option>
                 </select>
                 */
                var data = result.entity;
                for(var index in data) {
                    var $option = $("<option></option>");
                    $option.attr("value",data[index].gradeId);
                    $option.text(data[index].gradeName);
                    var $gradeElement = $("#addGrade");
                    $gradeElement.append($option);
                }
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
