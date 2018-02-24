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
            $("#updateGrade option[value!='0']").remove();
            //设置年级下拉菜单
            var data = result.entity;
            for (var index in data) {
                var $option = $("<option></option>");
                $option.attr("value", data[index].gradeId);
                $option.text(data[index].gradeName);
                var $gradeElement = $("#updateGrade");
                $gradeElement.append($option);
            }

        }
    });


    /**
     * 年级下拉框改变时调用
     */
    $("#updateGrade").change(function () {
        //获得选中的年级ID
        var $gradeValue = $("#updateGrade").val();
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
                $("#updateClasses option[value!='0']").remove();
                //设置年级下拉菜单
                var data = result.entity;
                for (var index in data) {
                    var $option = $("<option></option>");
                    $option.attr("value", data[index].classesId);
                    $option.text(data[index].classes);
                    var $classesElement = $("#updateClasses");
                    $classesElement.append($option);
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("抱歉系统繁忙..");
            }
        });
    });



    $('#updateRoster').click(function () {
        if (!validRoster()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'updateRoster',
            cache: false,
            dataType: 'json',
            data: {
                classesId: $.trim($("#updateClasses").val())
            },
            success: function (data) {
                $("#updateModal").modal("hide");//关闭模糊框
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


function validRoster() {
    var flag = true;

    var grade = $.trim($("#updateGrade").val());
    if (grade == 0) {
        $('#updateGrade').parent().addClass("has-error");
        $('#updateGrade').next().html("<i class='fa fa-times-circle-o'></i>    请选择年级");
        $("#updateGrade").next().show();
        flag = false;
    }else {
        $('#updateGrade').parent().removeClass("has-error");
        $('#updateGrade').next().text("");
        $("#updateGrade").next().hide();
    }

    var classes = $.trim($("#updateClasses").val());
    if (classes == 0) {
        $('#updateClasses').parent().addClass("has-error");
        $('#updateClasses').next().html("<i class='fa fa-times-circle-o'></i>    请选择班级");
        $("#updateClasses").next().show();
        flag = false;
    }else {
        $('#updateClasses').parent().removeClass("has-error");
        $('#updateClasses').next().text("");
        $("#updateClasses").next().hide();
    }

    return flag;
}



function updateRoster(id) {
    $.ajax({
        type: 'POST',
        url: 'getRosterById',
        cache: false,
        dataType: 'json',
        data: {
            rid: id
        },
        success: function (data) {
            if(data.entity.classes != null && data.entity.classes.grade != null){
                $.ajax({
                    type: 'POST',
                    url: 'getClassesByGrade',
                    cache: false,
                    async: false,
                    dataType:'json',
                    data:{
                        gradeId : data.entity.classes.grade.gradeId
                    },
                    success: function (result) {
                         if(result.isSuccess){
                            //清楚班级下拉框之前的项目
                            $("#updateClasses option[value!='0']").remove();
                            //设置年级下拉菜单
                            var data1 = result.entity;
                            for(var index in data1) {
                                var $option = $("<option></option>");
                                $option.attr("value",data1[index].classesId);
                                $option.text(data1[index].classes);
                                var $classesElement = $("#updateClasses");
                                $classesElement.append($option);
                            }
                            $("#updateGrade").val(data.entity.classes.grade.gradeId);
                            $("#updateClasses").val(data.entity.classes.classesId);
                        }else{
                            showInfo1(result.message)
                        }

                    }
                });
            }else{
                //当班级没有的时候
                $("#updateGrade").val(0);
                $("#updateClasses").val(0);
            }
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
