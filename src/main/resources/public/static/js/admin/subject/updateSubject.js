/**
 * ajax 提交修改科目
 */
$(function () {
	

    $('#updateSubject').click(function () {

    	
   	 if (!validUpdateSubject()) {
         return;
     }


		$.ajax({
			type: 'POST',
			url: 'updateSubject',
			cache: false,
			dataType:'json',
			data: {
				subjectId : $.trim($("#updateSubjectId").val()),
				subjectName :$.trim($("#updateSubjectName").val())
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
				showInfo("提交失败，请重试");
			}
		});
    	
	});
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});







function updateSubject(id){
	$.ajax({
		type: 'POST',
		url: 'getSubject',
		cache: false,
		dataType:'json',
		data: {
			subjectId: id
		},
		success: function (data) {
			$("#updateSubjectId").val(data.entity.subjectId);
			$("#updateSubjectName").val(data.entity.subjectName);
		}
	});
			

}


function validUpdateSubject() {
    var flag = true;

    var subjectName = $.trim($("#updateSubjectName").val());
    if (subjectName == "") {
        $('#updateSubjectName').parent().addClass("has-error");
        $('#updateSubjectName').next().html("<i class='fa fa-times-circle-o'></i>    请输入科目");
        $("#updateSubjectName").next().show();
        flag = false;
    }else {
        $('#updateSubjectName').parent().removeClass("has-error");
        $('#updateSubjectName').next().html("");
        $("#updateSubjectName").next().hide();
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
