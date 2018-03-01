function quiz(id){
	//先去请求这个学生是否在考试或者该试卷已经做过了
	$.ajax({
            type: 'POST',
            url: 'getLockState',
            cache: false,
            data: {
                sid: $.trim($("#quiz_sid").val()),
				quizId:id
            },
            success: function (data) {
                if (data.isSuccess) {
					var quiz_action = $.trim($("#quiz_action").val()) +"?quizId="+ id;
					 window.location.href = quiz_action;
                }else{
					showInfo(data.message);
				}

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("失败，请重试");
            }
        });
}


function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}

