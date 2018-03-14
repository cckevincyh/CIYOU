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
        $("#updateSubjectId option[value!='0']").remove();
        //设置年级下拉菜单
        var data = result.entity;
        for(var index in data) {
            var $option = $("<option></option>");
            $option.attr("value",data[index].subjectId);
            $option.text(data[index].subjectName);
            var $subjectElement = $("#updateSubjectId");
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

            document.getElementById("updateGradeId").appendChild(op);
        }
    }
});



$(function () {


    $('#updateVideoButton').click(function () {

        if (!validUpdateVideo()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'updateVideo',
            cache: false,
            dataType:'json',
            data: {
                videoId:$.trim($("#updateVideoId").val()),
                videoName: $.trim($("#updateVideoName").val()),
                videoUrl:$.trim($("#updateVideo").val()),
                imgUrl: $.trim($("#updateVideoImg").val()),
                "subject.subjectId": $.trim($("#updateSubjectId").val()),
                "grade.gradeId": $.trim($("#updateGradeId").val())
            },
            success: function (data) {
                if(data.isSuccess){
                    $("#updateModal").modal("hide");//关闭模糊框
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



function validUpdateVideo() {
    var flag = true;

    var videoName = $.trim($("#updateVideoName").val());
    if (videoName == "") {
        $('#updateVideoName').parent().addClass("has-error");
        $('#updateVideoName').next().html("<i class='fa fa-times-circle-o'></i>    请输入视频名称");
        $("#updateVideoName").next().show();
        flag = false;
    }else {
        $('#updateVideoName').parent().removeClass("has-error");
        $('#updateVideoName').next().text("");
        $("#updateVideoName").next().hide();
    }

    var video = $.trim($("#updateVideo").val());
    if (video == "") {
        $('#updateVideo').parent().addClass("has-error");
        $('#updateVideo').next().html("<i class='fa fa-times-circle-o'></i>    请上传视频");
        $("#updateVideo").next().show();
        flag = false;
    }else {
        $('#updateVideo').parent().removeClass("has-error");
        $('#updateVideo').next().text("");
        $("#updateVideo").next().hide();
    }

    var videoImg = $.trim($("#updateVideoImg").val());
    if (videoImg == "") {
        $('#updateVideoImg').parent().addClass("has-error");
        $('#updateVideoImg').next().html("<i class='fa fa-times-circle-o'></i>    请上传视频封面");
        $("#updateVideoImg").next().show();
        flag = false;
    }else {
        $('#updateVideoImg').parent().removeClass("has-error");
        $('#updateVideoImg').next().text("");
        $("#updateVideoImg").next().hide();
    }

    var grade = $.trim($("#updateGradeId").val());
    if (grade == 0) {
        $('#updateGradeId').parent().addClass("has-error");
        $('#updateGradeId').next().html("<i class='fa fa-times-circle-o'></i>    请选择年级");
        $("#updateGradeId").next().show();
        flag = false;
    }else {
        $('#updateGradeId').parent().removeClass("has-error");
        $('#updateGradeId').next().text("");
        $("#updateGradeId").next().hide();
    }

    var subject = $.trim($("#updateSubjectId").val());
    if (subject == 0) {
        $('#updateSubjectId').parent().addClass("has-error");
        $('#updateSubjectId').next().html("<i class='fa fa-times-circle-o'></i>    请选择科目");
        $("#updateSubjectId").next().show();
        flag = false;
    }else {
        $('#updateSubjectId').parent().removeClass("has-error");
        $('#updateSubjectId').next().text("");
        $("#updateSubjectId").next().hide();
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

function update_Video(id){
    $.ajax({
        type: 'POST',
        url: 'getVideoById',
        cache: false,
        dataType:'json',
        data: {
            videoId: id
        },
        success: function (data) {
            $("#updateVideoId").val(data.entity.videoId);
            $("#updateVideoName").val(data.entity.videoName);
            $("#updateVideo").val(data.entity.videoUrl);
            $("#updateVideoImg").val(data.entity.imgUrl);
            if(data.entity.grade!= null){
                $("#updateGradeId").val(data.entity.grade.gradeId);
            }else{
                $("#updateGradeId").val(0);
            }
            if(data.entity.subject!= null){
                $("#updateSubjectId").val(data.entity.subject.subjectId);
            }else{
                $("#updateSubjectId").val(0);
            }
        }
    });
}
