/**
 * Created by c on 2018/2/24.
 */
$(function () {
    /**
     * 获取所有的年级
     */
    $.ajax({
        type: 'POST',
        url: 'getAllGrade',
        cache: false,
        dataType: 'json',
        success: function (result) {
            //清空之前的
            $("#addGrade option[value!='0']").remove();
            //设置年级下拉菜单
            var data = result.entity;
            for (var index in data) {
                var $option = $("<option></option>");
                $option.attr("value", data[index].gradeId);
                $option.text(data[index].gradeName);
                var $gradeElement = $("#addGrade");
                $gradeElement.append($option);
            }

        }
    });


    /**
     * 年级下拉框改变时调用
     */
    $("#addGrade").change(function () {
        //获得选中的年级ID
        var $gradeValue = $("#addGrade").val();
        $.ajax({
            type: 'POST',
            url: 'getClassesByGrade',
            cache: false,
            dataType: 'json',
            data: {
                gradeId: $gradeValue
            },
            success: function (result) {
                //清楚班级下拉框之前的项目
                $("#addClasses option[value!='0']").remove();
                //设置年级下拉菜单
                var data = result.entity;
                for (var index in data) {
                    var $option = $("<option></option>");
                    $option.attr("value", data[index].classesId);
                    $option.text(data[index].classes);
                    var $classesElement = $("#addClasses");
                    $classesElement.append($option);
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("抱歉系统繁忙..");
            }
        });
    });



    $('#addRoster').click(function () {
        if (!validRoster1()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'addRoster',
            cache: false,
            dataType: 'json',
            data: {
                classesId: $.trim($("#addClasses").val())
            },
            success: function (data) {
                $("#addModal").modal("hide");//关闭模糊框
                showInfo(data.message);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("抱歉系统繁忙..");
            }
        });
    });

    $('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
        location.reload();  	//刷新当前页面
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


function validRoster1() {
    var flag = true;

    var grade = $.trim($("#addGrade").val());
    if (grade == 0) {
        $('#addGrade').parent().addClass("has-error");
        $('#addGrade').next().html("<i class='fa fa-times-circle-o'></i>    请选择年级");
        $("#addGrade").next().show();
        flag = false;
    }else {
        $('#addGrade').parent().removeClass("has-error");
        $('#addGrade').next().text("");
        $("#addGrade").next().hide();
    }

    var classes = $.trim($("#addClasses").val());
    if (classes == 0) {
        $('#addClasses').parent().addClass("has-error");
        $('#addClasses').next().html("<i class='fa fa-times-circle-o'></i>    请选择班级");
        $("#addClasses").next().show();
        flag = false;
    }else {
        $('#addClasses').parent().removeClass("has-error");
        $('#addClasses').next().text("");
        $("#addClasses").next().hide();
    }

    return flag;
}