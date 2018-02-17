/**
 * Created by c on 2018/2/15.
 */
$(function () {

    $.ajax({
        type: 'POST',
        url: 'getAllSubject',
        cache: false,
        dataType: 'json',
        success: function (result) {
            if(result.stateCode == "403"){
                showInfo(result.message);
                window.location.href = "/403";
            }else{
                //清空之前的
                $("#addSubject option[value!='0']").remove();
                //设置年级下拉菜单
                /*
                 * <select id="updateSubject" name="updateSubject">
                 <option value="">请选择....</option>
                 </select>
                 */
                var data = result.entity;
                for(var index in data) {
                    var $option = $("<option></option>");
                    $option.attr("value",data[index].subjectId);
                    $option.text(data[index].subjectName);
                    var $subjectElement = $("#addSubject");
                    $subjectElement.append($option);
                }
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("抱歉系统繁忙..");
        }
    });


    /**
     * 保存
     */
    $('#addTeacher').click(function () {
        if (!validTeacher()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'addTeacher',
            cache: false,
            dataType: 'json',
            data: {
                teacherId: $.trim($("#addTeacherId").val()),
                name: $.trim($("#addName").val()),
                sex: $("input[name='addSex']:checked").val(),
                mobile: $.trim($("#addPhone").val()),
                email: $.trim($("#addEmail").val()),
                subjectId: $.trim($("#addSubject").val())
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


});



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


function showInfo1(msg) {
    $("#div_info1").text(msg);
    $("#modal_info1").modal('show');
}



function validTeacher() {
    var flag = true;

    var teacherId = $.trim($("#addTeacherId").val());
    if (teacherId == "") {
        $('#addTeacherId').parent().addClass("has-error");
        $('#addTeacherId').next().html("<i class='fa fa-times-circle-o'></i>    请输入教师号");
        $("#addTeacherId").next().show();
        flag = false;
    } else {
        $('#addTeacherId').parent().removeClass("has-error");
        $('#addTeacherId').next().text("");
        $("#addTeacherId").next().hide();
    }



    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
    var name = $.trim($("#addName").val());
    if(name == ""){
        $('#addName').parent().addClass("has-error");
        $('#addName').next().html("<i class='fa fa-times-circle-o'></i>    请输入教师姓名");
        $("#addName").next().show();
        flag = false;
    }else if(!reg.test(name)){
        $('#addName').parent().addClass("has-error");
        $('#addName').next().html("<i class='fa fa-times-circle-o'></i>    教师姓名必须为中文");
        $("#addName").next().show();
        flag = false;
    }else {
        $('#addName').parent().removeClass("has-error");
        $('#addName').next().html("");
        $("#addName").next().hide();
    }

    var subject = $.trim($("#addSubject").val());
    if (subject == 0) {
        $('#addSubject').parent().addClass("has-error");
        $('#addSubject').next().html("<i class='fa fa-times-circle-o'></i>    请选择科目");
        $("#addSubject").next().show();
        flag = false;
    }else {
        $('#addSubject').parent().removeClass("has-error");
        $('#addSubject').next().text("");
        $("#addSubject").next().hide();
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

    return flag;
}


