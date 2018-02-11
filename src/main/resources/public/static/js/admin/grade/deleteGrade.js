/**
 * ajax请求后台删除年级
 */
function deleteGrade(id) {

	$.ajax({
		type: 'POST',
		url: 'deleteGrade',
		cache: false,
		data: {
			gradeId: id
		},
		success: function (data) {
			showInfo(data);
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


