
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
     * 获取科目
     */
    $.ajax({
        type: 'POST',
        url: 'getAllSubject',
        cache: false,
        dataType: 'json',
        success: function (result) {
            //清空之前的
            $("#updateSubject option[value!='0']").remove();
            //设置年级下拉菜单
            var data = result.entity;
            for(var index in data) {
                var $option = $("<option></option>");
                $option.attr("value",data[index].subjectId);
                $option.text(data[index].subjectName);
                var $subjectElement = $("#updateSubject");
                $subjectElement.append($option);
            }
        }
    });


    $('#updateQuiz').click(function () {


 	if (!validUpdateQuiz()) {
        return;
    }
	
	
		$.ajax({
            type: 'POST',
            url: 'updateQuiz',
            cache: false,
            data: {
                quizId:$.trim($("#updateQuizId").val()),
                quizName: $.trim($("#updateQuizName").val()),
			  	"subject.subjectId": $.trim($("#updateSubject").val()),
				quizTime: $.trim($("#updateQuizTime").val()),
                "grade.gradeId": $.trim($("#updateGrade").val()),
				choiceScore: $.trim($("#updateChoiceScore").val()),
				judgeScore: $.trim($("#updateJudgeScore").val())
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
                showInfo("修改失败，请重试");
            }
        });
	

	});


		
   
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});

		
});


function validUpdateQuiz() {
    var flag = true;


    var quizName = $.trim($("#updateQuizName").val());
    if (quizName == "") {
        $('#updateQuizName').parent().addClass("has-error");
        $('#updateQuizName').next().html("<i class='fa fa-times-circle-o'></i>    请输入小测名称");
        $("#updateQuizName").next().show();
        flag = false;
    }else {
        $('#updateQuizName').parent().removeClass("has-error");
        $('#updateQuizName').next().html("");
        $("#updateQuizName").next().hide();
    }
	
	
	var subject = $.trim($("#updateSubject").val());
	if(subject ==  0){
		 $('#updateSubject').parent().addClass("has-error");
        $('#updateSubject').next().html("<i class='fa fa-times-circle-o'></i>    请选择所属科目");
        $("#updateSubject").next().show();
        flag = false;
	}else {
        $('#updateSubject').parent().removeClass("has-error");
        $('#updateSubject').next().html("");
        $("#updateSubject").next().hide();
    }

    var grade = $.trim($("#updateGrade").val());
    if(grade ==  0){
        $('#updateGrade').parent().addClass("has-error");
        $('#updateGrade').next().html("<i class='fa fa-times-circle-o'></i>    请选择所属年级");
        $("#updateGrade").next().show();
        flag = false;
    }else {
        $('#updateGrade').parent().removeClass("has-error");
        $('#updateGrade').next().html("");
        $("#updateGrade").next().hide();
    }
	
	
	var quizTime = $.trim($("#updateQuizTime").val());
	if(quizTime == ""){
		 $('#updateQuizTime').parent().addClass("has-error");
        $('#updateQuizTime').next().html("<i class='fa fa-times-circle-o'></i>    请输入小测时间");
        $("#updateQuizTime").next().show();
        flag = false;
	}else if(quizTime<=0 || quizTime!=parseInt(quizTime)){
		 $('#updateQuizTime').parent().addClass("has-error");
        $('#updateQuizTime').next().html("<i class='fa fa-times-circle-o'></i>    时间必须为正整数");
        $("#updateQuizTime").next().show();
        flag = false;
	}else {
        $('#updateQuizTime').parent().removeClass("has-error");
        $('#updateQuizTime').next().html("");
        $("#updateQuizTime").next().hide();
    } 
	
	
	var updateChoiceScore = $.trim($("#updateChoiceScore").val());
	if(updateChoiceScore == ""){
		 $('#updateChoiceScore').parent().addClass("has-error");
        $('#updateChoiceScore').next().html("<i class='fa fa-times-circle-o'></i>    请输入选择题分值");
        $("#updateChoiceScore").next().show();
        flag = false;
	}else if(updateChoiceScore<=0 || updateChoiceScore!=parseInt(updateChoiceScore)){
		 $('#updateChoiceScore').parent().addClass("has-error");
        $('#updateChoiceScore').next().html("<i class='fa fa-times-circle-o'></i>    选择题分值必须为正整数");
        $("#updateChoiceScore").next().show();
        flag = false;
	}else {
        $('#updateChoiceScore').parent().removeClass("has-error");
        $('#updateChoiceScore').next().html("");
        $("#updateChoiceScore").next().hide();
    } 
	
	
	var updateJudgeScore = $.trim($("#updateJudgeScore").val());
	if(updateJudgeScore == ""){
		 $('#updateJudgeScore').parent().addClass("has-error");
        $('#updateJudgeScore').next().html("<i class='fa fa-times-circle-o'></i>    请输入判断题分值");
        $("#updateJudgeScore").next().show();
        flag = false;
	}else if(updateJudgeScore<=0 || updateJudgeScore!=parseInt(updateJudgeScore)){
		 $('#updateJudgeScore').parent().addClass("has-error");
        $('#updateJudgeScore').next().html("<i class='fa fa-times-circle-o'></i>    判断题分值必须为正整数");
        $("#updateJudgeScore").next().show();
        flag = false;
	}else {
        $('#updateJudgeScore').parent().removeClass("has-error");
        $('#updateJudgeScore').next().html("");
        $("#updateJudgeScore").next().hide();
    } 
	
	
	
    return flag;
}


function updateQuiz(id){
    $.ajax({
        type: 'POST',
        url: 'getQuizById',
        cache: false,
        dataType: "json",
        data: {
            quizId: id
        },
        success: function (data) {
            $("#updateQuizId").val(data.entity.quizId);
            $("#updateQuizName").val(data.entity.quizName);
            if(data.entity.grade != null){
                $("#updateGrade").val(data.entity.grade.gradeId);
            }
            if(data.entity.subject != null){
                $("#updateSubject").val(data.entity.subject.subjectId);
            }
            $("#updateQuizTime").val(data.entity.quizTime);
            $("#updateChoiceScore").val(data.entity.choiceScore);
            $("#updateJudgeScore").val(data.entity.judgeScore);
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
