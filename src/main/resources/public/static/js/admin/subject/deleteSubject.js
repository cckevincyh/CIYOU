/**
 * ajax请求后台删除科目
 */
function deleteSubject(id) {

	$.ajax({
		type: 'POST',
		url: 'deleteSubject',
		cache: false,
		dataType:'json',
		data: {
			subjectId: id
		},
		success: function (data) {
			if(data.stateCode == "403"){
				showInfo(data.message);
				window.location.href = "/403";
			}else{
				showInfo(data.message);
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			showInfo("提交失败，请重试");
		}
	});

}


$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
 });



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}
function showInfo1(msg) {
	$("#div_info1").text(msg);
	$("#modal_info1").modal('show');
}

