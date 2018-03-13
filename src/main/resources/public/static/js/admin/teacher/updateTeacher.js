/**
 * Created by c on 2018/2/17.
 */
$(function () {

    $.ajax({
        type: 'POST',
        url: 'getAllSubject',
        cache: false,
        dataType: 'json',
        success: function (result) {
            if (result.stateCode == "403") {
                showInfo(result.message);
                window.location.href = "/403";
            } else {
                //清空之前的
                $("#updateSubject option[value!='0']").remove();
                //设置年级下拉菜单
                /*
                 * <select id="updateSubject" name="updateSubject">
                 <option value="">请选择....</option>
                 </select>
                 */
                var data = result.entity;
                for (var index in data) {
                    var $option = $("<option></option>");
                    $option.attr("value", data[index].subjectId);
                    $option.text(data[index].subjectName);
                    var $subjectElement = $("#updateSubject");
                    $subjectElement.append($option);
                }
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("抱歉系统繁忙..");
        }
    });

    $('#updateTeacher').click(function () {
        if (!validUpdateTeacher()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'updateTeacher',
            cache: false,
            dataType: 'json',
            data: {
                tid: $.trim($("#updateTid").val()),
                name: $.trim($("#updateName").val()),
                sex: $("input[name='updateSex']:checked").val(),
                mobile: $.trim($("#updatePhone").val()),
                email: $.trim($("#updateEmail").val()),
                subjectId: $.trim($("#updateSubject").val())
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

});



function updateTeacher(id){
    $.ajax({
        type: 'POST',
        url: 'getTeacherByTid',
        cache: false,
        dataType:'json',
        data: {
            tid: id
        },
        success: function (data) {
            $("#updateTid").val(id);
            $("#updateTeacherId").val(data.entity.teacherId);
            $("#updateName").val(data.entity.name);
            if(data.entity.subject != null){
                $("#updateSubject").val(data.entity.subject.subjectId);
            }else{
                $("#updateSubject").val(0);
            }
            $(":radio[name='updateSex'][value='" + data.entity.sex + "']").attr("checked", "checked");
            $("#updatePhone").val(data.entity.mobile);
            $("#updateEmail").val(data.entity.email);
        }
    });
}



function validUpdateTeacher() {
    var flag = true;

    var teacherId = $.trim($("#updateTeacherId").val());
    if (teacherId == "") {
        $('#updateTeacherId').parent().addClass("has-error");
        $('#updateTeacherId').next().html("<i class='fa fa-times-circle-o'></i>    请输入教师号");
        $("#updateTeacherId").next().show();
        flag = false;
    } else {
        $('#updateTeacherId').parent().removeClass("has-error");
        $('#updateTeacherId').next().text("");
        $("#updateTeacherId").next().hide();
    }



    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
    var name = $.trim($("#updateName").val());
    if(name == ""){
        $('#updateName').parent().addClass("has-error");
        $('#updateName').next().html("<i class='fa fa-times-circle-o'></i>    请输入教师姓名");
        $("#updateName").next().show();
        flag = false;
    }else if(!reg.test(name)){
        $('#updateName').parent().addClass("has-error");
        $('#updateName').next().html("<i class='fa fa-times-circle-o'></i>    教师姓名必须为中文");
        $("#updateName").next().show();
        flag = false;
    }else {
        $('#updateName').parent().removeClass("has-error");
        $('#updateName').next().html("");
        $("#updateName").next().hide();
    }

    var subject = $.trim($("#updateSubject").val());
    if (subject == 0) {
        $('#updateSubject').parent().addClass("has-error");
        $('#updateSubject').next().html("<i class='fa fa-times-circle-o'></i>    请选择科目");
        $("#updateSubject").next().show();
        flag = false;
    }else {
        $('#updateSubject').parent().removeClass("has-error");
        $('#updateSubject').next().text("");
        $("#updateSubject").next().hide();
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

    return flag;
}