
$(function () {
	
	
	
    $('#addJudge').click(function () {
        if (!validAddJudge()) {
            return;
        }
		
		$.ajax({
            type: 'POST',
            url: 'addJudge.action',
            cache: false,
            data: {
                "quiz.quizId":$.trim($("#quizId").val()),
                question: $.trim($("#judge_question").val()),
				answer: $.trim($("#judge_answer").val())
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


function validAddJudge() {
    var flag = true;

    var judge_question = $.trim($("#judge_question").val());
    if (judge_question == "") {
        $('#judge_question').parent().addClass("has-error");
        $('#judge_question').next().html("<i class='fa fa-times-circle-o'></i>    请输入判断题问题");
        $("#judge_question").next().show();
        flag = false;
    }else {
        $('#judge_question').parent().removeClass("has-error");
        $('#judge_question').next().html("");
        $("#judge_question").next().hide();
    }

 	 var answer = $.trim($("#judge_answer").val());
    if (answer == 0) {
        $('#judge_answer').parent().addClass("has-error");
        $('#judge_answer').next().html("<i class='fa fa-times-circle-o'></i>    请选择判断的答案");
        $("#judge_answer").next().show();
        flag = false;
    }else {
        $('#judge_answer').parent().removeClass("has-error");
        $('#judge_answer').next().html("");
        $("#judge_answer").next().hide();
    }
	
    return flag;
}






function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


