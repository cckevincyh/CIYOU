
$(function () {
	

    $('#updateJudge').click(function () {

    	
    	if (!validUpdateJudge()) {
            return;
        }
    	
    	$.ajax({
            type: 'POST',
            url: 'updateJudge',
            cache: false,
            data: {
            	judgeId:$.trim($("#updateJudgeId").val()),
                question: $.trim($("#updateJudge_question").val()),
				answer: $.trim($("#updateJudge_answer").val())
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






function updateJudge(id){

	$.ajax({
		            type: 'POST',
		            url: 'getJudge.action',
		            cache: false,
					dataType: "json",
		            data: {
		                judgeId: id
		            },
		            success: function (data) {
						$("#updateJudgeId").val(data.entity.judgeId);
		            	$("#updateJudge_question").val(data.entity.question);
						$("#updateJudge_answer").val(data.entity.answer);
		            }
		        });
		        
}


function validUpdateJudge() {
    var flag = true;

    var judge_question = $.trim($("#updateJudge_question").val());
    if (judge_question == "") {
        $('#updateJudge_question').parent().addClass("has-error");
        $('#updateJudge_question').next().html("<i class='fa fa-times-circle-o'></i>    请输入判断题问题");
        $("#updateJudge_question").next().show();
        flag = false;
    }else {
        $('#updateJudge_question').parent().removeClass("has-error");
        $('#updateJudge_question').next().html("");
        $("#updateJudge_question").next().hide();
    }

 	 var answer = $.trim($("#updateJudge_answer").val());
    if (answer == 0) {
        $('#updateJudge_answer').parent().addClass("has-error");
        $('#updateJudge_answer').next().html("<i class='fa fa-times-circle-o'></i>    请选择判断的答案");
        $("#updateJudge_answer").next().show();
        flag = false;
    }else {
        $('#updateJudge_answer').parent().removeClass("has-error");
        $('#updateJudge_answer').next().html("");
        $("#updateJudge_answer").next().hide();
    }
	
    return flag;
}





function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


