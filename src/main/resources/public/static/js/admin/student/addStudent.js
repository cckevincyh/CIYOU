/**
 * Created by c on 2018/2/15.
 */
$(function () {


    /**
     * 获取所有的年级
     */
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

        }
    });


    /**
     * 保存
     */
    $('#addStudent').click(function () {
        if (!validStudent()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'addStudent',
            cache: false,
            dataType: 'json',
            data: {
                studentId: $.trim($("#addStudentId").val()),
                name: $.trim($("#addName").val()),
                classesId: $.trim($("#addClasses").val()),
                sex: $("input[name='addSex']:checked").val(),
                age: $.trim($("#addAge").val()),
                mobile: $.trim($("#addPhone").val()),
                email: $.trim($("#addEmail").val()),
                parentMobile: $.trim($("#addParentPhone").val()),
                parentEmail: $.trim($("#addParentEmail").val())
            },
            success: function (data) {
                if(data.stateCode == "403"){
                    showInfo(data.message);
                    window.location.href = "/403";
                }else if(data.isSuccess){
                    $("#addModal").modal("hide");//关闭模糊框
                    showInfo(data.message);
                }else{
                    showInfo1(data.message);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("抱歉系统繁忙..");
            }
        });
    });

    $('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
        location.reload();  	//刷新当前页面
    });

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
                showInfo("抱歉系统繁忙..");
            }
        });
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



function validStudent() {
    var flag = true;

    var studentId = $.trim($("#addStudentId").val());
    if (studentId == "") {
        $('#addStudentId').parent().addClass("has-error");
        $('#addStudentId').next().html("<i class='fa fa-times-circle-o'></i>    请输入学生学号");
        $("#addStudentId").next().show();
        flag = false;
    } else {
        $('#addStudentId').parent().removeClass("has-error");
        $('#addStudentId').next().text("");
        $("#addStudentId").next().hide();
    }



    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
    var name = $.trim($("#addName").val());
    if(name == ""){
        $('#addName').parent().addClass("has-error");
        $('#addName').next().html("<i class='fa fa-times-circle-o'></i>    请输入学生姓名");
        $("#addName").next().show();
        flag = false;
    }else if(!reg.test(name)){
        $('#addName').parent().addClass("has-error");
        $('#addName').next().html("<i class='fa fa-times-circle-o'></i>    学生姓名必须为中文");
        $("#addName").next().show();
        flag = false;
    }else {
        $('#addName').parent().removeClass("has-error");
        $('#addName').next().html("");
        $("#addName").next().hide();
    }


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

    var age = $.trim($("#addAge").val());
    if(age != "" && (age<=0 || age!=parseInt(age))){
        $('#addAge').parent().addClass("has-error");
        $('#addAge').next().html("<i class='fa fa-times-circle-o'></i>    年龄必须为正整数");
        $("#addAge").next().show();
        flag = false;
    }else {
        $('#addAge').parent().removeClass("has-error");
        $('#addAge').next().html("");
        $("#addAge").next().hide();
    }

    var phone = $.trim($("#addPhone").val());
    if(phone != "" && !(/^1[34578]\d{9}$/.test(phone))){
        //电话号码格式的校验
        $('#addPhone').parent().addClass("has-error");
        $('#addPhone').next().html("<i class='fa fa-times-circle-o'></i>    手机号码有误");
        $("#addPhone").next().show();
        return false;
    }else {
        $('#addPhone').parent().removeClass("has-error");
        $('#addPhone').next().html("");
        $("#addPhone").next().hide();
    }


    var regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    var email = $.trim($("#addEmail").val());
    if(email != "" && !regEmail.test(email)){
        $('#addEmail').parent().addClass("has-error");
        $('#addEmail').next().html("<i class='fa fa-times-circle-o'></i>    邮箱格式有误");
        $("#addEmail").next().show();
        flag = false;
    }else{
        $('#addEmail').parent().removeClass("has-error");
        $('#addEmail').next().text("");
        $("#addEmail").next().hide();
    }

    var parentPhone = $.trim($("#addParentPhone").val());
    if(parentPhone != "" && !(/^1[34578]\d{9}$/.test(parentPhone))){
        //电话号码格式的校验
        $('#addParentPhone').parent().addClass("has-error");
        $('#addParentPhone').next().html("<i class='fa fa-times-circle-o'></i>    家长手机号码有误");
        $("#addParentPhone").next().show();
        return false;
    }else {
        $('#addParentPhone').parent().removeClass("has-error");
        $('#addParentPhone').next().html("");
        $("#addParentPhone").next().hide();
    }


    var regPEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    var parentEmail = $.trim($("#addParentEmail").val());
    if(parentEmail != "" && !regPEmail.test(parentEmail)){
        $('#addParentEmail').parent().addClass("has-error");
        $('#addParentEmail').next().html("<i class='fa fa-times-circle-o'></i>    家长邮箱格式有误");
        $("#addParentEmail").next().show();
        flag = false;
    }else{
        $('#addParentEmail').parent().removeClass("has-error");
        $('#addParentEmail').next().text("");
        $("#addParentEmail").next().hide();
    }

    return flag;
}


