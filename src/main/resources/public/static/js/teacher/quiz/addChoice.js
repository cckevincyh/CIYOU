
$(function () {
	
	
	

    $('#addChoice').click(function () {
        if (!validAddChoice()) {
            return;
        }
		
		$.ajax({
            type: 'POST',
            url: 'addChoice',
            cache: false,
            data: {
				"quiz.quizId":$.trim($("#quizId").val()),
                question: $.trim($("#choice_question").val()),
				optionA: $.trim($("#optionA").val()),
				optionB: $.trim($("#optionB").val()),
				optionC: $.trim($("#optionC").val()),
				optionD: $.trim($("#optionD").val()),
				answer: $.trim($("#choice_answer").val())
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


function validAddChoice() {
    var flag = true;

    var choice_question = $.trim($("#choice_question").val());
    if (choice_question == "") {
        $('#choice_question').parent().addClass("has-error");
        $('#choice_question').next().html("<i class='fa fa-times-circle-o'></i>    请输入选择题问题");
        $("#choice_question").next().show();
        flag = false;
    }else {
        $('#choice_question').parent().removeClass("has-error");
        $('#choice_question').next().html("");
        $("#choice_question").next().hide();
    }

 	 var optionA = $.trim($("#optionA").val());
    if (optionA == "") {
        $('#optionA').parent().addClass("has-error");
        $('#optionA').next().html("<i class='fa fa-times-circle-o'></i>    请输入选项A的答案");
        $("#optionA").next().show();
        flag = false;
    }else {
        $('#optionA').parent().removeClass("has-error");
        $('#optionA').next().html("");
        $("#optionA").next().hide();
    }
	
	 var optionB = $.trim($("#optionB").val());
    if (optionB == "") {
        $('#optionB').parent().addClass("has-error");
        $('#optionB').next().html("<i class='fa fa-times-circle-o'></i>    请输入选项B的答案");
        $("#optionB").next().show();
        flag = false;
    }else {
        $('#optionB').parent().removeClass("has-error");
        $('#optionB').next().html("");
        $("#optionB").next().hide();
    }
	
	 var optionC = $.trim($("#optionC").val());
    if (optionC == "") {
        $('#optionC').parent().addClass("has-error");
        $('#optionC').next().html("<i class='fa fa-times-circle-o'></i>    请输入选项C的答案");
        $("#optionC").next().show();
        flag = false;
    }else {
        $('#optionC').parent().removeClass("has-error");
        $('#optionC').next().html("");
        $("#optionC").next().hide();
    }
	
	
	 var optionD = $.trim($("#optionD").val());
    if (optionD == "") {
        $('#optionD').parent().addClass("has-error");
        $('#optionD').next().html("<i class='fa fa-times-circle-o'></i>    请输入选项D的答案");
        $("#optionD").next().show();
        flag = false;
    }else {
        $('#optionD').parent().removeClass("has-error");
        $('#optionD').next().html("");
        $("#optionD").next().hide();
    }
	
	
	var answer = $.trim($("#choice_answer").val());
    if (answer == 0) {
        $('#choice_answer').parent().addClass("has-error");
        $('#choice_answer').next().html("<i class='fa fa-times-circle-o'></i>    请选择正确答案");
        $("#choice_answer").next().show();
        flag = false;
    }else {
        $('#choice_answer').parent().removeClass("has-error");
        $('#choice_answer').next().html("");
        $("#choice_answer").next().hide();
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

