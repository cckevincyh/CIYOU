/**
 * ajax 提交修改年级
 */
$(function () {
	

    $('#updateGrade').click(function () {

    	
   	 if (!validUpdateGrade()) {
         return;
     }


		$.ajax({
			type: 'POST',
			url: 'updateGrade',
			cache: false,
			dataType:'json',
			data: {
				gradeId : $.trim($("#updateGradeId").val()),
				gradeName :$.trim($("#updateGradeName").val())
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







function updateGrade(id){
	$.ajax({
		type: 'POST',
		url: 'getGrade',
		cache: false,
		dataType:'json',
		data: {
			gradeId: id
		},
		success: function (data) {
			$("#updateGradeId").val(data.entity.gradeId);
			$("#updateGradeName").val(data.entity.gradeName);
		}
	});
			

}


function validUpdateGrade() {
    var flag = true;

    var gradeName = $.trim($("#updateGradeName").val());
    if (gradeName == "") {
        $('#updateGradeName').parent().addClass("has-error");
        $('#updateGradeName').next().html("<i class='fa fa-times-circle-o'></i>    请输入年级");
        $("#updateGradeName").next().show();
        flag = false;
    }else {
        $('#updateGradeName').parent().removeClass("has-error");
        $('#updateGradeName').next().html("");
        $("#updateGradeName").next().hide();
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
