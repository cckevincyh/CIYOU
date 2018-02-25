
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
     * 获取科目
     */
    $.ajax({
        type: 'POST',
        url: 'getAllSubject',
        cache: false,
        dataType: 'json',
        success: function (result) {
            //清空之前的
            $("#addSubject option[value!='0']").remove();
            //设置年级下拉菜单
            var data = result.entity;
            for(var index in data) {
                var $option = $("<option></option>");
                $option.attr("value",data[index].subjectId);
                $option.text(data[index].subjectName);
                var $subjectElement = $("#addSubject");
                $subjectElement.append($option);
            }
        }
    });


    $('#addQuiz').click(function () {


 	if (!validAddQuiz()) {
        return;
    }
	
	
		$.ajax({
            type: 'POST',
            url: 'addQuiz',
            cache: false,
            data: {
                quizName: $.trim($("#addQuizName").val()),
			  	"subject.subjectId": $.trim($("#addSubject").val()),
				quizTime: $.trim($("#addQuizTime").val()),
                "grade.gradeId": $.trim($("#addGrade").val()),
				choiceScore: $.trim($("#addChoiceScore").val()),
				judgeScore: $.trim($("#addJudgeScore").val())
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
                showInfo("添加失败，请重试");
            }
        });
	

	});


		
   
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});

		
});


function validAddQuiz() {
    var flag = true;


    var quizName = $.trim($("#addQuizName").val());
    if (quizName == "") {
        $('#addQuizName').parent().addClass("has-error");
        $('#addQuizName').next().html("<i class='fa fa-times-circle-o'></i>    请输入小测名称");
        $("#addQuizName").next().show();
        flag = false;
    }else {
        $('#addQuizName').parent().removeClass("has-error");
        $('#addQuizName').next().html("");
        $("#addQuizName").next().hide();
    }
	
	
	var subject = $.trim($("#addSubject").val());
	if(subject ==  0){
		 $('#addSubject').parent().addClass("has-error");
        $('#addSubject').next().html("<i class='fa fa-times-circle-o'></i>    请选择所属科目");
        $("#addSubject").next().show();
        flag = false;
	}else {
        $('#addSubject').parent().removeClass("has-error");
        $('#addSubject').next().html("");
        $("#addSubject").next().hide();
    }

    var grade = $.trim($("#addGrade").val());
    if(grade ==  0){
        $('#addGrade').parent().addClass("has-error");
        $('#addGrade').next().html("<i class='fa fa-times-circle-o'></i>    请选择所属年级");
        $("#addGrade").next().show();
        flag = false;
    }else {
        $('#addGrade').parent().removeClass("has-error");
        $('#addGrade').next().html("");
        $("#addGrade").next().hide();
    }
	
	
	var quizTime = $.trim($("#addQuizTime").val());
	if(quizTime == ""){
		 $('#addQuizTime').parent().addClass("has-error");
        $('#addQuizTime').next().html("<i class='fa fa-times-circle-o'></i>    请输入小测时间");
        $("#addQuizTime").next().show();
        flag = false;
	}else if(quizTime<=0 || quizTime!=parseInt(quizTime)){
		 $('#addQuizTime').parent().addClass("has-error");
        $('#addQuizTime').next().html("<i class='fa fa-times-circle-o'></i>    时间必须为正整数");
        $("#addQuizTime").next().show();
        flag = false;
	}else {
        $('#addQuizTime').parent().removeClass("has-error");
        $('#addQuizTime').next().html("");
        $("#addQuizTime").next().hide();
    } 
	
	
	var addChoiceScore = $.trim($("#addChoiceScore").val());
	if(addChoiceScore == ""){
		 $('#addChoiceScore').parent().addClass("has-error");
        $('#addChoiceScore').next().html("<i class='fa fa-times-circle-o'></i>    请输入选择题分值");
        $("#addChoiceScore").next().show();
        flag = false;
	}else if(addChoiceScore<=0 || addChoiceScore!=parseInt(addChoiceScore)){
		 $('#addChoiceScore').parent().addClass("has-error");
        $('#addChoiceScore').next().html("<i class='fa fa-times-circle-o'></i>    选择题分值必须为正整数");
        $("#addChoiceScore").next().show();
        flag = false;
	}else {
        $('#addChoiceScore').parent().removeClass("has-error");
        $('#addChoiceScore').next().html("");
        $("#addChoiceScore").next().hide();
    } 
	
	
	var addJudgeScore = $.trim($("#addJudgeScore").val());
	if(addJudgeScore == ""){
		 $('#addJudgeScore').parent().addClass("has-error");
        $('#addJudgeScore').next().html("<i class='fa fa-times-circle-o'></i>    请输入判断题分值");
        $("#addJudgeScore").next().show();
        flag = false;
	}else if(addJudgeScore<=0 || addJudgeScore!=parseInt(addJudgeScore)){
		 $('#addJudgeScore').parent().addClass("has-error");
        $('#addJudgeScore').next().html("<i class='fa fa-times-circle-o'></i>    判断题分值必须为正整数");
        $("#addJudgeScore").next().show();
        flag = false;
	}else {
        $('#addJudgeScore').parent().removeClass("has-error");
        $('#addJudgeScore').next().html("");
        $("#addJudgeScore").next().hide();
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
