


function deleteJudge(id){
	
	
	$.ajax({
        type: 'POST',
        url: 'deleteJudge',
        cache: false,
        data: {
        	judgeId:id,
        },
        success: function (data) {
            showInfo(data.message);
        },
		error: function (jqXHR, textStatus, errorThrown) {
            showInfo("删除失败，请重试");
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


