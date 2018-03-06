/**
 * Created by c on 2018/2/15.
 */
$(function () {

    $('#updateStudent').click(function () {
        if (!validUpdateStudent()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'updateStudent',
            cache: false,
            dataType: 'json',
            data: {
                sid: $.trim($("#updateSid").val()),
                name: $.trim($("#updateName").val()),
                classesId: $.trim($("#updateClasses").val()),
                sex: $("input[name='updateSex']:checked").val(),
                age: $.trim($("#updateAge").val()),
                mobile: $.trim($("#updatePhone").val()),
                email: $.trim($("#updateEmail").val()),
                parentMobile: $.trim($("#updateParentPhone").val()),
                parentEmail: $.trim($("#updateParentEmail").val())
            },
            success: function (data) {
                if(data.stateCode == "403"){
                    showInfo(data.message);
                    window.location.href = "/403";
                }else if(data.isSuccess){
                    $("#updateModal").modal("hide");//关闭模糊框
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
                $("#updateGrade option[value!='0']").remove();
                //设置年级下拉菜单
                /*
                 * <select id="updateGrade" name="updateGrade">
                 <option value="">请选择....</option>
                 </select>
                 */
                var data = result.entity;
                for(var index in data) {
                    var $option = $("<option></option>");
                    $option.attr("value",data[index].gradeId);
                    $option.text(data[index].gradeName);
                    var $gradeElement = $("#updateGrade");
                    $gradeElement.append($option);
                }

            }

        }
    });


    /**
     * 年级下拉框改变时调用
     */
    $("#updateGrade").change(function() {
        //获得选中的年级ID
        var $gradeValue = $("#updateGrade").val();
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
                    $("#updateClasses option[value!='0']").remove();
                    //设置年级下拉菜单
                    /*
                     * <select id="updateClasses" name="updateClasses">
                     <option value="">请选择....</option>
                     </select>
                     */
                    var data = result.entity;
                    for(var index in data) {
                        var $option = $("<option></option>");
                        $option.attr("value",data[index].classesId);
                        $option.text(data[index].classes);
                        var $classesElement = $("#updateClasses");
                        $classesElement.append($option);
                    }
                }else{
                    showInfo1(result.message)
                }

            }
        });
    });
});
/**
 * 修改学生
 */
function updateStudent(id){
    $.ajax({
        type: 'POST',
        url: 'getStudentById',
        cache: false,
        dataType: 'json',
        data: {
            sid: id
        },
        success: function (data) {
            if(data.stateCode == "403"){
                showInfo(data.message);
                window.location.href = "/403";
            }else{
                if(data.entity.classes != null){
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
                            if(result.stateCode == "403"){
                                showInfo(result.message);
                                window.location.href = "/403";
                            }else if(result.isSuccess){
                                //清楚班级下拉框之前的项目
                                $("#updateClasses option[value!='0']").remove();
                                //设置年级下拉菜单
                                /*
                                 * <select id="updateClasses" name="updateClasses">
                                 <option value="">请选择....</option>
                                 </select>
                                 */
                                var data1 = result.entity;
                                for(var index in data1) {
                                    var $option = $("<option></option>");
                                    $option.attr("value",data1[index].classesId);
                                    $option.text(data1[index].classes);
                                    var $classesElement = $("#updateClasses");
                                    $classesElement.append($option);
                                }
                                $("#updateSid").val(id);
                                $("#updateStudentId").val(data.entity.studentId);
                                $("#updateName").val(data.entity.name);
                                $("#updateGrade").val(data.entity.classes.grade.gradeId);
                                $("#updateClasses").val(data.entity.classes.classesId);
                                $(":radio[name='updateSex'][value='" + data.entity.sex + "']").attr("checked", "checked");
                                $("#updateAge").val(data.entity.age);
                                $("#updatePhone").val(data.entity.mobile);
                                $("#updateEmail").val(data.entity.email);
                                $("#updateParentPhone").val(data.entity.parentMobile);
                                $("#updateParentEmail").val(data.entity.parentEmail);
                            }else{
                                showInfo1(result.message)
                            }

                        }
                    });
                }else{
                    //当班级没有的时候
                    $("#updateSid").val(id);
                    $("#updateGrade").val(0);
                    $("#updateClasses").val(0);
                    $("#updateStudentId").val(data.entity.studentId);
                    $("#updateName").val(data.entity.name);
                    $(":radio[name='updateSex'][value='" + data.entity.sex + "']").attr("checked", "checked");
                    $("#updateAge").val(data.entity.age);
                    $("#updatePhone").val(data.entity.mobile);
                    $("#updateEmail").val(data.entity.email);
                    $("#updateParentPhone").val(data.entity.parentMobile);
                    $("#updateParentEmail").val(data.entity.parentEmail);
                }
            }
        }
    });

}




