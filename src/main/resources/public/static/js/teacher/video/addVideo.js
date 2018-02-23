/**
 * Created by c on 2018/2/22.
 */
/**
 * 获取科目
 */
$.ajax({
    type: 'POST',
    url: 'getAllSubject',
    cache: false,
    dataType: 'json',
    success: function (result) {
        //清空之前的
        $("#addSubjectId option[value!='0']").remove();
        //设置年级下拉菜单
        var data = result.entity;
        for(var index in data) {
            var $option = $("<option></option>");
            $option.attr("value",data[index].subjectId);
            $option.text(data[index].subjectName);
            var $subjectElement = $("#addSubjectId");
            $subjectElement.append($option);
        }
    }
});

$.ajax({
    type: 'POST',
    url: 'getAllGrade',
    cache: false,
    dataType: 'json',
    success: function (result) {
        var data = result.entity;
        // 循环遍历每个年级，每个名称生成一个option对象，添加到<select>中
        for (var index in data) {
            var op = document.createElement("option");//创建一个指名名称元素
            op.value = data[index].gradeId;//设置op的实际值为当前的年级ID
            var textNode = document.createTextNode(data[index].gradeName);//创建文本节点
            op.appendChild(textNode);//把文本子节点添加到op元素中，指定其显示值

            document.getElementById("addGradeId").appendChild(op);
        }
    }
});



$(function () {


    $('#addVideoButton').click(function () {

        if (!validVideo()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'addVideo',
            cache: false,
            dataType:'json',
            data: {
                videoName: $.trim($("#addVideoName").val()),
                videoUrl:$.trim($("#addVideo").val()),
                imgUrl: $.trim($("#addVideoImg").val()),
                "subject.subjectId": $.trim($("#addSubjectId").val()),
                "grade.gradeId": $.trim($("#addGradeId").val())
            },
            success: function (data) {
                if(data.isSuccess){
                    $("#addModal").modal("hide");//关闭模糊框
                    showInfo(data.message);
                }else{
                    showInfo1(data.message);
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("提交失败，请重试");
            }
        });


    });

    $('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
        location.reload();  	//刷新当前页面
    });



});



function validVideo() {
    var flag = true;

    var videoName = $.trim($("#addVideoName").val());
    if (videoName == "") {
        $('#addVideoName').parent().addClass("has-error");
        $('#addVideoName').next().html("<i class='fa fa-times-circle-o'></i>    请输入视频名称");
        $("#addVideoName").next().show();
        flag = false;
    }else {
        $('#addVideoName').parent().removeClass("has-error");
        $('#addVideoName').next().text("");
        $("#addVideoName").next().hide();
    }

    var video = $.trim($("#addVideo").val());
    if (video == "") {
        $('#addVideo').parent().addClass("has-error");
        $('#addVideo').next().html("<i class='fa fa-times-circle-o'></i>    请上传视频");
        $("#addVideo").next().show();
        flag = false;
    }else {
        $('#addVideo').parent().removeClass("has-error");
        $('#addVideo').next().text("");
        $("#addVideo").next().hide();
    }

    var videoImg = $.trim($("#addVideoImg").val());
    if (videoImg == "") {
        $('#addVideoImg').parent().addClass("has-error");
        $('#addVideoImg').next().html("<i class='fa fa-times-circle-o'></i>    请上传视频封面");
        $("#addVideoImg").next().show();
        flag = false;
    }else {
        $('#addVideoImg').parent().removeClass("has-error");
        $('#addVideoImg').next().text("");
        $("#addVideoImg").next().hide();
    }

    var grade = $.trim($("#addGradeId").val());
    if (grade == 0) {
        $('#addGradeId').parent().addClass("has-error");
        $('#addGradeId').next().html("<i class='fa fa-times-circle-o'></i>    请选择年级");
        $("#addGradeId").next().show();
        flag = false;
    }else {
        $('#addGradeId').parent().removeClass("has-error");
        $('#addGradeId').next().text("");
        $("#addGradeId").next().hide();
    }

    var subject = $.trim($("#addSubjectId").val());
    if (subject == 0) {
        $('#addSubjectId').parent().addClass("has-error");
        $('#addSubjectId').next().html("<i class='fa fa-times-circle-o'></i>    请选择科目");
        $("#addSubjectId").next().show();
        flag = false;
    }else {
        $('#addSubjectId').parent().removeClass("has-error");
        $('#addSubjectId').next().text("");
        $("#addSubjectId").next().hide();
    }


    return flag;
}





function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


function showInfo1(msg) {
    $("#div_info1").text(msg);
    $("#modal_info1").modal('show');
}