function validUpdateStudent() {
    var flag = true;

    var studentId = $.trim($("#updateStudentId").val());
    if (studentId == "") {
        $('#updateStudentId').parent().addClass("has-error");
        $('#updateStudentId').next().html("<i class='fa fa-times-circle-o'></i>    请输入学生学号");
        $("#updateStudentId").next().show();
        flag = false;
    } else {
        $('#updateStudentId').parent().removeClass("has-error");
        $('#updateStudentId').next().text("");
        $("#updateStudentId").next().hide();
    }



    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
    var name = $.trim($("#updateName").val());
    if(name == ""){
        $('#updateName').parent().addClass("has-error");
        $('#updateName').next().html("<i class='fa fa-times-circle-o'></i>    请输入学生姓名");
        $("#updateName").next().show();
        flag = false;
    }else if(!reg.test(name)){
        $('#updateName').parent().addClass("has-error");
        $('#updateName').next().html("<i class='fa fa-times-circle-o'></i>    学生姓名必须为中文");
        $("#updateName").next().show();
        flag = false;
    }else {
        $('#updateName').parent().removeClass("has-error");
        $('#updateName').next().html("");
        $("#updateName").next().hide();
    }


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

    var age = $.trim($("#updateAge").val());
    if(age != "" && (age<=0 || age!=parseInt(age))){
        $('#updateAge').parent().addClass("has-error");
        $('#updateAge').next().html("<i class='fa fa-times-circle-o'></i>    年龄必须为正整数");
        $("#updateAge").next().show();
        flag = false;
    }else {
        $('#updateAge').parent().removeClass("has-error");
        $('#updateAge').next().html("");
        $("#updateAge").next().hide();
    }

    var phone = $.trim($("#updatePhone").val());
    if(phone != "" && !(/^1[34578]\d{9}$/.test(phone))){
        //电话号码格式的校验
        $('#updatePhone').parent().addClass("has-error");
        $('#updatePhone').next().html("<i class='fa fa-times-circle-o'></i>    手机号码有误");
        $("#updatePhone").next().show();
        return false;
    }else {
        $('#updatePhone').parent().removeClass("has-error");
        $('#updatePhone').next().html("");
        $("#updatePhone").next().hide();
    }


    var regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    var email = $.trim($("#updateEmail").val());
    if(email != "" && !regEmail.test(email)){
        $('#updateEmail').parent().addClass("has-error");
        $('#updateEmail').next().html("<i class='fa fa-times-circle-o'></i>    邮箱格式有误");
        $("#updateEmail").next().show();
        flag = false;
    }else{
        $('#updateEmail').parent().removeClass("has-error");
        $('#updateEmail').next().text("");
        $("#updateEmail").next().hide();
    }

    var parentPhone = $.trim($("#updateParentPhone").val());
    if(parentPhone != "" && !(/^1[34578]\d{9}$/.test(parentPhone))){
        //电话号码格式的校验
        $('#updateParentPhone').parent().addClass("has-error");
        $('#updateParentPhone').next().html("<i class='fa fa-times-circle-o'></i>    家长手机号码有误");
        $("#updateParentPhone").next().show();
        return false;
    }else {
        $('#updateParentPhone').parent().removeClass("has-error");
        $('#updateParentPhone').next().html("");
        $("#updateParentPhone").next().hide();
    }


    var regPEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    var parentEmail = $.trim($("#updateParentEmail").val());
    if(parentEmail != "" && !regPEmail.test(parentEmail)){
        $('#updateParentEmail').parent().addClass("has-error");
        $('#updateParentEmail').next().html("<i class='fa fa-times-circle-o'></i>    家长邮箱格式有误");
        $("#updateParentEmail").next().show();
        flag = false;
    }else{
        $('#updateParentEmail').parent().removeClass("has-error");
        $('#updateParentEmail').next().text("");
        $("#updateParentEmail").next().hide();
    }

    return flag;
}

